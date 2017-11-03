package org.drools.dmnem.poc20171103;

import org.kie.api.runtime.rule.DataSource;
import org.kie.api.runtime.rule.RuleUnit;

public class SuggestedLightInputUnit implements RuleUnit {

    private DataSource<Boolean> isPresent;
    private DataSource<String> sunlight;

    private DataSource<Boolean> suggestedLightInput1;
    private DataSource<String> suggestedLightInput2;

    public SuggestedLightInputUnit() {
        super();
    }

    public DataSource<Boolean> getIsPresent() {
        return isPresent;
    }

    public DataSource<String> getSunlight() {
        return sunlight;
    }

    public DataSource<Boolean> getSuggestedLightInput1() {
        return suggestedLightInput1;
    }

    public DataSource<String> getSuggestedLightInput2() {
        return suggestedLightInput2;
    }

}
