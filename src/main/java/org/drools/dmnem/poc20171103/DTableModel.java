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
import java.util.List;

import org.kie.dmn.core.compiler.DMNCompilerContext;
import org.kie.dmn.core.impl.BaseDMNTypeImpl;
import org.kie.dmn.feel.codegen.feel11.CodegenStringUtil;
import org.kie.dmn.feel.codegen.feel11.CompiledFEELExpression;
import org.kie.dmn.feel.lang.CompilerContext;
import org.kie.dmn.feel.runtime.UnaryTest;
import org.kie.dmn.model.v1_1.DecisionRule;
import org.kie.dmn.model.v1_1.DecisionTable;
import org.kie.dmn.model.v1_1.InputClause;
import org.kie.dmn.model.v1_1.LiteralExpression;
import org.kie.dmn.model.v1_1.UnaryTests;

import static java.util.stream.Collectors.toList;

import static org.drools.dmnem.poc20171103.FeelUtil.asFeelExpression;

public class DTableModel {
    private final String namespace;
    private final String dtName;
    private final List<DColumnModel> inputs;
    private final int outputsNr;
    private final List<DRowModel> rows;

    public DTableModel(String namespace, String dtName, DecisionTable dt) {
        this.namespace = CodegenStringUtil.escapeIdentifier( namespace );
        this.dtName = CodegenStringUtil.escapeIdentifier( dtName );
        this.inputs = dt.getInput().stream()
                .map( InputClause::getInputExpression )
                .map( DColumnModel::new ).collect( toList() );
        this.outputsNr = dt.getOutput().size();
        this.rows = dt.getRule().stream().map( DRowModel::new ).collect( toList() );
    }

    public List<CompiledFEELExpression> getFeelExpressionsForInputs(DMNCompilerContext ctx) {
        CompilerContext feelctx = FeelUtil.feel.newCompilerContext();
        ctx.getVariables().forEach( (k, v) -> feelctx.addInputVariableType( k, ((BaseDMNTypeImpl ) v).getFeelType() ) );
        return inputs.stream().map( DColumnModel::getName ).map( n -> asFeelExpression( n, feelctx ) ).collect( toList() );
    }

    public List<List<UnaryTest>> getUnaryTests() {
        List<List<UnaryTest>> result = new ArrayList<>();
        for (int i = 0; i < rows.size(); i++) {
            List<UnaryTest> list = new ArrayList<>();
            DRowModel row = rows.get(i);
            for (int j = 0; j < row.inputs.size(); j++) {
                String input = row.inputs.get(j);
                list.add( FeelUtil.asFeelUnaryTest( namespace, dtName + "r" + i + "c" + j, input ) );
            }
            result.add(list);
        }
        return result;
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

    public static final String dtClass =
            "package org.drools.dmnem.poc20171103;\n" +
            "\n" +
            "import org.kie.api.runtime.rule.DataSource;\n" +
            "import org.kie.dmn.api.core.DMNContext;\n" +
            "import temp.kiedmn.DMNUnit;\n" +
            "\n" +
            "public class SuggestedLightDTUnit extends DMNUnit {\n" +
            "\n" +
            "    private Boolean isPresent;\n" +
            "    private String sunlight;\n" +
            "\n" +
            "    private DataSource<Boolean> suggestedLightInput1 = DataSource.create();\n" +
            "    private DataSource<String> suggestedLightInput2 = DataSource.create();\n" +
            "\n" +
            "    private DataSource<String> suggestedLightOutput1 = DataSource.create();\n" +
            "\n" +
            "    private String suggestedLight;\n" +
            "\n" +
            "    public SuggestedLightDTUnit(DMNContext context) {\n" +
            "        super(context);\n" +
            "        this.isPresent = (Boolean) context.get(\"is Present\");\n" +
            "        this.sunlight = (String) context.get(\"sunlight\");\n" +
            "    }\n" +
            "\n" +
            "    public DataSource<Boolean> getSuggestedLightInput1() {\n" +
            "        return suggestedLightInput1;\n" +
            "    }\n" +
            "\n" +
            "    public DataSource<String> getSuggestedLightInput2() {\n" +
            "        return suggestedLightInput2;\n" +
            "    }\n" +
            "\n" +
            "    public DataSource<String> getSuggestedLightOutput1() {\n" +
            "        return suggestedLightOutput1;\n" +
            "    }\n" +
            "\n" +
            "    public String getSuggestedLight() {\n" +
            "        return suggestedLight;\n" +
            "    }\n" +
            "\n" +
            "    @Override\n" +
            "    public void onStart() {\n" +
            "        suggestedLightInput1.insert(isPresent);\n" +
            "        suggestedLightInput2.insert(sunlight);\n" +
            "    }\n" +
            "\n" +
            "    @Override\n" +
            "    public void onEnd() {\n" +
            "        suggestedLight = suggestedLightOutput1.iterator().next();\n" +
            "    }\n" +
            "\n" +
            "    @Override\n" +
            "    public Object getResult() {\n" +
            "        return getSuggestedLight();\n" +
            "    }\n" +
            "\n" +
            "}\n";
}
