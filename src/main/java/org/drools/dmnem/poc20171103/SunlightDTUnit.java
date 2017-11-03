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

import java.time.LocalDateTime;

import org.kie.api.runtime.rule.DataSource;
import org.kie.api.runtime.rule.RuleUnit;

public class SunlightDTUnit implements RuleUnit {

    private DataSource<LocalDateTime> now;

    private DataSource<Integer> sunlightInput1;
    private DataSource<String> sunlightOutput1;

    private DataSource<String> sunlight;

    public SunlightDTUnit() {
        super();
    }

    public DataSource<Integer> getSunlightInput1() {
        return sunlightInput1;
    }

    public DataSource<String> getSunlightOutput1() {
        return sunlightOutput1;
    }

    public DataSource<LocalDateTime> getNow() {
        return now;
    }

    public DataSource<String> getSunlight() {
        return sunlight;
    }

    @Override
    public void onStart() {
        // input1: now.hour:
        sunlightInput1.insert(now.iterator().next().getHour());
    }

    @Override
    public void onEnd() {
        // output1: sunlight
        sunlight.insert(sunlightOutput1.iterator().next());
    }


}
