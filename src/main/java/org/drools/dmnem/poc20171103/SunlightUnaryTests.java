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

import java.util.List;

import org.kie.dmn.feel.codegen.feel11.CompiledFEELUnaryTests;
import org.kie.dmn.feel.runtime.UnaryTest;

import static org.kie.dmn.feel.codegen.feel11.CompiledFEELSemanticMappings.gte;
import static org.kie.dmn.feel.codegen.feel11.CompiledFEELSemanticMappings.list;
import static org.kie.dmn.feel.codegen.feel11.CompiledFEELSemanticMappings.lt;

public class SunlightUnaryTests {
    public static class sunlightr0c0 implements CompiledFEELUnaryTests {

        /**
         * FEEL: <8
         */
        @Override
        public List<UnaryTest> getUnaryTests() {
            return (java.util.List) (list(UT__608));
        }

        public static final java.math.BigDecimal K_8 = new java.math.BigDecimal(8, java.math.MathContext.DECIMAL128);

        /**
         * FEEL unary test: <8
         */
        public static final org.kie.dmn.feel.runtime.UnaryTest UT__608 = (feelExprCtx, left) -> lt(left, K_8);
    }

    public static class sunlightr1c0 implements CompiledFEELUnaryTests {

        /**
         * FEEL: [8..18)
         */
        @Override
        public List<UnaryTest> getUnaryTests() {
            return (java.util.List) (list(UT__918_46_4618_41));
        }

        public static final java.math.BigDecimal K_18 = new java.math.BigDecimal(18, java.math.MathContext.DECIMAL128);

        public static final java.math.BigDecimal K_8 = new java.math.BigDecimal(8, java.math.MathContext.DECIMAL128);

        /**
         * FEEL range: [8..18)
         */
        public static final org.kie.dmn.feel.runtime.Range RANGE__918_46_4618_41 = new org.kie.dmn.feel.runtime.impl.RangeImpl(org.kie.dmn.feel.runtime.Range.RangeBoundary.CLOSED, (java.lang.Comparable) K_8, (java.lang.Comparable) K_18, org.kie.dmn.feel.runtime.Range.RangeBoundary.OPEN);

        /**
         * FEEL unary test: [8..18)
         */
        public static final org.kie.dmn.feel.runtime.UnaryTest UT__918_46_4618_41 = (feelExprCtx, left) -> RANGE__918_46_4618_41.includes(left);
    }

    public static class sunlightr2c0 implements CompiledFEELUnaryTests {

        /**
         * FEEL: >= 18
         */
        @Override
        public List<UnaryTest> getUnaryTests() {
            return (java.util.List) (list(UT__62_61_3218));
        }

        public static final java.math.BigDecimal K_18 = new java.math.BigDecimal(18, java.math.MathContext.DECIMAL128);

        /**
         * FEEL unary test: >= 18
         */
        public static final org.kie.dmn.feel.runtime.UnaryTest UT__62_61_3218 = (feelExprCtx, left) -> gte(left, K_18);
    }
}
