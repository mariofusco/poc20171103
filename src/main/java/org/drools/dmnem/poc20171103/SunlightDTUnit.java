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

import java.math.BigDecimal;

import org.kie.api.runtime.rule.DataSource;
import temp.kiedmn.DMNUnit;

public class SunlightDTUnit extends DMNUnit {

    private DataSource<BigDecimal> sunlightInput1;
    private DataSource<String> sunlightOutput1 = DataSource.create();

    private String sunlight;

    public DataSource<BigDecimal> getSunlightInput1() {
        return sunlightInput1;
    }

    public DataSource<String> getSunlightOutput1() {
        return sunlightOutput1;
    }

    public String getSunlight() {
        return sunlight;
    }

    @Override
    public void onStart() {
        sunlightInput1 = DataSource.create( (BigDecimal) getValue(0) );
    }

    @Override
    public void onEnd() {
        sunlight = sunlightOutput1.iterator().next();
    }

    @Override
    public String getResult() {
        return getSunlight();
    }

}
