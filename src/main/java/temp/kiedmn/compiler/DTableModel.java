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

package temp.kiedmn.compiler;

import java.util.List;

import org.kie.dmn.core.compiler.DMNCompilerContext;
import org.kie.dmn.core.impl.BaseDMNTypeImpl;
import org.kie.dmn.feel.codegen.feel11.CodegenStringUtil;
import org.kie.dmn.feel.codegen.feel11.CompiledFEELExpression;
import org.kie.dmn.feel.lang.CompilerContext;
import org.kie.dmn.model.v1_1.DecisionRule;
import org.kie.dmn.model.v1_1.DecisionTable;
import org.kie.dmn.model.v1_1.InputClause;
import org.kie.dmn.model.v1_1.LiteralExpression;
import org.kie.dmn.model.v1_1.UnaryTests;

import static java.util.stream.Collectors.toList;

import static temp.kiedmn.compiler.FeelUtil.asFeelExpression;

public class DTableModel {
    private final String namespace;
    private final String tableName;
    private final List<DColumnModel> inputs;
    private final int outputsNr;
    private final List<DRowModel> rows;

    public DTableModel(String namespace, String dtName, DecisionTable dt) {
        this.namespace = CodegenStringUtil.escapeIdentifier( namespace );
        this.tableName = CodegenStringUtil.escapeIdentifier( dtName );
        this.inputs = dt.getInput().stream()
                .map( InputClause::getInputExpression )
                .map( DColumnModel::new ).collect( toList() );
        this.outputsNr = dt.getOutput().size();
        this.rows = dt.getRule().stream().map( DRowModel::new ).collect( toList() );
    }

    public String getNamespace() {
        return namespace;
    }

    public String getTableName() {
        return tableName;
    }

    public List<DColumnModel> getInputs() {
        return inputs;
    }

    public List<DRowModel> getRows() {
        return rows;
    }

    public int getOutputsNr() {
        return outputsNr;
    }

    public List<CompiledFEELExpression> getFeelExpressionsForInputs( DMNCompilerContext ctx) {
        CompilerContext feelctx = FeelUtil.feel.newCompilerContext();
        ctx.getVariables().forEach( (k, v) -> feelctx.addInputVariableType( k, ((BaseDMNTypeImpl ) v).getFeelType() ) );
        return inputs.stream().map( DColumnModel::getName ).map( n -> asFeelExpression( n, feelctx ) ).collect( toList() );
    }

    public static class DRowModel {

        private final List<String> inputs;
        private final List<String> outputs;

        DRowModel(DecisionRule dr) {
            this.inputs = dr.getInputEntry().stream().map( UnaryTests::getText ).collect( toList() );
            this.outputs = dr.getOutputEntry().stream().map( LiteralExpression::getText ).collect( toList() );
        }

        public List<CompiledFEELExpression> getOutputsAsFeelExpressions() {
            return outputs.stream().map( FeelUtil::asFeelExpression ).collect( toList() );
        }

        public List<String> getInputs() {
            return inputs;
        }

        public List<String> getOutputs() {
            return outputs;
        }
    }

    public static class DColumnModel {
        private final String name;
        private final String javaName;
        private final String type;

        DColumnModel (LiteralExpression expr) {
            this.name = expr.getText();
            this.javaName = CodegenStringUtil.escapeIdentifier( name );
            this.type = expr.getTypeRef() != null ? expr.getTypeRef().getLocalPart() : null;
        }

        public Class<?> getTypeClass() {
            if (type != null) {
                if (type.equalsIgnoreCase( "string" )) {
                    return String.class;
                }
                if (type.equalsIgnoreCase( "boolean" )) {
                    return Boolean.class;
                }
            }
            return Object.class;
        }

        public String getName() {
            return name;
        }
    }
}
