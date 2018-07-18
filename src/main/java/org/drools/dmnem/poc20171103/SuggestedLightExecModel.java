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

import org.drools.modelcompiler.dsl.pattern.D;
import org.drools.dmnem.poc20171103.SuggestedLightUnaryTests.*;

import static org.drools.dmnem.poc20171103.UnaryTestsUtil.or;

public class SuggestedLightExecModel {

    /**
     * Rule name: SuggestedLightDTUnit-1
     */
    public static org.drools.model.Rule rule_SuggestedLightDTUnit_451() {
        final org.drools.model.UnitData<java.lang.Object> var_result = D.unitData(java.lang.Object.class,
                "result");
        final org.drools.model.UnitData<org.kie.api.runtime.rule.DataSource> var_suggestedLightInput2 = D.unitData(org.kie.api.runtime.rule.DataSource.class,
                "input1");
        final org.drools.model.UnitData<org.kie.api.runtime.rule.DataSource> var_suggestedLightInput1 = D.unitData(org.kie.api.runtime.rule.DataSource.class,
                "input0");
        final org.drools.model.UnitData<org.kie.api.runtime.rule.DataSource> var_suggestedLightOutput1 = D.unitData(org.kie.api.runtime.rule.DataSource.class,
                "output0");
        final org.drools.model.UnitData<java.lang.Object> var_suggestedLight = D.unitData(java.lang.Object.class,
                "suggestedLight");
        final org.drools.model.Variable<java.lang.Object> var_$pattern_Boolean$1$ = D.declarationOf(java.lang.Object.class,
                "$pattern_Boolean$1$",
                var_suggestedLightInput1);
        org.drools.model.Rule rule = D.rule("org.drools.dmnem.poc20171103",
                "SuggestedLightDTUnit-1").unit(org.drools.dmnem.poc20171103.SuggestedLightDTUnit.class)
                .build(D.pattern(var_$pattern_Boolean$1$).expr("$expr$1$",
                        (_this) -> or( new Suggested_32lightr0c0().getUnaryTests() ).apply( null, _this )),
                        D.on(var_suggestedLightOutput1).execute((suggestedLightOutput1) -> {
                            System.out.println("SuggestedLight ROW 1");
                            suggestedLightOutput1.insert("OFF");
                        }));
        return rule;
    }

    /**
     * Rule name: SuggestedLightDTUnit-2
     */
    public static org.drools.model.Rule rule_SuggestedLightDTUnit_452() {
        final org.drools.model.UnitData<java.lang.Object> var_result = D.unitData(java.lang.Object.class,
                "result");
        final org.drools.model.UnitData<org.kie.api.runtime.rule.DataSource> var_suggestedLightInput2 = D.unitData(org.kie.api.runtime.rule.DataSource.class,
                "input1");
        final org.drools.model.UnitData<org.kie.api.runtime.rule.DataSource> var_suggestedLightInput1 = D.unitData(org.kie.api.runtime.rule.DataSource.class,
                "input0");
        final org.drools.model.UnitData<org.kie.api.runtime.rule.DataSource> var_suggestedLightOutput1 = D.unitData(org.kie.api.runtime.rule.DataSource.class,
                "output0");
        final org.drools.model.UnitData<java.lang.Object> var_suggestedLight = D.unitData(java.lang.Object.class,
                "suggestedLight");
        final org.drools.model.Variable<java.lang.Object> var_$pattern_Boolean$2$ = D.declarationOf(java.lang.Object.class,
                "$pattern_Boolean$2$",
                var_suggestedLightInput1);
        final org.drools.model.Variable<java.lang.Object> var_$pattern_String$1$ = D.declarationOf(java.lang.Object.class,
                "$pattern_String$1$",
                var_suggestedLightInput2);
        org.drools.model.Rule rule = D.rule("org.drools.dmnem.poc20171103",
                "SuggestedLightDTUnit-2").unit(org.drools.dmnem.poc20171103.SuggestedLightDTUnit.class)
                .build(D.pattern(var_$pattern_Boolean$2$).expr("$expr$2$",
                        (_this) -> or( new Suggested_32lightr1c0().getUnaryTests() ).apply( null, _this )),
                        D.pattern(var_$pattern_String$1$).expr("$expr$3$",
                                (_this) -> or( new Suggested_32lightr1c1().getUnaryTests() ).apply( null, _this )),
                        D.on(var_suggestedLightOutput1).execute((suggestedLightOutput1) -> {
                            System.out.println("SuggestedLight ROW 2");
                            suggestedLightOutput1.insert("OFF");
                        }));
        return rule;
    }

    /**
     * Rule name: SuggestedLightDTUnit-3
     */
    public static org.drools.model.Rule rule_SuggestedLightDTUnit_453() {
        final org.drools.model.UnitData<java.lang.Object> var_result = D.unitData(java.lang.Object.class,
                "result");
        final org.drools.model.UnitData<org.kie.api.runtime.rule.DataSource> var_suggestedLightInput2 = D.unitData(org.kie.api.runtime.rule.DataSource.class,
                "input1");
        final org.drools.model.UnitData<org.kie.api.runtime.rule.DataSource> var_suggestedLightInput1 = D.unitData(org.kie.api.runtime.rule.DataSource.class,
                "input0");
        final org.drools.model.UnitData<org.kie.api.runtime.rule.DataSource> var_suggestedLightOutput1 = D.unitData(org.kie.api.runtime.rule.DataSource.class,
                "output0");
        final org.drools.model.UnitData<java.lang.Object> var_suggestedLight = D.unitData(java.lang.Object.class,
                "suggestedLight");
        final org.drools.model.Variable<java.lang.Object> var_$pattern_Boolean$3$ = D.declarationOf(java.lang.Object.class,
                "$pattern_Boolean$3$",
                var_suggestedLightInput1);
        final org.drools.model.Variable<java.lang.Object> var_$pattern_String$2$ = D.declarationOf(java.lang.Object.class,
                "$pattern_String$2$",
                var_suggestedLightInput2);
        org.drools.model.Rule rule = D.rule("org.drools.dmnem.poc20171103",
                "SuggestedLightDTUnit-3").unit(org.drools.dmnem.poc20171103.SuggestedLightDTUnit.class)
                .build(D.pattern(var_$pattern_Boolean$3$).expr("$expr$2$",
                        (_this) -> or( new Suggested_32lightr2c0().getUnaryTests() ).apply( null, _this )),
                        D.pattern(var_$pattern_String$2$).expr("$expr$4$",
                                (_this) -> or( new Suggested_32lightr2c1().getUnaryTests() ).apply( null, _this )),
                        D.on(var_suggestedLightOutput1).execute((suggestedLightOutput1) -> {
                            System.out.println("SuggestedLight ROW 3");
                            suggestedLightOutput1.insert("ON");
                        }));
        return rule;
    }
}
