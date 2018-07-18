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
import org.drools.dmnem.poc20171103.SunlightUnaryTests.*;

import static org.drools.dmnem.poc20171103.UnaryTestsUtil.or;

public class SunlightExecModel {

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
                        (_this) -> or( new sunlightr0c0().getUnaryTests() ).apply( null, _this )),
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
                        (_this) -> or( new sunlightr1c0().getUnaryTests() ).apply( null, _this )),
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
                        (_this) -> or( new sunlightr2c0().getUnaryTests() ).apply( null, _this )),
                        D.on(var_sunlightOutput1).execute((sunlightOutput1) -> {
                            System.out.println("sunlight ROW 3");
                            sunlightOutput1.insert("night time");
                        }));
        return rule;
    }
}
