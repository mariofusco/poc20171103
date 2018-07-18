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
import org.drools.dmnem.poc20171103.SuggestedBlindsUnaryTests.*;

import static org.drools.dmnem.poc20171103.UnaryTestsUtil.or;

public class SuggestedBlindsExecModel {
    /**
     * Rule name: SuggestedBlindsDTUnit-1
     */
    public static org.drools.model.Rule rule_SuggestedBlindsDTUnit_451() {
        final org.drools.model.UnitData<org.kie.api.runtime.rule.DataSource> var_suggestedBlindsInput1 = D.unitData(org.kie.api.runtime.rule.DataSource.class,
                "input0");
        final org.drools.model.UnitData<java.lang.Object> var_result = D.unitData(java.lang.Object.class,
                "result");
        final org.drools.model.UnitData<org.kie.api.runtime.rule.DataSource> var_suggestedBlindsInput2 = D.unitData(org.kie.api.runtime.rule.DataSource.class,
                "input1");
        final org.drools.model.UnitData<java.lang.Object> var_suggestedBlinds = D.unitData(java.lang.Object.class,
                "suggestedBlinds");
        final org.drools.model.UnitData<org.kie.api.runtime.rule.DataSource> var_suggestedBlindsOutput1 = D.unitData(org.kie.api.runtime.rule.DataSource.class,
                "output0");
        final org.drools.model.Variable<java.lang.Object> var_$pattern_Boolean$6$ = D.declarationOf(java.lang.Object.class,
                "$pattern_Boolean$6$",
                var_suggestedBlindsInput1);
        org.drools.model.Rule rule = D.rule("org.drools.dmnem.poc20171103",
                "SuggestedBlindsDTUnit-1").unit(org.drools.dmnem.poc20171103.SuggestedBlindsDTUnit.class)
                .build(D.pattern(var_$pattern_Boolean$6$).expr("$expr$1$",
                        (_this) -> or( new Suggested_32Blindsr0c0().getUnaryTests() ).apply( null, _this )),
                        D.on(var_suggestedBlindsOutput1).execute((suggestedBlindsOutput1) -> {
                            System.out.println("SuggestedBlinds ROW 1");
                            suggestedBlindsOutput1.insert("CLOSED");
                        }));
        return rule;
    }

    /**
     * Rule name: SuggestedBlindsDTUnit-2
     */
    public static org.drools.model.Rule rule_SuggestedBlindsDTUnit_452() {
        final org.drools.model.UnitData<org.kie.api.runtime.rule.DataSource> var_suggestedBlindsInput1 = D.unitData(org.kie.api.runtime.rule.DataSource.class,
                "input0");
        final org.drools.model.UnitData<java.lang.Object> var_result = D.unitData(java.lang.Object.class,
                "result");
        final org.drools.model.UnitData<org.kie.api.runtime.rule.DataSource> var_suggestedBlindsInput2 = D.unitData(org.kie.api.runtime.rule.DataSource.class,
                "input1");
        final org.drools.model.UnitData<java.lang.Object> var_suggestedBlinds = D.unitData(java.lang.Object.class,
                "suggestedBlinds");
        final org.drools.model.UnitData<org.kie.api.runtime.rule.DataSource> var_suggestedBlindsOutput1 = D.unitData(org.kie.api.runtime.rule.DataSource.class,
                "output0");
        final org.drools.model.Variable<java.lang.Object> var_$pattern_Boolean$7$ = D.declarationOf(java.lang.Object.class,
                "$pattern_Boolean$7$",
                var_suggestedBlindsInput1);
        final org.drools.model.Variable<java.lang.Object> var_$pattern_String$8$ = D.declarationOf(java.lang.Object.class,
                "$pattern_String$8$",
                var_suggestedBlindsInput2);
        org.drools.model.Rule rule = D.rule("org.drools.dmnem.poc20171103",
                "SuggestedBlindsDTUnit-2").unit(org.drools.dmnem.poc20171103.SuggestedBlindsDTUnit.class)
                .build(D.pattern(var_$pattern_Boolean$7$).expr("$expr$2$",
                        (_this) -> or( new Suggested_32Blindsr1c0().getUnaryTests() ).apply( null, _this )),
                        D.pattern(var_$pattern_String$8$).expr("$expr$3$",
                                (_this) -> or( new Suggested_32Blindsr1c1().getUnaryTests() ).apply( null, _this )),
                        D.on(var_suggestedBlindsOutput1).execute((suggestedBlindsOutput1) -> {
                            System.out.println("SuggestedBlinds ROW 2");
                            suggestedBlindsOutput1.insert("OPEN");
                        }));
        return rule;
    }

    /**
     * Rule name: SuggestedBlindsDTUnit-3
     */
    public static org.drools.model.Rule rule_SuggestedBlindsDTUnit_453() {
        final org.drools.model.UnitData<org.kie.api.runtime.rule.DataSource> var_suggestedBlindsInput1 = D.unitData(org.kie.api.runtime.rule.DataSource.class,
                "input0");
        final org.drools.model.UnitData<java.lang.Object> var_result = D.unitData(java.lang.Object.class,
                "result");
        final org.drools.model.UnitData<org.kie.api.runtime.rule.DataSource> var_suggestedBlindsInput2 = D.unitData(org.kie.api.runtime.rule.DataSource.class,
                "input1");
        final org.drools.model.UnitData<java.lang.Object> var_suggestedBlinds = D.unitData(java.lang.Object.class,
                "suggestedBlinds");
        final org.drools.model.UnitData<org.kie.api.runtime.rule.DataSource> var_suggestedBlindsOutput1 = D.unitData(org.kie.api.runtime.rule.DataSource.class,
                "output0");
        final org.drools.model.Variable<java.lang.Object> var_$pattern_Boolean$8$ = D.declarationOf(java.lang.Object.class,
                "$pattern_Boolean$8$",
                var_suggestedBlindsInput1);
        final org.drools.model.Variable<java.lang.Object> var_$pattern_String$9$ = D.declarationOf(java.lang.Object.class,
                "$pattern_String$9$",
                var_suggestedBlindsInput2);
        org.drools.model.Rule rule = D.rule("org.drools.dmnem.poc20171103",
                "SuggestedBlindsDTUnit-3").unit(org.drools.dmnem.poc20171103.SuggestedBlindsDTUnit.class)
                .build(D.pattern(var_$pattern_Boolean$8$).expr("$expr$2$",
                        (_this) -> or( new Suggested_32Blindsr2c0().getUnaryTests() ).apply( null, _this )),
                        D.pattern(var_$pattern_String$9$).expr("$expr$4$",
                                (_this) -> or( new Suggested_32Blindsr2c1().getUnaryTests() ).apply( null, _this )),
                        D.on(var_suggestedBlindsOutput1).execute((suggestedBlindsOutput1) -> {
                            System.out.println("SuggestedBlinds ROW 3");
                            suggestedBlindsOutput1.insert("CLOSED");
                        }));
        return rule;
    }
}
