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

import org.kie.dmn.core.api.DMNExpressionEvaluator;
import org.kie.dmn.core.ast.DMNBaseNode;
import org.kie.dmn.core.compiler.DMNCompilerContext;
import org.kie.dmn.core.compiler.DMNCompilerImpl;
import org.kie.dmn.core.compiler.DMNEvaluatorCompilerImpl;
import org.kie.dmn.core.compiler.DMNFEELHelper;
import org.kie.dmn.core.impl.DMNModelImpl;
import org.kie.dmn.feel.codegen.feel11.CompiledFEELExpression;
import org.kie.dmn.model.v1_1.DecisionTable;

public class ExecModelDMNEvaluatorCompiler extends DMNEvaluatorCompilerImpl {
    public ExecModelDMNEvaluatorCompiler( DMNCompilerImpl compiler, DMNFEELHelper feel ) {
        super( compiler, feel );
    }

    @Override
    protected DMNExpressionEvaluator compileDecisionTable( DMNCompilerContext ctx, DMNModelImpl model, DMNBaseNode node, String dtName, DecisionTable dt ) {
//        if (true) return super.compileDecisionTable( ctx, model, node, dtName, dt );
        DTableModel dTableModel = new DTableModel( model.getNamespace(), dtName, dt );
//        dTableModel.getUnaryTests();
        List<CompiledFEELExpression> compiledExprs = dTableModel.getFeelExpressionsForInputs( ctx );
        switch (dtName) {
            case "Suggested light" : return new SuggestedLightEvaluator().initParameters(model, node, dt, dtName, compiledExprs);
            case "Suggested Blinds" : return new SuggestedBlindsEvaluator().initParameters(model, node, dt, dtName, compiledExprs);
            case "sunlight" : return new SunlightEvaluator().initParameters(model, node, dt, dtName, compiledExprs);
        }
        throw new UnsupportedOperationException( "Unknown decision table: " + dtName );
    }

}
