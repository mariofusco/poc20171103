/*
 * Copyright 2015 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
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

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.UUID;

import org.junit.Test;
import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.builder.Message;
import org.kie.api.builder.Results;
import org.kie.api.definition.KiePackage;
import org.kie.api.definition.rule.Rule;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.rule.RuleUnitExecutor;
import org.kie.dmn.api.core.DMNContext;
import org.kie.dmn.api.core.DMNModel;
import org.kie.dmn.api.core.DMNResult;
import org.kie.dmn.api.core.DMNRuntime;
import org.kie.dmn.core.impl.DMNContextImpl;
import org.kie.dmn.core.util.KieHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import temp.kiedmn.DMNUnit;

public class RuleTest {
    static final Logger LOG = LoggerFactory.getLogger(RuleTest.class);

    /**
     * NOTES: cannot create a kiebase/kiesession against a kiebase with UNITs
     */
    @Test(timeout = 5_000)
    public void testDMN() throws InterruptedException {
        KieServices kieServices = KieServices.Factory.get();

        KieContainer kContainer = KieHelper.getKieContainer(kieServices.newReleaseId("x", UUID.randomUUID().toString(), "1.0.0"),
                                                            kieServices.getResources().newClassPathResource("/POC20171103.dmn", RuleTest.class));
        Results verifyResults = kContainer.verify();
        for (Message m : verifyResults.getMessages()) {
            LOG.info("{}", m);
        }

        LOG.info("Creating kieBase");
        KieBase kieBase = kContainer.getKieBase();

        LOG.info("Running units");
        DMNRuntime dmnRuntime = kieBase.newKieSession().getKieRuntime(DMNRuntime.class);

        DMNModel dmnModel = dmnRuntime.getModels().get(0);

        DMNContext dmnContext = dmnRuntime.newContext();
        dmnContext.set("now", LocalDateTime.parse("2007-12-03T10:15:30"));
        dmnContext.set("is Present", true);

        DMNResult dmnResult = dmnRuntime.evaluateAll(dmnModel, dmnContext);
        System.out.println(dmnResult);

        DMNContext resultContext = dmnResult.getContext();
        assertEquals("sunlight", resultContext.get("sunlight"));
        assertEquals("OFF", resultContext.get("Suggested light"));
        assertEquals("OPEN", resultContext.get("Suggested Blinds"));
    }

    /* notes
     * cannot have agendalistener
     * datasource even for 1 singleton (cannot be a variable ~~ global )
     * need to instantiate datasource actively otherwise NPE. 
     * need to rememebr the getter on the Unit class.
     * DataSource generic of T is not typesafe in T, there was a bug I could insert a String in a DataSoruce<Integer>
     * cannot init the Datasource from within Unit code, even from lifecycle methods because I need session.
     */
    @Test(timeout = 5_000)
    public void test() throws InterruptedException {
        KieServices kieServices = KieServices.Factory.get();

        KieContainer kContainer = kieServices.getKieClasspathContainer();
        Results verifyResults = kContainer.verify();
        for (Message m : verifyResults.getMessages()) {
            LOG.info("{}", m);
        }

        LOG.info("Creating kieBase");
        KieBase kieBase = kContainer.getKieBase();

        LOG.info("There should be rules: ");
        for ( KiePackage kp : kieBase.getKiePackages() ) {
            for (Rule rule : kp.getRules()) {
                LOG.info("kp " + kp + " rule " + rule.getName());
            }
        }

        LOG.info("Running units");
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

    @Test()
    public void testUsingDMNController() throws InterruptedException {
        DMNContext dmnContext = new DMNContextImpl();
        dmnContext.set("now", LocalDateTime.parse("2007-12-03T10:15:30"));
        dmnContext.set("is Present", true);

        ThisModelDMNController.run(dmnContext, Collections.emptyList());

        // currently it mutates the original context, just to prove the point.
        assertEquals("sunlight", dmnContext.get("sunlight"));
        assertEquals("OFF", dmnContext.get("Suggested light"));
        assertEquals("OPEN", dmnContext.get("Suggested Blinds"));
    }

    @Test()
    public void testUsingDMNControllerOnlySomeDecision() throws InterruptedException {
        DMNContext dmnContext = new DMNContextImpl();
        dmnContext.set("now", LocalDateTime.parse("2007-12-03T10:15:30"));
        dmnContext.set("is Present", true);

        ThisModelDMNController.run(dmnContext, Arrays.asList("Suggested light"));

        // currently it mutates the original context, just to prove the point.
        assertEquals("sunlight", dmnContext.get("sunlight"));
        assertEquals("OFF", dmnContext.get("Suggested light"));
        assertEquals(null, dmnContext.get("Suggested Blinds"));
    }
}