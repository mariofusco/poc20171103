/*
 * Copyright 2017 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.drools.dmnem.poc20171103;

import org.kie.api.runtime.rule.DataSource;
import temp.kiedmn.DMNUnit;

public class SuggestedBlindsDTUnit extends DMNUnit {

    private DataSource<Object> input0;
    private DataSource<Object> input1;

    private DataSource<Object> output0 = DataSource.create();

    public DataSource<Object> getInput0() {
        return input0;
    }

    public DataSource<Object> getInput1() {
        return input1;
    }

    public DataSource<Object> getOutput0() {
        return output0;
    }

    @Override
    public void onStart() {
        input0 = DataSource.create( getValue(0) );
        input1 = DataSource.create( getValue(1) );
    }

    @Override
    public void onEnd() {
        result = output0.iterator().next();
    }
}
