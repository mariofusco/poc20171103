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

public class SuggestedLightDTUnit extends DMNUnit {

    private Boolean isPresent;
    private String sunlight;

    private DataSource<Boolean> suggestedLightInput1 = DataSource.create();
    private DataSource<String> suggestedLightInput2 = DataSource.create();

    private DataSource<String> suggestedLightOutput1 = DataSource.create();

    private String suggestedLight;

    public SuggestedLightDTUnit(DMNContext context) {
        super(context);
        this.isPresent = (Boolean) context.get("is Present");
        this.sunlight = (String) context.get("sunlight");
    }

    public DataSource<Boolean> getSuggestedLightInput1() {
        return suggestedLightInput1;
    }

    public DataSource<String> getSuggestedLightInput2() {
        return suggestedLightInput2;
    }

    public DataSource<String> getSuggestedLightOutput1() {
        return suggestedLightOutput1;
    }

    public String getSuggestedLight() {
        return suggestedLight;
    }

    @Override
    public void onStart() {
        suggestedLightInput1.insert(isPresent);
        suggestedLightInput2.insert(sunlight);
    }

    @Override
    public void onEnd() {
        suggestedLight = suggestedLightOutput1.iterator().next();
    }

    @Override
    public Object getResult() {
        return getSuggestedLight();
    }

}
