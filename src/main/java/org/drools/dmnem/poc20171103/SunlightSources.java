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

public class SunlightSources {

    private static final String PACKAGE_NAME = "org.drools.dmnem.poc20171103";

    public static final String EXEC_MODEL =
            "package " + PACKAGE_NAME + ";\n" +
            "\n" +
            "import java.util.List;\n" +
            "import org.kie.api.runtime.rule.DataSource;\n" +
            "import org.drools.model.*;\n" +
            "import org.drools.modelcompiler.dsl.pattern.D;\n" +
            "import " + PACKAGE_NAME + ".SunlightUnaryTests.*;\n" +
            "\n" +
            "import static java.util.Arrays.asList;\n" +
            "import static " + UnaryTestsUtil.class.getCanonicalName() + ".or;\n" +
            "\n" +
            "public class SunlightExecModel {\n" +
            "\n" +
            "    public static List<Rule> getRules() {\n" +
            "        return asList( rule_SunlightDTUnit__1(), rule_SunlightDTUnit__2() , rule_SunlightDTUnit__3() );\n" +
            "    }\n" +
            "    /**\n" +
            "     * Rule name: SunlightDTUnit_1\n" +
            "     */\n" +
            "    public static Rule rule_SunlightDTUnit__1() {\n" +
            "        final UnitData<DataSource> var_output0 = D.unitData(DataSource.class, \"output0\");\n" +
            "        final UnitData<DataSource> var_input0 = D.unitData(DataSource.class, \"input0\");\n" +
            "        final Variable<Object> var_$pattern_Integer$1$ = D.declarationOf(Object.class, \"$pattern_Integer$1$\", var_input0);\n" +
            "        Rule rule = D.rule(\"org.drools.dmnem.poc20171103\",\n" +
            "                \"SunlightDTUnit_1\").unit(org.drools.dmnem.poc20171103.SunlightDTUnit.class)\n" +
            "                .build(D.pattern(var_$pattern_Integer$1$).expr(\"$expr$5$\",\n" +
            "                        (_this) -> or( new sunlightr0c0().getUnaryTests() ).apply( null, _this )),\n" +
            "                        D.on(var_output0).execute((sunlightOutput1) -> {\n" +
            "                            System.out.println(\"sunlight ROW 1\");\n" +
            "                            sunlightOutput1.insert(\"night time\");\n" +
            "                        }));\n" +
            "        return rule;\n" +
            "    }\n" +
            "\n" +
            "    /**\n" +
            "     * Rule name: SunlightDTUnit_2\n" +
            "     */\n" +
            "    public static Rule rule_SunlightDTUnit__2() {\n" +
            "        final UnitData<DataSource> var_output0 = D.unitData(DataSource.class, \"output0\");\n" +
            "        final UnitData<DataSource> var_input0 = D.unitData(DataSource.class, \"input0\");\n" +
            "        final Variable<Object> var_$pattern_Integer$2$ = D.declarationOf(Object.class, \"$pattern_Integer$2$\", var_input0);\n" +
            "        Rule rule = D.rule(\"org.drools.dmnem.poc20171103\",\n" +
            "                \"SunlightDTUnit_2\").unit(org.drools.dmnem.poc20171103.SunlightDTUnit.class)\n" +
            "                .build(D.pattern(var_$pattern_Integer$2$).expr(\"$expr$6$\",\n" +
            "                        (_this) -> or( new sunlightr1c0().getUnaryTests() ).apply( null, _this )),\n" +
            "                        D.on(var_output0).execute((sunlightOutput1) -> {\n" +
            "                            System.out.println(\"sunlight ROW 2\");\n" +
            "                            sunlightOutput1.insert(\"sunlight\");\n" +
            "                        }));\n" +
            "        return rule;\n" +
            "    }\n" +
            "\n" +
            "    /**\n" +
            "     * Rule name: SunlightDTUnit_3\n" +
            "     */\n" +
            "    public static Rule rule_SunlightDTUnit__3() {\n" +
            "        final UnitData<DataSource> var_output0 = D.unitData(DataSource.class, \"output0\");\n" +
            "        final UnitData<DataSource> var_input0 = D.unitData(DataSource.class, \"input0\");\n" +
            "        final Variable<Object> var_$pattern_Integer$3$ = D.declarationOf(Object.class, \"$pattern_Integer$3$\", var_input0);\n" +
            "        Rule rule = D.rule(\"org.drools.dmnem.poc20171103\",\n" +
            "                \"SunlightDTUnit_3\").unit(org.drools.dmnem.poc20171103.SunlightDTUnit.class)\n" +
            "                .build(D.pattern(var_$pattern_Integer$3$).expr(\"$expr$7$\",\n" +
            "                        (_this) -> or( new sunlightr2c0().getUnaryTests() ).apply( null, _this )),\n" +
            "                        D.on(var_output0).execute((sunlightOutput1) -> {\n" +
            "                            System.out.println(\"sunlight ROW 3\");\n" +
            "                            sunlightOutput1.insert(\"night time\");\n" +
            "                        }));\n" +
            "        return rule;\n" +
            "    }\n" +
            "}\n";

}
