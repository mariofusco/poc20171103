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

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.junit.Test;
import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.builder.Message;
import org.kie.api.builder.Results;
import org.kie.api.runtime.KieContainer;
import org.kie.dmn.api.core.DMNContext;
import org.kie.dmn.api.core.DMNModel;
import org.kie.dmn.api.core.DMNResult;
import org.kie.dmn.api.core.DMNRuntime;
import org.kie.dmn.core.api.DMNFactory;
import org.kie.dmn.core.util.KieHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class RuleTest {
    static final Logger LOG = LoggerFactory.getLogger(RuleTest.class);

    /**
     * NOTES: cannot create a kiebase/kiesession against a kiebase with UNITs
     */
    @Test
    public void testDMN() throws InterruptedException {
        DMNRuntime dmnRuntime = getDmnRuntime("POC20171103.dmn");

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

    @Test
    public void testSimpleDecisionTableHitPolicyUnique() {
        DMNRuntime dmnRuntime = getDmnRuntime("0004-simpletable-U.dmn");
        DMNModel dmnModel = dmnRuntime.getModel("https://github.com/kiegroup/kie-dmn", "0004-simpletable-U");
        assertThat(dmnModel, notNullValue());

        DMNContext dmnContext = getSimpleTableContext( BigDecimal.valueOf(18), "Medium", true);

        DMNResult dmnResult = dmnRuntime.evaluateAll(dmnModel, dmnContext);
        System.out.println(dmnResult);

        DMNContext resultContext = dmnResult.getContext();

        assertThat(resultContext.get("Approval Status"), is("Approved"));
    }

    @Test
    public void testSimpleDecisionTableHitPolicyUniqueSatisfies() {
        DMNRuntime dmnRuntime = getDmnRuntime("0004-simpletable-U.dmn");
        DMNModel dmnModel = dmnRuntime.getModel("https://github.com/kiegroup/kie-dmn", "0004-simpletable-U");
        assertThat(dmnModel, notNullValue());

        DMNContext dmnContext = getSimpleTableContext(BigDecimal.valueOf(18), "ASD", false);

        DMNResult dmnResult = dmnRuntime.evaluateAll(dmnModel, dmnContext);
        System.out.println(dmnResult);

        DMNContext resultContext = dmnResult.getContext();

        assertThat(resultContext.get("Approval Status"), nullValue());
        assertTrue(dmnResult.getMessages().size() > 0);
    }

    @Test
    public void testSimpleDecisionTableHitPolicyFirst() {
        DMNRuntime dmnRuntime = getDmnRuntime("0004-simpletable-F.dmn");
        DMNModel dmnModel = dmnRuntime.getModel("https://github.com/kiegroup/kie-dmn", "0004-simpletable-F");
        assertThat(dmnModel, notNullValue());

        DMNContext dmnContext = getSimpleTableContext(BigDecimal.valueOf(18), "Medium", true);

        DMNResult dmnResult = dmnRuntime.evaluateAll(dmnModel, dmnContext);
        System.out.println(dmnResult);

        DMNContext resultContext = dmnResult.getContext();

        final Map<String, Object> decisionResult = (Map<String, Object>) resultContext.get("Decision Result");
        assertThat(decisionResult.values(), hasSize(2));
        assertThat(decisionResult, hasEntry(is("Approval Status"), is("Approved")));
        assertThat(decisionResult, hasEntry(is("Decision Review"), is("Decision final")));
    }

    @Test
    public void testSimpleDecisionTableHitPolicyAnyEqualRules() {
        testSimpleDecisionTableHitPolicyAny("0004-simpletable-A.dmn", "0004-simpletable-A", true);
    }

    @Test
    public void testSimpleDecisionTableHitPolicyAnyNonEqualRules() {
        testSimpleDecisionTableHitPolicyAny("0004-simpletable-A-non-equal.dmn", "0004-simpletable-A-non-equal", false);
    }

    private void testSimpleDecisionTableHitPolicyAny(final String resourceName, final String modelName, final boolean equalRules) {
        DMNRuntime dmnRuntime = getDmnRuntime(resourceName);
        DMNModel dmnModel = dmnRuntime.getModel("https://github.com/kiegroup/kie-dmn", modelName);
        assertThat(dmnModel, notNullValue());

        DMNContext context = getSimpleTableContext(BigDecimal.valueOf(18), "Medium", true);

        DMNResult dmnResult = dmnRuntime.evaluateAll(dmnModel, context);
        System.out.println(dmnResult);

        DMNContext result = dmnResult.getContext();
        if (equalRules) {
            assertThat(result.get("Approval Status"), is("Approved"));
        } else {
            assertThat(dmnResult.hasErrors(), is(true));
            assertThat((String) result.get("Approval Status"), isEmptyOrNullString());
        }
    }

    @Test
    public void testSimpleDecisionTableHitPolicyPriority() {
        DMNRuntime dmnRuntime = getDmnRuntime("0004-simpletable-P.dmn");
        DMNModel dmnModel = dmnRuntime.getModel("https://github.com/kiegroup/kie-dmn", "0004-simpletable-P");
        assertThat(dmnModel, notNullValue());

        DMNContext context = getSimpleTableContext(BigDecimal.valueOf(70), "Medium", true);
        DMNResult dmnResult = dmnRuntime.evaluateAll(dmnModel, context);
        System.out.println(dmnResult);

        DMNContext result = dmnResult.getContext();
        assertThat(result.get("Approval Status"), is("Declined"));
    }

    @Test
    public void testSimpleDecisionTableHitPolicyPriorityMultipleOutputs() {
        DMNRuntime dmnRuntime = getDmnRuntime("0004-simpletable-P-multiple-outputs.dmn");
        final DMNModel dmnModel = dmnRuntime.getModel("https://github.com/kiegroup/kie-dmn", "0004-simpletable-P-multiple-outputs");
        assertThat(dmnModel, notNullValue());

        DMNContext context = getSimpleTableContext(BigDecimal.valueOf(18), "Medium", true);

        DMNResult dmnResult = dmnRuntime.evaluateAll(dmnModel, context);
        System.out.println(dmnResult);

        DMNContext result = dmnResult.getContext();
        Map<String, Object> decisionResult = (Map<String, Object>) result.get("Decision Result");
        assertThat(decisionResult.values(), hasSize(2));
        assertThat(decisionResult, hasEntry(is("Approval Status"), is("Declined")));
        assertThat(decisionResult, hasEntry(is("Decision Review"), is("Needs verification")));
    }

    @Test
    public void testSimpleDecisionTableHitPolicyOutputOrder() {
        DMNRuntime dmnRuntime = getDmnRuntime("0004-simpletable-O.dmn");
        DMNModel dmnModel = dmnRuntime.getModel("https://github.com/kiegroup/kie-dmn", "0004-simpletable-O");
        assertThat(dmnModel, notNullValue());

        DMNContext context = getSimpleTableContext(BigDecimal.valueOf(70), "Medium", true);
        DMNResult dmnResult = dmnRuntime.evaluateAll(dmnModel, context);
        System.out.println(dmnResult);

        DMNContext result = dmnResult.getContext();
        List<String> decisionResults = (List<String>) result.get("Approval Status");
        assertThat(decisionResults, hasSize(3));
        assertThat(decisionResults, contains("Declined", "Declined", "Approved"));
    }

    @Test
    public void testSimpleDecisionTableHitPolicyOutputOrderMultipleOutputs() {
        DMNRuntime dmnRuntime = getDmnRuntime("0004-simpletable-O-multiple-outputs.dmn");
        DMNModel dmnModel = dmnRuntime.getModel("https://github.com/kiegroup/kie-dmn", "0004-simpletable-O-multiple-outputs");
        assertThat(dmnModel, notNullValue());

        DMNContext context = getSimpleTableContext(BigDecimal.valueOf(18), "Medium", true);
        DMNResult dmnResult = dmnRuntime.evaluateAll(dmnModel, context);
        System.out.println(dmnResult);

        DMNContext result = dmnResult.getContext();
        List<Map<String, String>> decisionResult = (List<Map<String, String>>) result.get("Decision Result");
        assertThat(decisionResult, hasSize(4));
        // Must be ordered, so we can read from the list by index
        checkMultipleOutputResult(decisionResult.get(0), "Declined", "Needs verification");
        checkMultipleOutputResult(decisionResult.get(1), "Declined", "Decision final");
        checkMultipleOutputResult(decisionResult.get(2), "Approved", "Needs verification");
        checkMultipleOutputResult(decisionResult.get(3), "Approved", "Decision final");
    }

    private void checkMultipleOutputResult(final Map<String, String> outputResult,
                                           final String expectedApprovalStatus, final String expectedDecisionReview) {
        assertThat(outputResult, hasEntry(is("Approval Status"), is(expectedApprovalStatus)));
        assertThat(outputResult, hasEntry(is("Decision Review"), is(expectedDecisionReview)));
    }

    @Test
    public void testSimpleDecisionTableHitPolicyRuleOrder() {
        DMNRuntime dmnRuntime = getDmnRuntime("0004-simpletable-R.dmn");
        DMNModel dmnModel = dmnRuntime.getModel("https://github.com/kiegroup/kie-dmn", "0004-simpletable-R");
        assertThat(dmnModel, notNullValue());

        DMNContext context = getSimpleTableContext(BigDecimal.valueOf(70), "Medium", true);
        DMNResult dmnResult = dmnRuntime.evaluateAll(dmnModel, context);
        System.out.println(dmnResult);

        DMNContext result = dmnResult.getContext();
        List<String> decisionResults = (List<String>) result.get("Approval Status");
        assertThat(decisionResults, hasSize(3));
        assertThat(decisionResults, contains("Approved", "Needs review", "Declined"));
    }

    @Test
    public void testSimpleDecisionTableHitPolicyCollect() {
        List<BigDecimal> decisionResults = executeTestDecisionTableHitPolicyCollect(getSimpleTableContext( BigDecimal.valueOf( 70 ), "Medium", true));
        assertThat(decisionResults, hasSize(3));
        assertThat(decisionResults, contains(BigDecimal.valueOf(10), BigDecimal.valueOf(25), BigDecimal.valueOf(13)));
    }

    @Test
    public void testSimpleDecisionTableHitPolicyCollectNoHits() {
        List<BigDecimal> decisionResults = executeTestDecisionTableHitPolicyCollect(getSimpleTableContext( BigDecimal.valueOf( 5 ), "Medium", true));
        assertThat(decisionResults, hasSize(0));
    }

    private List<BigDecimal> executeTestDecisionTableHitPolicyCollect(DMNContext context) {
        DMNRuntime dmnRuntime = getDmnRuntime( "0004-simpletable-C.dmn");
        DMNModel dmnModel = dmnRuntime.getModel( "https://github.com/kiegroup/kie-dmn", "0004-simpletable-C");
        assertThat(dmnModel, notNullValue());

        DMNResult dmnResult = dmnRuntime.evaluateAll(dmnModel, context);
        System.out.println(dmnResult);

        DMNContext result = dmnResult.getContext();

        final List<BigDecimal> decisionResults = (List<BigDecimal>) result.get( "Status number");
        return decisionResults;
    }

    @Test
    public void testSimpleDecisionTableHitPolicyCollectSum() {
        testSimpleDecisionTableHitPolicyCollectAggregateFunction(
                "0004-simpletable-C-sum.dmn", "0004-simpletable-C-sum", BigDecimal.valueOf(48),
                getSimpleTableContext(BigDecimal.valueOf(70), "Medium", true));
    }

    @Test
    public void testSimpleDecisionTableHitPolicyCollectSumMultipleOutputs() {
        DMNRuntime dmnRuntime = getDmnRuntime("0004-simpletable-C-sum-multiple-outputs.dmn");
        DMNModel dmnModel = dmnRuntime.getModel("https://github.com/kiegroup/kie-dmn", "0004-simpletable-C-sum-multiple-outputs");
        assertThat(dmnModel, notNullValue());

        DMNContext context = getSimpleTableContext(BigDecimal.valueOf(70), "Medium", true);
        DMNResult dmnResult = dmnRuntime.evaluateAll(dmnModel, context);

        DMNContext result = dmnResult.getContext();
        Map<String, Object> decisionResult = (Map<String, Object>) result.get("Decision Result");
        assertThat(decisionResult.values(), hasSize(2));
        assertThat(decisionResult, hasEntry( "Value1", BigDecimal.valueOf( 25 ) ));
        assertThat(decisionResult, hasEntry( "Value2", BigDecimal.valueOf( 32 ) ));
    }

    @Test
    public void testSimpleDecisionTableHitPolicyCollectMin() {
        testSimpleDecisionTableHitPolicyCollectAggregateFunction(
                "0004-simpletable-C-min.dmn", "0004-simpletable-C-min", BigDecimal.valueOf(10),
                getSimpleTableContext(BigDecimal.valueOf(70), "Medium", true));
    }

    @Test
    public void testSimpleDecisionTableHitPolicyCollectMax() {
        testSimpleDecisionTableHitPolicyCollectAggregateFunction(
                "0004-simpletable-C-max.dmn", "0004-simpletable-C-max", BigDecimal.valueOf(25),
                getSimpleTableContext(BigDecimal.valueOf(70), "Medium", true));
    }

    @Test
    public void testSimpleDecisionTableHitPolicyCollectCount() {
        testSimpleDecisionTableHitPolicyCollectAggregateFunction(
                "0004-simpletable-C-count.dmn", "0004-simpletable-C-count", BigDecimal.valueOf(3),
                getSimpleTableContext(BigDecimal.valueOf(70), "Medium", true));
    }

    @Test
    public void testSimpleDecisionTableHitPolicyCollectCountNoHits() {
        testSimpleDecisionTableHitPolicyCollectAggregateFunction(
                "0004-simpletable-C-count.dmn", "0004-simpletable-C-count", BigDecimal.valueOf(0),
                getSimpleTableContext(BigDecimal.valueOf(5), "Medium", true));
    }

    private void testSimpleDecisionTableHitPolicyCollectAggregateFunction(
            final String resourceName, final String modelName, final BigDecimal expectedResult, final DMNContext context) {
        DMNRuntime dmnRuntime = getDmnRuntime(resourceName);
        DMNModel dmnModel = dmnRuntime.getModel("https://github.com/kiegroup/kie-dmn", modelName);
        assertThat(dmnModel, notNullValue());

        DMNResult dmnResult = dmnRuntime.evaluateAll(dmnModel, context);
        System.out.println(dmnResult);

        DMNContext result = dmnResult.getContext();
        assertThat(result.get("Status number"), is(expectedResult));
    }

    @Test
    public void testDecisionTableHitPolicyCollect() {
        DMNRuntime dmnRuntime = getDmnRuntime("Collect_Hit_Policy.dmn");
        final DMNModel dmnModel = dmnRuntime.getModel("http://www.trisotech.com/definitions/_da1a4dcb-01bf-4dee-9be8-f498bc68178c", "Collect Hit Policy");
        assertThat(dmnModel, notNullValue());

        final DMNContext context = DMNFactory.newContext();
        context.set("Input", 20);

        final DMNResult dmnResult = dmnRuntime.evaluateAll(dmnModel, context);
        assertThat(dmnResult.hasErrors(), is(false));

        final DMNContext result = dmnResult.getContext();
        assertThat(result.get("Collect"), is(BigDecimal.valueOf(50)));
    }

    private DMNRuntime getDmnRuntime(String dmnFile) {
        KieServices kieServices = KieServices.Factory.get();

        KieContainer kContainer = KieHelper.getKieContainer(kieServices.newReleaseId("x", UUID.randomUUID().toString(), "1.0.0"),
                                                            kieServices.getResources().newClassPathResource("/dtables/" + dmnFile, RuleTest.class));
        Results verifyResults = kContainer.verify();
        for (Message m : verifyResults.getMessages()) {
            LOG.info("{}", m);
        }

        LOG.info("Creating kieBase");
        KieBase kieBase = kContainer.getKieBase();

        LOG.info("Running units");
        return kieBase.newKieSession().getKieRuntime(DMNRuntime.class);
    }

    private DMNContext getSimpleTableContext(final BigDecimal age, final String riskCategory, final boolean isAffordable) {
        final DMNContext context = DMNFactory.newContext();
        context.set("Age", age);
        context.set("RiskCategory", riskCategory);
        context.set("isAffordable", isAffordable);
        return context;
    }
}