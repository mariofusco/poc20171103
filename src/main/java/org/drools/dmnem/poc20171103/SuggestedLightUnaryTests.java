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

import static org.kie.dmn.feel.codegen.feel11.CompiledFEELSemanticMappings.eq;
import static org.kie.dmn.feel.codegen.feel11.CompiledFEELSemanticMappings.list;

public class SuggestedLightUnaryTests {
    public static class Suggested_32lightr0c0 implements CompiledFEELUnaryTests {

        /**
         * FEEL: false
         */
        @Override
        public List<UnaryTest> getUnaryTests() {
            return (java.util.List) (list(UT_false));
        }

        /**
         * FEEL unary test: false
         */
        public static final org.kie.dmn.feel.runtime.UnaryTest UT_false = (feelExprCtx, left) -> eq(left, false);
    }

    public static class Suggested_32lightr0c1 implements CompiledFEELUnaryTests {

        /**
         * FEEL: -
         */
        @Override
        public List<UnaryTest> getUnaryTests() {
            return (java.util.List) (list(org.kie.dmn.feel.lang.ast.DashNode.DashUnaryTest.INSTANCE));
        }
    }

    public static class Suggested_32lightr1c0 implements CompiledFEELUnaryTests {

        /**
         * FEEL: true
         */
        @Override
        public List<UnaryTest> getUnaryTests() {
            return (java.util.List) (list(UT_true));
        }

        /**
         * FEEL unary test: true
         */
        public static final org.kie.dmn.feel.runtime.UnaryTest UT_true = (feelExprCtx, left) -> eq(left, true);
    }

    public static class Suggested_32lightr1c1 implements CompiledFEELUnaryTests {

        /**
         * FEEL: "sunlight"
         */
        @Override
        public List<UnaryTest> getUnaryTests() {
            return (java.util.List) (list(UT__34sunlight_34));
        }

        /**
         * FEEL unary test: "sunlight"
         */
        public static final org.kie.dmn.feel.runtime.UnaryTest UT__34sunlight_34 = (feelExprCtx, left) -> eq(left, "sunlight");
    }

    public static class Suggested_32lightr2c0 implements CompiledFEELUnaryTests {

        /**
         * FEEL: true
         */
        @Override
        public List<UnaryTest> getUnaryTests() {
            return (java.util.List) (list(UT_true));
        }

        /**
         * FEEL unary test: true
         */
        public static final org.kie.dmn.feel.runtime.UnaryTest UT_true = (feelExprCtx, left) -> eq(left, true);
    }

    public static class Suggested_32lightr2c1 implements CompiledFEELUnaryTests {

        /**
         * FEEL: "night time"
         */
        @Override
        public List<UnaryTest> getUnaryTests() {
            return (java.util.List) (list(UT__34night_32time_34));
        }

        /**
         * FEEL unary test: "night time"
         */
        public static final org.kie.dmn.feel.runtime.UnaryTest UT__34night_32time_34 = (feelExprCtx, left) -> eq(left, "night time");
    }
}
