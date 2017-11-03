package org.drools.dmnem.poc20171103;

import org.kie.api.runtime.rule.DataSource;
import org.kie.api.runtime.rule.RuleUnit;

public class SunlightDTUnit implements RuleUnit {

    private DataSource<Integer> sunlightInput1;
    private DataSource<Integer> sunlightOutput1;

    public SunlightDTUnit() {
        super();
    }

    public DataSource<Integer> getSunlightInput1() {
        return sunlightInput1;
    }

    public DataSource<Integer> getSunlightOutput1() {
        return sunlightOutput1;
    }

}
