package org.drools.dmnem.poc20171103;

import java.time.LocalDateTime;

import org.kie.api.runtime.rule.DataSource;
import org.kie.api.runtime.rule.RuleUnit;

public class SunlightInputUnit implements RuleUnit {

    private DataSource<LocalDateTime> now;

    private DataSource<Integer> sunlightInput1;

    public SunlightInputUnit() {
        super();
    }

    public SunlightInputUnit(DataSource<LocalDateTime> now) {
        super();
        this.now = now;
    }

    public DataSource<LocalDateTime> getNow() {
        return now;
    }

    public DataSource<Integer> getSunlightInput1() {
        return sunlightInput1;
    }

}
