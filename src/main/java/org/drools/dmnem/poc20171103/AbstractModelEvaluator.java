/*
 * Copyright 2005 JBoss Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.drools.dmnem.poc20171103;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.drools.model.Model;
import org.drools.model.Rule;
import org.drools.model.impl.ModelImpl;
import org.drools.modelcompiler.builder.KieBaseBuilder;
import org.kie.api.KieBase;
import org.kie.api.runtime.rule.RuleUnitExecutor;
import org.kie.dmn.api.core.DMNResult;
import org.kie.dmn.api.core.event.DMNRuntimeEventManager;
import org.kie.dmn.api.feel.runtime.events.FEELEvent;
import org.kie.dmn.core.api.DMNExpressionEvaluator;
import org.kie.dmn.core.api.EvaluatorResult;
import org.kie.dmn.core.ast.DMNBaseNode;
import org.kie.dmn.core.ast.EvaluatorResultImpl;
import org.kie.dmn.core.impl.DMNModelImpl;
import org.kie.dmn.core.impl.DMNResultImpl;
import org.kie.dmn.core.impl.DMNRuntimeEventManagerUtils;
import org.kie.dmn.core.impl.DMNRuntimeImpl;
import org.kie.dmn.feel.codegen.feel11.CompiledFEELExpression;
import org.kie.dmn.feel.lang.EvaluationContext;
import org.kie.dmn.feel.lang.impl.EvaluationContextImpl;
import org.kie.dmn.feel.lang.impl.FEELImpl;
import org.kie.dmn.model.v1_1.DecisionTable;
import temp.kiedmn.DMNUnit;

import static org.kie.dmn.core.compiler.DMNEvaluatorCompilerImpl.getParameters;

public abstract class AbstractModelEvaluator implements DMNExpressionEvaluator {
    protected final KieBase kieBase;

    private List<String> paramNames;
    private List<CompiledFEELExpression> compiledExprs;
    private String dtName;
    private String nodeName;

    protected AbstractModelEvaluator() {
        Model model = getRules().stream().reduce( new ModelImpl(), ModelImpl::addRule, ( m1, m2) -> { throw new UnsupportedOperationException(); } );
        kieBase = KieBaseBuilder.createKieBaseFromModel( model );
    }

    protected abstract List<Rule> getRules();
    protected abstract DMNUnit getDMNUnit();

    @Override
    public EvaluatorResult evaluate( DMNRuntimeEventManager eventManager, DMNResult dmnResult ) {
        DMNRuntimeEventManagerUtils.fireBeforeEvaluateDecisionTable( eventManager, nodeName, dtName, dmnResult );

        try {
            RuleUnitExecutor executor = RuleUnitExecutor.create().bind( kieBase );

            DMNUnit unit = getDMNUnit()
                    .setEvalCtx( createEvaluationContext( eventManager, dmnResult ) )
                    .setCompiledExprs( compiledExprs );

            Object result = unit.execute( executor ).getResult();

            return new EvaluatorResultImpl( result, EvaluatorResult.ResultType.SUCCESS );
        } finally {
            DMNRuntimeEventManagerUtils.fireAfterEvaluateDecisionTable( eventManager, nodeName, dtName, dmnResult, null, null); // (r != null ? r.matchedRules : null), (r != null ? r.fired : null) );
        }
    }

    private EvaluationContext createEvaluationContext( DMNRuntimeEventManager eventManager, DMNResult dmnResult ) {
        final FEELImpl feel = ( FEELImpl ) FeelUtil.feel;
        final List<FEELEvent> events = new ArrayList<>();
        DMNResultImpl result = (DMNResultImpl) dmnResult;

        Object[] params = new Object[paramNames.size()];
        EvaluationContextImpl ctx = feel.newEvaluationContext( Arrays.asList(events::add), Collections.emptyMap());
        ctx.setPerformRuntimeTypeCheck(((DMNRuntimeImpl ) eventManager.getRuntime()).performRuntimeTypeCheck(result.getModel()));

//            ctx.enterFrame();
        // need to set the values for in context variables...
        for ( Map.Entry<String,Object> entry : result.getContext().getAll().entrySet() ) {
            ctx.setValue( entry.getKey(), entry.getValue() );
        }
        for ( int i = 0; i < params.length; i++ ) {
            EvaluationContextImpl evalCtx = feel.newEvaluationContext(Arrays.asList(events::add), Collections.emptyMap());
            evalCtx.setValues(result.getContext().getAll());
            params[i] = feel.evaluate( paramNames.get( i ), evalCtx );
            ctx.setValue( paramNames.get( i ), params[i] );
        }

//            ctx.exitFrame();
        return ctx;
    }

    protected AbstractModelEvaluator initParameters( DMNModelImpl model, DMNBaseNode node, DecisionTable dt, String dtName, List<CompiledFEELExpression> compiledExprs) {
        this.paramNames = getParameters( model, node, dt );
        this.dtName = dtName;
        this.nodeName = node.getName();
        this.compiledExprs = compiledExprs;
        return this;
    }
}