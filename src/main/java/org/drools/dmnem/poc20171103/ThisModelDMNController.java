package org.drools.dmnem.poc20171103;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.drools.core.event.DebugRuleRuntimeEventListener;
import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.RuleUnitExecutor;
import org.kie.dmn.api.core.DMNContext;
import org.kie.dmn.core.impl.DMNContextImpl;
import temp.kiedmn.Computed;
import temp.kiedmn.DMNUnit;
import temp.kiedmn.DRGNode;
import temp.kiedmn.DecisionNode;
import temp.kiedmn.InformationRequirement;
import temp.kiedmn.InputDataNode;
import temp.kiedmn.Requested;
import temp.kiedmn.ToBeEvaluated;

public class ThisModelDMNController {

    public static void main(String[] args) {
        DMNContext dmnContext = new DMNContextImpl();
        dmnContext.set("now", LocalDateTime.parse("2007-12-03T10:15:30"));
        dmnContext.set("is Present", true);
        run(dmnContext, Collections.emptyList());
    }

    public static void run(DMNContext dmnContext, List<String> decisionNames) {
        InputDataNode now = new InputDataNode(null, "now");
        InputDataNode isPresent = new InputDataNode(null, "is Present");

        DecisionNode sunlight = new DecisionNode(null, "sunlight", ctx -> new SunlightDTUnit(ctx));
        DecisionNode suggestedBlinds = new DecisionNode(null, "Suggested Blinds", ctx -> new SuggestedBlindsDTUnit(ctx));
        DecisionNode suggestedLights = new DecisionNode(null, "Suggested light", ctx -> new SuggestedLightDTUnit(ctx));

        Map<String, DRGNode> drgByName = Stream.of(now, isPresent, sunlight, suggestedBlinds, suggestedLights).collect(Collectors.toMap(DRGNode::getName, Function.identity()));

        InformationRequirement req1 = new InformationRequirement(sunlight, now);
        InformationRequirement req2 = new InformationRequirement(suggestedBlinds, sunlight);
        InformationRequirement req3 = new InformationRequirement(suggestedBlinds, isPresent);
        InformationRequirement req4 = new InformationRequirement(suggestedLights, sunlight);
        InformationRequirement req5 = new InformationRequirement(suggestedLights, isPresent);

        KieServices ks = KieServices.get();
        KieContainer kieContainer = ks.newKieClasspathContainer();

        KieSession kieSession = kieContainer.newKieSession("ControllerSession1");

        kieSession.addEventListener(new DebugRuleRuntimeEventListener());

        kieSession.insert(now);
        kieSession.insert(isPresent);
        kieSession.insert(sunlight);
        kieSession.insert(suggestedBlinds);
        kieSession.insert(suggestedLights);
        kieSession.insert(req1);
        kieSession.insert(req2);
        kieSession.insert(req3);
        kieSession.insert(req4);
        kieSession.insert(req5);

        // compute requirements only once
        kieSession.getAgenda().getAgendaGroup("compute-requirements").setFocus();

        // take from the DMNContext was is already computed (i.e.: inputData)
        List<Computed> computed = dmnContext.getAll().keySet().stream().map(ctxKey -> new Computed(drgByName.get(ctxKey))).collect(Collectors.toList());
        computed.forEach(kieSession::insert);

        // if no specific decision is requested, compute all decisions
        if (decisionNames == null || decisionNames.isEmpty()) {
            List<Requested> allDecisionsAsRequested = drgByName.values().stream().filter(DecisionNode.class::isInstance).map(dn -> new Requested((DecisionNode) dn)).collect(Collectors.toList());
            allDecisionsAsRequested.forEach(kieSession::insert);
        } else {
            List<Requested> onlyRequestedDecisions = drgByName.entrySet().stream().filter(kv -> decisionNames.contains(kv.getKey())).map(kv -> new Requested((DecisionNode) kv.getValue())).collect(Collectors.toList());
            onlyRequestedDecisions.forEach(kieSession::insert);
        }

        // get Unit executor
        KieBase defaultBase = kieContainer.getKieBase();
        RuleUnitExecutor executor = RuleUnitExecutor.create().bind(defaultBase);

        // looping...
        List<ToBeEvaluated> nextComputations = calculateNextComputation(kieSession);
        while (!nextComputations.isEmpty()) {
            for (ToBeEvaluated next : nextComputations) {
                DRGNode nextStep = next.getStep();
                if (nextStep instanceof DecisionNode) {
                    DecisionNode decisionNode = (DecisionNode) nextStep;
                    DMNUnit unit = decisionNode.getUnit(dmnContext);
                    Object unitResult = unit.execute(executor).getResult();

                    System.out.println("The unit : " + unit + " evaluated with result: " + unitResult);
                    dmnContext.set(decisionNode.getName(), unitResult);
                    kieSession.insert(new Computed(decisionNode));
                } else {
                    throw new IllegalArgumentException("Was missing an inputdata node! for: " + nextStep.getName());
                }
            }

            nextComputations = calculateNextComputation(kieSession);
        }
    }

    private static List<ToBeEvaluated> calculateNextComputation(KieSession kieSession) {
        kieSession.fireAllRules();
        return kieSession.getObjects(ToBeEvaluated.class::isInstance).stream().map(ToBeEvaluated.class::cast).collect(Collectors.toList());
    }
}
