package org.drools.dmnem.poc20171103;

import org.kie.api.runtime.rule.DataSource;
import org.kie.api.runtime.rule.RuleUnit;

public class SunlightOutputUnit implements RuleUnit {

    private DataSource<Integer> sunlightOutput1;

    private DataSource<String> sunlight;

    public SunlightOutputUnit() {
        super();
    }

    public DataSource<Integer> getSunlightOutput1() {
        return sunlightOutput1;
    }

    public DataSource<String> getSunlight() {
        return sunlight;
    }
}
