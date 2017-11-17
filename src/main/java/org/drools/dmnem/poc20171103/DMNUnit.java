package org.drools.dmnem.poc20171103;

import java.util.Collections;
import java.util.Set;

import org.kie.api.runtime.rule.RuleUnit;
import org.kie.api.runtime.rule.RuleUnitExecutor;
import org.kie.dmn.api.core.DMNContext;
import org.kie.dmn.api.core.DMNDecisionResult;
import org.kie.dmn.api.core.DMNDecisionResult.DecisionEvaluationStatus;
import org.kie.dmn.core.impl.DMNDecisionResultImpl;

public abstract class DMNUnit implements RuleUnit {

    private DMNContext context;

    protected DMNUnit(DMNContext context) {
        this.context = context;
    }

    // TODO
    public Set<String> getRequirements() {
        return Collections.emptySet();
    }

    public abstract Object getResult();

    public DMNDecisionResult execute(RuleUnitExecutor executor) {
        executor.run(this);
        DMNDecisionResultImpl result = new DMNDecisionResultImpl("x", "x", DecisionEvaluationStatus.SUCCEEDED, getResult(), Collections.emptyList());
        return result;
    }

}
