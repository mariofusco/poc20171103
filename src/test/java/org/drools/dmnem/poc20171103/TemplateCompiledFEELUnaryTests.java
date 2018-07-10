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

public class TemplateCompiledFEELUnaryTests implements CompiledFEELUnaryTests {

    public static final CompiledFEELUnaryTests INSTANCE = new TemplateCompiledFEELUnaryTests();

    /**
     * FEEL: "sunlight", "night"
     */
    @Override
    public List<UnaryTest> getUnaryTests() {
        return (java.util.List) (list(UT__34sunlight_34, UT__34night_34));
    }

    /**
     * FEEL unary test: "night"
     */
    public static final org.kie.dmn.feel.runtime.UnaryTest UT__34night_34 = (feelExprCtx, left) -> eq(left, "night");

    /**
     * FEEL unary test: "sunlight"
     */
    public static final org.kie.dmn.feel.runtime.UnaryTest UT__34sunlight_34 = (feelExprCtx, left) -> eq(left, "sunlight");
}
