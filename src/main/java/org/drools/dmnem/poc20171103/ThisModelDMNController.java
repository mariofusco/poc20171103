package org.drools.dmnem.poc20171103;

import org.drools.core.event.DebugRuleRuntimeEventListener;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import temp.kiedmn.Computed;
import temp.kiedmn.DecisionNode;
import temp.kiedmn.InformationRequirement;
import temp.kiedmn.InputDataNode;
import temp.kiedmn.Requested;

public class ThisModelDMNController {

    public static void main(String[] args) {
        run();
    }

    public static void run() {
        InputDataNode now = new InputDataNode(null, "now");
        InputDataNode isPresent = new InputDataNode(null, "is Present");

        DecisionNode sunlight = new DecisionNode(null, "sunlight");
        DecisionNode suggestedBlinds = new DecisionNode(null, "suggested Blinds");
        DecisionNode suggestedLights = new DecisionNode(null, "suggested Lights");

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

        kieSession.insert(new Computed(now));
        kieSession.insert(new Computed(isPresent));

        // let's assume we want to compute only suggestedLights
        kieSession.insert(new Requested(suggestedLights));

        kieSession.fireAllRules();
        for (Object o : kieSession.getObjects()) {
            System.out.println(o);
        }
        // first will ask to compute sunlight

        System.out.println("---");

        kieSession.insert(new Computed(sunlight));
        kieSession.fireAllRules();
        for (Object o : kieSession.getObjects()) {
            System.out.println(o);
        }
        // then will ask to compute suggestedLights
    }
}
