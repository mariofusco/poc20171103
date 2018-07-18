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

import static org.drools.dmnem.poc20171103.UnaryTests.or;

public class ExecModelRules {
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
                        (_this) -> or( new UnaryTests.Suggested_32lightr0c0().getUnaryTests() ).apply( null, _this )),
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
                        (_this) -> or( new UnaryTests.Suggested_32lightr1c0().getUnaryTests() ).apply( null, _this )),
                        D.pattern(var_$pattern_String$1$).expr("$expr$3$",
                                (_this) -> or( new UnaryTests.Suggested_32lightr1c1().getUnaryTests() ).apply( null, _this )),
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
                        (_this) -> or( new UnaryTests.Suggested_32lightr2c0().getUnaryTests() ).apply( null, _this )),
                        D.pattern(var_$pattern_String$2$).expr("$expr$4$",
                                (_this) -> or( new UnaryTests.Suggested_32lightr2c1().getUnaryTests() ).apply( null, _this )),
                        D.on(var_suggestedLightOutput1).execute((suggestedLightOutput1) -> {
                            System.out.println("SuggestedLight ROW 3");
                            suggestedLightOutput1.insert("ON");
                        }));
        return rule;
    }

    /**
     * Rule name: guard sunlight decision
     */
    public static org.drools.model.Rule rule_guard_32sunlight_32decision() {
        final org.drools.model.UnitData<org.kie.api.runtime.rule.DataSource> var_sunlight = D.unitData(org.kie.api.runtime.rule.DataSource.class,
                "sunlight");
        final org.drools.model.UnitData<org.kie.api.runtime.rule.DataSource> var_suggestedBlinds = D.unitData(org.kie.api.runtime.rule.DataSource.class,
                "suggestedBlinds");
        final org.drools.model.UnitData<org.kie.api.runtime.rule.DataSource> var_now = D.unitData(org.kie.api.runtime.rule.DataSource.class,
                "now");
        final org.drools.model.UnitData<org.kie.api.runtime.rule.DataSource> var_suggestedLight = D.unitData(org.kie.api.runtime.rule.DataSource.class,
                "suggestedLight");
        final org.drools.model.UnitData<org.kie.api.runtime.rule.DataSource> var_isPresent = D.unitData(org.kie.api.runtime.rule.DataSource.class,
                "isPresent");
        final org.drools.model.Variable<java.time.LocalDateTime> var_$pattern_LocalDateTime$1$ = D.declarationOf(java.time.LocalDateTime.class,
                "$pattern_LocalDateTime$1$",
                var_now);
        final org.drools.model.Variable<java.lang.Object> var_sCoPe1_$pattern_String$3$ = D.declarationOf(java.lang.Object.class,
                "sCoPe1_$pattern_String$3$",
                var_sunlight);
        org.drools.model.Rule rule = D.rule("org.drools.dmnem.poc20171103",
                "guard sunlight decision").unit(org.drools.dmnem.poc20171103.MainDRDUnit.class)
                .build( D.pattern(var_$pattern_LocalDateTime$1$),
                        D.not(D.pattern(var_sCoPe1_$pattern_String$3$)),
                        D.execute((drools) -> {
                            System.out.println("okay enabling sunlight guard");
                            ((org.drools.modelcompiler.consequence.DroolsImpl) drools).asKnowledgeHelper()
                                    .guard(SunlightDTUnit.class);
                        }));
        return rule;
    }

    /**
     * Rule name: guard suggestedLight decision
     */
    public static org.drools.model.Rule rule_guard_32suggestedLight_32decision() {
        final org.drools.model.UnitData<org.kie.api.runtime.rule.DataSource> var_sunlight = D.unitData(org.kie.api.runtime.rule.DataSource.class,
                "sunlight");
        final org.drools.model.UnitData<org.kie.api.runtime.rule.DataSource> var_suggestedBlinds = D.unitData(org.kie.api.runtime.rule.DataSource.class,
                "suggestedBlinds");
        final org.drools.model.UnitData<org.kie.api.runtime.rule.DataSource> var_now = D.unitData(org.kie.api.runtime.rule.DataSource.class,
                "now");
        final org.drools.model.UnitData<org.kie.api.runtime.rule.DataSource> var_suggestedLight = D.unitData(org.kie.api.runtime.rule.DataSource.class,
                "suggestedLight");
        final org.drools.model.UnitData<org.kie.api.runtime.rule.DataSource> var_isPresent = D.unitData(org.kie.api.runtime.rule.DataSource.class,
                "isPresent");
        final org.drools.model.Variable<java.lang.Object> var_$pattern_String$4$ = D.declarationOf(java.lang.Object.class,
                "$pattern_String$4$",
                var_sunlight);
        final org.drools.model.Variable<java.lang.Object> var_$pattern_Boolean$4$ = D.declarationOf(java.lang.Object.class,
                "$pattern_Boolean$4$",
                var_isPresent);
        final org.drools.model.Variable<java.lang.Object> var_sCoPe2_$pattern_String$5$ = D.declarationOf(java.lang.Object.class,
                "sCoPe2_$pattern_String$5$",
                var_suggestedLight);
        org.drools.model.Rule rule = D.rule("org.drools.dmnem.poc20171103",
                "guard suggestedLight decision").unit(org.drools.dmnem.poc20171103.MainDRDUnit.class)
                .build(D.pattern(var_$pattern_String$4$),
                        D.pattern(var_$pattern_Boolean$4$),
                        D.not(D.pattern(var_sCoPe2_$pattern_String$5$)),
                        D.execute((drools) -> {
                            System.out.println("okay enabling suggestedLight guard");
                            ((org.drools.modelcompiler.consequence.DroolsImpl) drools).asKnowledgeHelper()
                                    .guard(SuggestedLightDTUnit.class);
                        }));
        return rule;
    }

    /**
     * Rule name: guard suggestedBlinds decision
     */
    public static org.drools.model.Rule rule_guard_32suggestedBlinds_32decision() {
        final org.drools.model.UnitData<org.kie.api.runtime.rule.DataSource> var_sunlight = D.unitData(org.kie.api.runtime.rule.DataSource.class,
                "sunlight");
        final org.drools.model.UnitData<org.kie.api.runtime.rule.DataSource> var_suggestedBlinds = D.unitData(org.kie.api.runtime.rule.DataSource.class,
                "suggestedBlinds");
        final org.drools.model.UnitData<org.kie.api.runtime.rule.DataSource> var_now = D.unitData(org.kie.api.runtime.rule.DataSource.class,
                "now");
        final org.drools.model.UnitData<org.kie.api.runtime.rule.DataSource> var_suggestedLight = D.unitData(org.kie.api.runtime.rule.DataSource.class,
                "suggestedLight");
        final org.drools.model.UnitData<org.kie.api.runtime.rule.DataSource> var_isPresent = D.unitData(org.kie.api.runtime.rule.DataSource.class,
                "isPresent");
        final org.drools.model.Variable<java.lang.Object> var_$pattern_String$6$ = D.declarationOf(java.lang.Object.class,
                "$pattern_String$6$",
                var_sunlight);
        final org.drools.model.Variable<java.lang.Object> var_$pattern_Boolean$5$ = D.declarationOf(java.lang.Object.class,
                "$pattern_Boolean$5$",
                var_isPresent);
        final org.drools.model.Variable<java.lang.Object> var_sCoPe3_$pattern_String$7$ = D.declarationOf(java.lang.Object.class,
                "sCoPe3_$pattern_String$7$",
                var_suggestedBlinds);
        org.drools.model.Rule rule = D.rule("org.drools.dmnem.poc20171103",
                "guard suggestedBlinds decision").unit(org.drools.dmnem.poc20171103.MainDRDUnit.class)
                .build(D.pattern(var_$pattern_String$6$),
                        D.pattern(var_$pattern_Boolean$5$),
                        D.not(D.pattern(var_sCoPe3_$pattern_String$7$)),
                        D.execute((drools) -> {
                            System.out.println("okay enabling suggestedBlinds guard");
                            ((org.drools.modelcompiler.consequence.DroolsImpl) drools).asKnowledgeHelper()
                                    .guard(SuggestedBlindsDTUnit.class);
                        }));
        return rule;
    }

    /**
     * Rule name: SunlightDTUnit_1
     */
    public static org.drools.model.Rule rule_SunlightDTUnit__1() {
        final org.drools.model.UnitData<org.kie.api.runtime.rule.DataSource> var_sunlightOutput1 = D.unitData(org.kie.api.runtime.rule.DataSource.class,
                "output0");
        final org.drools.model.UnitData<java.lang.Object> var_result = D.unitData(java.lang.Object.class,
                "result");
        final org.drools.model.UnitData<java.lang.Object> var_sunlight = D.unitData(java.lang.Object.class,
                "sunlight");
        final org.drools.model.UnitData<org.kie.api.runtime.rule.DataSource> var_sunlightInput1 = D.unitData(org.kie.api.runtime.rule.DataSource.class,
                "input0");
        final org.drools.model.Variable<java.lang.Object> var_$pattern_Integer$1$ = D.declarationOf(java.lang.Object.class,
                "$pattern_Integer$1$",
                var_sunlightInput1);
        org.drools.model.Rule rule = D.rule("org.drools.dmnem.poc20171103",
                "SunlightDTUnit_1").unit(org.drools.dmnem.poc20171103.SunlightDTUnit.class)
                .build(D.pattern(var_$pattern_Integer$1$).expr("$expr$5$",
                        (_this) -> or( new UnaryTests.sunlightr0c0().getUnaryTests() ).apply( null, _this )),
                        D.on(var_sunlightOutput1).execute((sunlightOutput1) -> {
                            System.out.println("sunlight ROW 1");
                            sunlightOutput1.insert("night time");
                        }));
        return rule;
    }

    /**
     * Rule name: SunlightDTUnit_2
     */
    public static org.drools.model.Rule rule_SunlightDTUnit__2() {
        final org.drools.model.UnitData<org.kie.api.runtime.rule.DataSource> var_sunlightOutput1 = D.unitData(org.kie.api.runtime.rule.DataSource.class,
                "output0");
        final org.drools.model.UnitData<java.lang.Object> var_result = D.unitData(java.lang.Object.class,
                "result");
        final org.drools.model.UnitData<java.lang.Object> var_sunlight = D.unitData(java.lang.Object.class,
                "sunlight");
        final org.drools.model.UnitData<org.kie.api.runtime.rule.DataSource> var_sunlightInput1 = D.unitData(org.kie.api.runtime.rule.DataSource.class,
                "input0");
        final org.drools.model.Variable<java.lang.Object> var_$pattern_Integer$2$ = D.declarationOf(java.lang.Object.class,
                "$pattern_Integer$2$",
                var_sunlightInput1);
        org.drools.model.Rule rule = D.rule("org.drools.dmnem.poc20171103",
                "SunlightDTUnit_2").unit(org.drools.dmnem.poc20171103.SunlightDTUnit.class)
                .build(D.pattern(var_$pattern_Integer$2$).expr("$expr$6$",
                        (_this) -> or( new UnaryTests.sunlightr1c0().getUnaryTests() ).apply( null, _this )),
                        D.on(var_sunlightOutput1).execute((sunlightOutput1) -> {
                            System.out.println("sunlight ROW 2");
                            sunlightOutput1.insert("sunlight");
                        }));
        return rule;
    }

    /**
     * Rule name: SunlightDTUnit_3
     */
    public static org.drools.model.Rule rule_SunlightDTUnit__3() {
        final org.drools.model.UnitData<org.kie.api.runtime.rule.DataSource> var_sunlightOutput1 = D.unitData(org.kie.api.runtime.rule.DataSource.class,
                "output0");
        final org.drools.model.UnitData<java.lang.Object> var_result = D.unitData(java.lang.Object.class,
                "result");
        final org.drools.model.UnitData<java.lang.Object> var_sunlight = D.unitData(java.lang.Object.class,
                "sunlight");
        final org.drools.model.UnitData<org.kie.api.runtime.rule.DataSource> var_sunlightInput1 = D.unitData(org.kie.api.runtime.rule.DataSource.class,
                "input0");
        final org.drools.model.Variable<java.lang.Object> var_$pattern_Integer$3$ = D.declarationOf(java.lang.Object.class,
                "$pattern_Integer$3$",
                var_sunlightInput1);
        org.drools.model.Rule rule = D.rule("org.drools.dmnem.poc20171103",
                "SunlightDTUnit_3").unit(org.drools.dmnem.poc20171103.SunlightDTUnit.class)
                .build(D.pattern(var_$pattern_Integer$3$).expr("$expr$7$",
                        (_this) -> or( new UnaryTests.sunlightr2c0().getUnaryTests() ).apply( null, _this )),
                        D.on(var_sunlightOutput1).execute((sunlightOutput1) -> {
                            System.out.println("sunlight ROW 3");
                            sunlightOutput1.insert("night time");
                        }));
        return rule;
    }

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
                        (_this) -> or( new UnaryTests.Suggested_32Blindsr0c0().getUnaryTests() ).apply( null, _this )),
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
                        (_this) -> or( new UnaryTests.Suggested_32Blindsr1c0().getUnaryTests() ).apply( null, _this )),
                        D.pattern(var_$pattern_String$8$).expr("$expr$3$",
                                (_this) -> or( new UnaryTests.Suggested_32Blindsr1c1().getUnaryTests() ).apply( null, _this )),
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
                        (_this) -> or( new UnaryTests.Suggested_32Blindsr2c0().getUnaryTests() ).apply( null, _this )),
                        D.pattern(var_$pattern_String$9$).expr("$expr$4$",
                                (_this) -> or( new UnaryTests.Suggested_32Blindsr2c1().getUnaryTests() ).apply( null, _this )),
                        D.on(var_suggestedBlindsOutput1).execute((suggestedBlindsOutput1) -> {
                            System.out.println("SuggestedBlinds ROW 3");
                            suggestedBlindsOutput1.insert("CLOSED");
                        }));
        return rule;
    }
}
