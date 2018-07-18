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

public class SuggestedLightSources {

    private static final String PACKAGE_NAME = "org.drools.dmnem.poc20171103";

    public static final String EXEC_MODEL =
            "package " + PACKAGE_NAME + ";\n" +
            "\n" +
            "import java.util.List;\n" +
            "import org.drools.model.Rule;\n" +
            "import org.drools.modelcompiler.dsl.pattern.D;\n" +
            "import " + PACKAGE_NAME + ".SuggestedLightUnaryTests.*;\n" +
            "\n" +
            "import static java.util.Arrays.asList;\n" +
            "import static " + UnaryTestsUtil.class.getCanonicalName() + ".or;\n" +
            "\n" +
            "public class SuggestedLightExecModel {\n" +
            "\n" +
            "    public static List<Rule> getRules() {\n" +
            "        return asList( rule_SuggestedLightDTUnit_451(), rule_SuggestedLightDTUnit_452() , rule_SuggestedLightDTUnit_453() );\n" +
            "    }\n" +
            "    /**\n" +
            "     * Rule name: SuggestedLightDTUnit-1\n" +
            "     */\n" +
            "    public static Rule rule_SuggestedLightDTUnit_451() {\n" +
            "        final org.drools.model.UnitData<java.lang.Object> var_result = D.unitData(java.lang.Object.class,\n" +
            "                \"result\");\n" +
            "        final org.drools.model.UnitData<org.kie.api.runtime.rule.DataSource> var_suggestedLightInput2 = D.unitData(org.kie.api.runtime.rule.DataSource.class,\n" +
            "                \"input1\");\n" +
            "        final org.drools.model.UnitData<org.kie.api.runtime.rule.DataSource> var_suggestedLightInput1 = D.unitData(org.kie.api.runtime.rule.DataSource.class,\n" +
            "                \"input0\");\n" +
            "        final org.drools.model.UnitData<org.kie.api.runtime.rule.DataSource> var_suggestedLightOutput1 = D.unitData(org.kie.api.runtime.rule.DataSource.class,\n" +
            "                \"output0\");\n" +
            "        final org.drools.model.UnitData<java.lang.Object> var_suggestedLight = D.unitData(java.lang.Object.class,\n" +
            "                \"suggestedLight\");\n" +
            "        final org.drools.model.Variable<java.lang.Object> var_$pattern_Boolean$1$ = D.declarationOf(java.lang.Object.class,\n" +
            "                \"$pattern_Boolean$1$\",\n" +
            "                var_suggestedLightInput1);\n" +
            "        org.drools.model.Rule rule = D.rule(\"org.drools.dmnem.poc20171103\",\n" +
            "                \"SuggestedLightDTUnit-1\").unit(org.drools.dmnem.poc20171103.SuggestedLightDTUnit.class)\n" +
            "                .build(D.pattern(var_$pattern_Boolean$1$).expr(\"$expr$1$\",\n" +
            "                        (_this) -> or( new Suggested_32lightr0c0().getUnaryTests() ).apply( null, _this )),\n" +
            "                        D.on(var_suggestedLightOutput1).execute((suggestedLightOutput1) -> {\n" +
            "                            System.out.println(\"SuggestedLight ROW 1\");\n" +
            "                            suggestedLightOutput1.insert(\"OFF\");\n" +
            "                        }));\n" +
            "        return rule;\n" +
            "    }\n" +
            "\n" +
            "    /**\n" +
            "     * Rule name: SuggestedLightDTUnit-2\n" +
            "     */\n" +
            "    public static Rule rule_SuggestedLightDTUnit_452() {\n" +
            "        final org.drools.model.UnitData<java.lang.Object> var_result = D.unitData(java.lang.Object.class,\n" +
            "                \"result\");\n" +
            "        final org.drools.model.UnitData<org.kie.api.runtime.rule.DataSource> var_suggestedLightInput2 = D.unitData(org.kie.api.runtime.rule.DataSource.class,\n" +
            "                \"input1\");\n" +
            "        final org.drools.model.UnitData<org.kie.api.runtime.rule.DataSource> var_suggestedLightInput1 = D.unitData(org.kie.api.runtime.rule.DataSource.class,\n" +
            "                \"input0\");\n" +
            "        final org.drools.model.UnitData<org.kie.api.runtime.rule.DataSource> var_suggestedLightOutput1 = D.unitData(org.kie.api.runtime.rule.DataSource.class,\n" +
            "                \"output0\");\n" +
            "        final org.drools.model.UnitData<java.lang.Object> var_suggestedLight = D.unitData(java.lang.Object.class,\n" +
            "                \"suggestedLight\");\n" +
            "        final org.drools.model.Variable<java.lang.Object> var_$pattern_Boolean$2$ = D.declarationOf(java.lang.Object.class,\n" +
            "                \"$pattern_Boolean$2$\",\n" +
            "                var_suggestedLightInput1);\n" +
            "        final org.drools.model.Variable<java.lang.Object> var_$pattern_String$1$ = D.declarationOf(java.lang.Object.class,\n" +
            "                \"$pattern_String$1$\",\n" +
            "                var_suggestedLightInput2);\n" +
            "        org.drools.model.Rule rule = D.rule(\"org.drools.dmnem.poc20171103\",\n" +
            "                \"SuggestedLightDTUnit-2\").unit(org.drools.dmnem.poc20171103.SuggestedLightDTUnit.class)\n" +
            "                .build(D.pattern(var_$pattern_Boolean$2$).expr(\"$expr$2$\",\n" +
            "                        (_this) -> or( new Suggested_32lightr1c0().getUnaryTests() ).apply( null, _this )),\n" +
            "                        D.pattern(var_$pattern_String$1$).expr(\"$expr$3$\",\n" +
            "                                (_this) -> or( new Suggested_32lightr1c1().getUnaryTests() ).apply( null, _this )),\n" +
            "                        D.on(var_suggestedLightOutput1).execute((suggestedLightOutput1) -> {\n" +
            "                            System.out.println(\"SuggestedLight ROW 2\");\n" +
            "                            suggestedLightOutput1.insert(\"OFF\");\n" +
            "                        }));\n" +
            "        return rule;\n" +
            "    }\n" +
            "\n" +
            "    /**\n" +
            "     * Rule name: SuggestedLightDTUnit-3\n" +
            "     */\n" +
            "    public static Rule rule_SuggestedLightDTUnit_453() {\n" +
            "        final org.drools.model.UnitData<java.lang.Object> var_result = D.unitData(java.lang.Object.class,\n" +
            "                \"result\");\n" +
            "        final org.drools.model.UnitData<org.kie.api.runtime.rule.DataSource> var_suggestedLightInput2 = D.unitData(org.kie.api.runtime.rule.DataSource.class,\n" +
            "                \"input1\");\n" +
            "        final org.drools.model.UnitData<org.kie.api.runtime.rule.DataSource> var_suggestedLightInput1 = D.unitData(org.kie.api.runtime.rule.DataSource.class,\n" +
            "                \"input0\");\n" +
            "        final org.drools.model.UnitData<org.kie.api.runtime.rule.DataSource> var_suggestedLightOutput1 = D.unitData(org.kie.api.runtime.rule.DataSource.class,\n" +
            "                \"output0\");\n" +
            "        final org.drools.model.UnitData<java.lang.Object> var_suggestedLight = D.unitData(java.lang.Object.class,\n" +
            "                \"suggestedLight\");\n" +
            "        final org.drools.model.Variable<java.lang.Object> var_$pattern_Boolean$3$ = D.declarationOf(java.lang.Object.class,\n" +
            "                \"$pattern_Boolean$3$\",\n" +
            "                var_suggestedLightInput1);\n" +
            "        final org.drools.model.Variable<java.lang.Object> var_$pattern_String$2$ = D.declarationOf(java.lang.Object.class,\n" +
            "                \"$pattern_String$2$\",\n" +
            "                var_suggestedLightInput2);\n" +
            "        org.drools.model.Rule rule = D.rule(\"org.drools.dmnem.poc20171103\",\n" +
            "                \"SuggestedLightDTUnit-3\").unit(org.drools.dmnem.poc20171103.SuggestedLightDTUnit.class)\n" +
            "                .build(D.pattern(var_$pattern_Boolean$3$).expr(\"$expr$2$\",\n" +
            "                        (_this) -> or( new Suggested_32lightr2c0().getUnaryTests() ).apply( null, _this )),\n" +
            "                        D.pattern(var_$pattern_String$2$).expr(\"$expr$4$\",\n" +
            "                                (_this) -> or( new Suggested_32lightr2c1().getUnaryTests() ).apply( null, _this )),\n" +
            "                        D.on(var_suggestedLightOutput1).execute((suggestedLightOutput1) -> {\n" +
            "                            System.out.println(\"SuggestedLight ROW 3\");\n" +
            "                            suggestedLightOutput1.insert(\"ON\");\n" +
            "                        }));\n" +
            "        return rule;\n" +
            "    }\n" +
            "}\n";


}
