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

import java.util.List;

import org.drools.model.Model;
import org.drools.model.Rule;
import org.drools.model.impl.ModelImpl;
import org.drools.modelcompiler.builder.KieBaseBuilder;
import org.kie.api.KieBase;
import org.kie.api.runtime.rule.RuleUnitExecutor;
import org.kie.dmn.api.core.DMNResult;
import org.kie.dmn.api.core.event.DMNRuntimeEventManager;
import org.kie.dmn.core.api.DMNExpressionEvaluator;
import org.kie.dmn.core.api.EvaluatorResult;
import org.kie.dmn.core.ast.DMNBaseNode;
import org.kie.dmn.core.ast.EvaluatorResultImpl;
import org.kie.dmn.core.compiler.DMNCompilerContext;
import org.kie.dmn.core.compiler.DMNCompilerImpl;
import org.kie.dmn.core.compiler.DMNEvaluatorCompilerImpl;
import org.kie.dmn.core.compiler.DMNFEELHelper;
import org.kie.dmn.core.impl.DMNModelImpl;
import org.kie.dmn.model.v1_1.DecisionTable;
import temp.kiedmn.DMNUnit;

import static java.util.Arrays.asList;

import static org.drools.dmnem.poc20171103.ExecModelRules.rule_SuggestedBlindsDTUnit_451;
import static org.drools.dmnem.poc20171103.ExecModelRules.rule_SuggestedBlindsDTUnit_452;
import static org.drools.dmnem.poc20171103.ExecModelRules.rule_SuggestedBlindsDTUnit_453;
import static org.drools.dmnem.poc20171103.ExecModelRules.rule_SuggestedLightDTUnit_451;
import static org.drools.dmnem.poc20171103.ExecModelRules.rule_SuggestedLightDTUnit_452;
import static org.drools.dmnem.poc20171103.ExecModelRules.rule_SuggestedLightDTUnit_453;
import static org.drools.dmnem.poc20171103.ExecModelRules.rule_SunlightDTUnit__1;
import static org.drools.dmnem.poc20171103.ExecModelRules.rule_SunlightDTUnit__2;
import static org.drools.dmnem.poc20171103.ExecModelRules.rule_SunlightDTUnit__3;

public class ExecModelDMNEvaluatorCompiler extends DMNEvaluatorCompilerImpl {
    public ExecModelDMNEvaluatorCompiler( DMNCompilerImpl compiler, DMNFEELHelper feel ) {
        super( compiler, feel );
    }

    @Override
    protected DMNExpressionEvaluator compileDecisionTable( DMNCompilerContext ctx, DMNModelImpl model, DMNBaseNode node, String dtName, DecisionTable dt ) {
        switch (dtName) {
            case "Suggested light" : return new SuggestedLightEvaluator();
            case "Suggested Blinds" : return new SuggestedBlindsEvaluator();
            case "sunlight" : return new SunlightEvaluator();
        }
        throw new UnsupportedOperationException( "Unknown decision table: " + dtName );
    }

    public static abstract class AbstractModelEvaluator implements DMNExpressionEvaluator {
        protected final KieBase kieBase;

        protected AbstractModelEvaluator() {
            Model model = getRules().stream().reduce( new ModelImpl(), ModelImpl::addRule, (m1,m2) -> { throw new UnsupportedOperationException(); } );
            kieBase = KieBaseBuilder.createKieBaseFromModel( model );
        }

        protected abstract List<Rule> getRules();
        protected abstract DMNUnit getDMNUnit( DMNResult dmnResult );

        @Override
        public EvaluatorResult evaluate( DMNRuntimeEventManager eventManager, DMNResult dmnResult ) {
            RuleUnitExecutor executor = RuleUnitExecutor.create().bind(kieBase);

            DMNUnit unit = getDMNUnit( dmnResult );
            Object result = unit.execute(executor).getResult();

            return new EvaluatorResultImpl( result, EvaluatorResult.ResultType.SUCCESS );
        }
    }

    public static class SuggestedLightEvaluator extends AbstractModelEvaluator {

        @Override
        protected List<Rule> getRules() {
            return asList( rule_SuggestedLightDTUnit_451(), rule_SuggestedLightDTUnit_452() , rule_SuggestedLightDTUnit_453() );
        }

        @Override
        protected DMNUnit getDMNUnit( DMNResult dmnResult ) {
            return new SuggestedLightDTUnit(dmnResult.getContext());
        }
    }

    public static class SuggestedBlindsEvaluator extends AbstractModelEvaluator {

        @Override
        protected List<Rule> getRules() {
            return asList( rule_SuggestedBlindsDTUnit_451(), rule_SuggestedBlindsDTUnit_452() , rule_SuggestedBlindsDTUnit_453() );
        }

        @Override
        protected DMNUnit getDMNUnit( DMNResult dmnResult ) {
            return new SuggestedBlindsDTUnit(dmnResult.getContext());
        }
    }

    public static class SunlightEvaluator extends AbstractModelEvaluator {

        @Override
        protected List<Rule> getRules() {
            return asList( rule_SunlightDTUnit__1(), rule_SunlightDTUnit__2() , rule_SunlightDTUnit__3() );
        }

        @Override
        protected DMNUnit getDMNUnit( DMNResult dmnResult ) {
            return new SunlightDTUnit(dmnResult.getContext());
        }
    }
}
