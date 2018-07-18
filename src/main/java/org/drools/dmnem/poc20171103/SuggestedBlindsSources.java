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

public class SuggestedBlindsSources {

    private static final String PACKAGE_NAME = "org.drools.dmnem.poc20171103";

    public static final String EXEC_MODEL =
            "package " + PACKAGE_NAME + ";\n" +
            "\n" +
            "import java.util.List;\n" +
            "import org.drools.model.Rule;\n" +
            "import org.drools.modelcompiler.dsl.pattern.D;\n" +
            "import " + PACKAGE_NAME + ".SuggestedBlindsUnaryTests.*;\n" +
            "\n" +
            "import static java.util.Arrays.asList;\n" +
            "import static " + UnaryTestsUtil.class.getCanonicalName() + ".or;\n" +
            "\n" +
            "public class SuggestedBlindsExecModel {\n" +
            "    public static List<Rule> getRules() {\n" +
            "        return asList( rule_SuggestedBlindsDTUnit_451(), rule_SuggestedBlindsDTUnit_452() , rule_SuggestedBlindsDTUnit_453() );\n" +
            "    }\n" +
            "    /**\n" +
            "     * Rule name: SuggestedBlindsDTUnit-1\n" +
            "     */\n" +
            "    public static Rule rule_SuggestedBlindsDTUnit_451() {\n" +
            "        final org.drools.model.UnitData<org.kie.api.runtime.rule.DataSource> var_suggestedBlindsInput1 = D.unitData(org.kie.api.runtime.rule.DataSource.class,\n" +
            "                \"input0\");\n" +
            "        final org.drools.model.UnitData<java.lang.Object> var_result = D.unitData(java.lang.Object.class,\n" +
            "                \"result\");\n" +
            "        final org.drools.model.UnitData<org.kie.api.runtime.rule.DataSource> var_suggestedBlindsInput2 = D.unitData(org.kie.api.runtime.rule.DataSource.class,\n" +
            "                \"input1\");\n" +
            "        final org.drools.model.UnitData<java.lang.Object> var_suggestedBlinds = D.unitData(java.lang.Object.class,\n" +
            "                \"suggestedBlinds\");\n" +
            "        final org.drools.model.UnitData<org.kie.api.runtime.rule.DataSource> var_suggestedBlindsOutput1 = D.unitData(org.kie.api.runtime.rule.DataSource.class,\n" +
            "                \"output0\");\n" +
            "        final org.drools.model.Variable<java.lang.Object> var_$pattern_Boolean$6$ = D.declarationOf(java.lang.Object.class,\n" +
            "                \"$pattern_Boolean$6$\",\n" +
            "                var_suggestedBlindsInput1);\n" +
            "        org.drools.model.Rule rule = D.rule(\"org.drools.dmnem.poc20171103\",\n" +
            "                \"SuggestedBlindsDTUnit-1\").unit(org.drools.dmnem.poc20171103.SuggestedBlindsDTUnit.class)\n" +
            "                .build(D.pattern(var_$pattern_Boolean$6$).expr(\"$expr$1$\",\n" +
            "                        (_this) -> or( new Suggested_32Blindsr0c0().getUnaryTests() ).apply( null, _this )),\n" +
            "                        D.on(var_suggestedBlindsOutput1).execute((suggestedBlindsOutput1) -> {\n" +
            "                            System.out.println(\"SuggestedBlinds ROW 1\");\n" +
            "                            suggestedBlindsOutput1.insert(\"CLOSED\");\n" +
            "                        }));\n" +
            "        return rule;\n" +
            "    }\n" +
            "\n" +
            "    /**\n" +
            "     * Rule name: SuggestedBlindsDTUnit-2\n" +
            "     */\n" +
            "    public static Rule rule_SuggestedBlindsDTUnit_452() {\n" +
            "        final org.drools.model.UnitData<org.kie.api.runtime.rule.DataSource> var_suggestedBlindsInput1 = D.unitData(org.kie.api.runtime.rule.DataSource.class,\n" +
            "                \"input0\");\n" +
            "        final org.drools.model.UnitData<java.lang.Object> var_result = D.unitData(java.lang.Object.class,\n" +
            "                \"result\");\n" +
            "        final org.drools.model.UnitData<org.kie.api.runtime.rule.DataSource> var_suggestedBlindsInput2 = D.unitData(org.kie.api.runtime.rule.DataSource.class,\n" +
            "                \"input1\");\n" +
            "        final org.drools.model.UnitData<java.lang.Object> var_suggestedBlinds = D.unitData(java.lang.Object.class,\n" +
            "                \"suggestedBlinds\");\n" +
            "        final org.drools.model.UnitData<org.kie.api.runtime.rule.DataSource> var_suggestedBlindsOutput1 = D.unitData(org.kie.api.runtime.rule.DataSource.class,\n" +
            "                \"output0\");\n" +
            "        final org.drools.model.Variable<java.lang.Object> var_$pattern_Boolean$7$ = D.declarationOf(java.lang.Object.class,\n" +
            "                \"$pattern_Boolean$7$\",\n" +
            "                var_suggestedBlindsInput1);\n" +
            "        final org.drools.model.Variable<java.lang.Object> var_$pattern_String$8$ = D.declarationOf(java.lang.Object.class,\n" +
            "                \"$pattern_String$8$\",\n" +
            "                var_suggestedBlindsInput2);\n" +
            "        org.drools.model.Rule rule = D.rule(\"org.drools.dmnem.poc20171103\",\n" +
            "                \"SuggestedBlindsDTUnit-2\").unit(org.drools.dmnem.poc20171103.SuggestedBlindsDTUnit.class)\n" +
            "                .build(D.pattern(var_$pattern_Boolean$7$).expr(\"$expr$2$\",\n" +
            "                        (_this) -> or( new Suggested_32Blindsr1c0().getUnaryTests() ).apply( null, _this )),\n" +
            "                        D.pattern(var_$pattern_String$8$).expr(\"$expr$3$\",\n" +
            "                                (_this) -> or( new Suggested_32Blindsr1c1().getUnaryTests() ).apply( null, _this )),\n" +
            "                        D.on(var_suggestedBlindsOutput1).execute((suggestedBlindsOutput1) -> {\n" +
            "                            System.out.println(\"SuggestedBlinds ROW 2\");\n" +
            "                            suggestedBlindsOutput1.insert(\"OPEN\");\n" +
            "                        }));\n" +
            "        return rule;\n" +
            "    }\n" +
            "\n" +
            "    /**\n" +
            "     * Rule name: SuggestedBlindsDTUnit-3\n" +
            "     */\n" +
            "    public static Rule rule_SuggestedBlindsDTUnit_453() {\n" +
            "        final org.drools.model.UnitData<org.kie.api.runtime.rule.DataSource> var_suggestedBlindsInput1 = D.unitData(org.kie.api.runtime.rule.DataSource.class,\n" +
            "                \"input0\");\n" +
            "        final org.drools.model.UnitData<java.lang.Object> var_result = D.unitData(java.lang.Object.class,\n" +
            "                \"result\");\n" +
            "        final org.drools.model.UnitData<org.kie.api.runtime.rule.DataSource> var_suggestedBlindsInput2 = D.unitData(org.kie.api.runtime.rule.DataSource.class,\n" +
            "                \"input1\");\n" +
            "        final org.drools.model.UnitData<java.lang.Object> var_suggestedBlinds = D.unitData(java.lang.Object.class,\n" +
            "                \"suggestedBlinds\");\n" +
            "        final org.drools.model.UnitData<org.kie.api.runtime.rule.DataSource> var_suggestedBlindsOutput1 = D.unitData(org.kie.api.runtime.rule.DataSource.class,\n" +
            "                \"output0\");\n" +
            "        final org.drools.model.Variable<java.lang.Object> var_$pattern_Boolean$8$ = D.declarationOf(java.lang.Object.class,\n" +
            "                \"$pattern_Boolean$8$\",\n" +
            "                var_suggestedBlindsInput1);\n" +
            "        final org.drools.model.Variable<java.lang.Object> var_$pattern_String$9$ = D.declarationOf(java.lang.Object.class,\n" +
            "                \"$pattern_String$9$\",\n" +
            "                var_suggestedBlindsInput2);\n" +
            "        org.drools.model.Rule rule = D.rule(\"org.drools.dmnem.poc20171103\",\n" +
            "                \"SuggestedBlindsDTUnit-3\").unit(org.drools.dmnem.poc20171103.SuggestedBlindsDTUnit.class)\n" +
            "                .build(D.pattern(var_$pattern_Boolean$8$).expr(\"$expr$2$\",\n" +
            "                        (_this) -> or( new Suggested_32Blindsr2c0().getUnaryTests() ).apply( null, _this )),\n" +
            "                        D.pattern(var_$pattern_String$9$).expr(\"$expr$4$\",\n" +
            "                                (_this) -> or( new Suggested_32Blindsr2c1().getUnaryTests() ).apply( null, _this )),\n" +
            "                        D.on(var_suggestedBlindsOutput1).execute((suggestedBlindsOutput1) -> {\n" +
            "                            System.out.println(\"SuggestedBlinds ROW 3\");\n" +
            "                            suggestedBlindsOutput1.insert(\"CLOSED\");\n" +
            "                        }));\n" +
            "        return rule;\n" +
            "    }\n" +
            "}\n";

}
