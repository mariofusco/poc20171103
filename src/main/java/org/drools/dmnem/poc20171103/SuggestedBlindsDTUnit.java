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
import org.kie.dmn.api.core.DMNContext;
import temp.kiedmn.DMNUnit;

public class SuggestedBlindsDTUnit extends DMNUnit {

    private Boolean isPresent;
    private String sunlight;

    private DataSource<Boolean> suggestedBlindsInput1 = DataSource.create();
    private DataSource<String> suggestedBlindsInput2 = DataSource.create();

    private DataSource<String> suggestedBlindsOutput1 = DataSource.create();

    private String suggestedBlinds;

    public SuggestedBlindsDTUnit(DMNContext context) {
        super(context);
        this.isPresent = (Boolean) context.get("is Present");
        this.sunlight = (String) context.get("sunlight");
    }

    public DataSource<Boolean> getSuggestedBlindsInput1() {
        return suggestedBlindsInput1;
    }

    public DataSource<String> getSuggestedBlindsInput2() {
        return suggestedBlindsInput2;
    }

    public DataSource<String> getSuggestedBlindsOutput1() {
        return suggestedBlindsOutput1;
    }

    public String getSuggestedBlinds() {
        return suggestedBlinds;
    }

    @Override
    public void onStart() {
        suggestedBlindsInput1.insert(isPresent);
        suggestedBlindsInput2.insert(sunlight);
    }

    @Override
    public void onEnd() {
        suggestedBlinds = suggestedBlindsOutput1.iterator().next();
    }

    @Override
    public Object getResult() {
        return getSuggestedBlinds();
    }

}
