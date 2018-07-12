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

import java.time.LocalDateTime;

import org.drools.model.Model;
import org.drools.model.impl.ModelImpl;
import org.drools.modelcompiler.builder.KieBaseBuilder;
import org.junit.Test;
import org.kie.api.KieBase;
import org.kie.api.runtime.rule.RuleUnitExecutor;
import org.kie.dmn.api.core.DMNContext;
import org.kie.dmn.core.impl.DMNContextImpl;
import temp.kiedmn.DMNUnit;

import static org.drools.dmnem.poc20171103.ExecModelRules.rule_SuggestedBlindsDTUnit_451;
import static org.drools.dmnem.poc20171103.ExecModelRules.rule_SuggestedBlindsDTUnit_452;
import static org.drools.dmnem.poc20171103.ExecModelRules.rule_SuggestedBlindsDTUnit_453;
import static org.drools.dmnem.poc20171103.ExecModelRules.rule_SuggestedLightDTUnit_451;
import static org.drools.dmnem.poc20171103.ExecModelRules.rule_SuggestedLightDTUnit_452;
import static org.drools.dmnem.poc20171103.ExecModelRules.rule_SuggestedLightDTUnit_453;
import static org.drools.dmnem.poc20171103.ExecModelRules.rule_SunlightDTUnit__1;
import static org.drools.dmnem.poc20171103.ExecModelRules.rule_SunlightDTUnit__2;
import static org.drools.dmnem.poc20171103.ExecModelRules.rule_SunlightDTUnit__3;
import static org.drools.dmnem.poc20171103.ExecModelRules.rule_guard_32suggestedBlinds_32decision;
import static org.drools.dmnem.poc20171103.ExecModelRules.rule_guard_32suggestedLight_32decision;
import static org.drools.dmnem.poc20171103.ExecModelRules.rule_guard_32sunlight_32decision;
import static org.junit.Assert.assertEquals;

public class ExecutableModelTest {

    @Test
    public void test() {
        Model model = new ModelImpl()
                .addRule( rule_SuggestedLightDTUnit_451() )
                .addRule( rule_SuggestedLightDTUnit_452() )
                .addRule( rule_SuggestedLightDTUnit_453() )
                .addRule( rule_guard_32sunlight_32decision() )
                .addRule( rule_guard_32suggestedLight_32decision() )
                .addRule( rule_guard_32suggestedBlinds_32decision() )
                .addRule( rule_SunlightDTUnit__1() )
                .addRule( rule_SunlightDTUnit__2() )
                .addRule( rule_SunlightDTUnit__3() )
                .addRule( rule_SuggestedBlindsDTUnit_451() )
                .addRule( rule_SuggestedBlindsDTUnit_452() )
                .addRule( rule_SuggestedBlindsDTUnit_453() );

        KieBase kieBase = KieBaseBuilder.createKieBaseFromModel( model );

        RuleUnitExecutor executor = RuleUnitExecutor.create().bind(kieBase);

        DMNContext dmnContext = new DMNContextImpl();
        dmnContext.set("now", LocalDateTime.parse("2007-12-03T10:15:30"));
        dmnContext.set("is Present", true);

        DMNUnit sunlightDTUnit = new SunlightDTUnit(dmnContext);
        Object sunlight = sunlightDTUnit.execute(executor).getResult();
        assertEquals("sunlight", sunlight);

        // in this case we mutate the main context, with hard code result knowing its key.
        dmnContext.set("sunlight", sunlight);

        SuggestedBlindsDTUnit suggestedBlindsDTUnit = new SuggestedBlindsDTUnit(dmnContext);
        Object suggestedBlinds = suggestedBlindsDTUnit.execute(executor).getResult();

        SuggestedLightDTUnit suggestedLightDTUnit = new SuggestedLightDTUnit(dmnContext);
        Object suggestedLight = suggestedLightDTUnit.execute(executor).getResult();

        assertEquals("OFF", suggestedLight);
        assertEquals("OPEN", suggestedBlinds);
    }


}
