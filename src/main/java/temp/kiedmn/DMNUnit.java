package temp.kiedmn;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.kie.api.runtime.rule.RuleUnit;
import org.kie.api.runtime.rule.RuleUnitExecutor;
import org.kie.dmn.api.core.DMNDecisionResult;
import org.kie.dmn.api.core.DMNDecisionResult.DecisionEvaluationStatus;
import org.kie.dmn.core.impl.DMNDecisionResultImpl;
import org.kie.dmn.feel.codegen.feel11.CompiledFEELExpression;
import org.kie.dmn.feel.lang.EvaluationContext;

public abstract class DMNUnit implements RuleUnit {

    private EvaluationContext evalCtx;
    private List<CompiledFEELExpression> compiledExprs;

    protected Object result;

    // TODO
    public Set<String> getRequirements() {
        return Collections.emptySet();
    }

    public Object getResult() {
        return result;
    }

    public DMNDecisionResult execute(RuleUnitExecutor executor) {
        executor.run(this);
        DMNDecisionResultImpl result = new DMNDecisionResultImpl("x", "x", DecisionEvaluationStatus.SUCCEEDED, getResult(), Collections.emptyList());
        return result;
    }

    protected Object getValue( int pos ) {
        return compiledExprs.get(pos).apply( evalCtx );
    }

    public DMNUnit setEvalCtx( EvaluationContext evalCtx ) {
        this.evalCtx = evalCtx;
        return this;
    }

    public DMNUnit setCompiledExprs( List<CompiledFEELExpression> compiledExprs ) {
        this.compiledExprs = compiledExprs;
        return this;
    }
}
