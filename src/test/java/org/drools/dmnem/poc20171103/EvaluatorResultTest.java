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

import org.junit.Test;
import org.kie.dmn.api.core.DMNContext;
import org.kie.dmn.api.core.DMNResult;
import org.kie.dmn.api.core.event.DMNRuntimeEventManager;
import org.kie.dmn.core.api.DMNExpressionEvaluator;
import org.kie.dmn.core.api.EvaluatorResult;
import org.kie.dmn.core.impl.DMNContextImpl;
import org.kie.dmn.core.impl.DMNResultImpl;

import static org.junit.Assert.assertEquals;

public class EvaluatorResultTest {

    @Test
    public void testDMNExpressionEvaluator() {
        DMNExpressionEvaluator evaluator = new ModelBasedDMNExpressionEvaluator();

        DMNContext dmnContext = new DMNContextImpl();
        dmnContext.set("now", LocalDateTime.parse("2007-12-03T10:15:30"));
        dmnContext.set("is Present", true);

        DMNResultImpl dmnResult = new DMNResultImpl( null );
        dmnResult.setContext( dmnContext );
        EvaluatorResult result = evaluator.evaluate( null, dmnResult );
        assertEquals( EvaluatorResult.ResultType.SUCCESS, result.getResultType() );
        assertEquals( "OFF", result.getResult() );
    }

    public static class ModelBasedDMNExpressionEvaluator implements DMNExpressionEvaluator {

        @Override
        public EvaluatorResult evaluate( DMNRuntimeEventManager eventManager, DMNResult result ) {
            throw new UnsupportedOperationException( "org.drools.dmnem.poc20171103.ExecutableModelTest.ModelBasedDMNExpressionEvaluator.evaluate -> TODO" );
        }
    }
}
