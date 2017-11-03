package org.drools.dmnem.poc20171103;

import org.kie.api.runtime.rule.DataSource;
import org.kie.api.runtime.rule.RuleUnit;

public class SuggestedBlindsInputUnit implements RuleUnit {

    private DataSource<Boolean> isPresent;
    private DataSource<String> sunlight;

    private DataSource<Boolean> suggestedBlindsInput1;
    private DataSource<String> suggestedBlindsInput2;

    public SuggestedBlindsInputUnit() {
        super();
    }

    public DataSource<Boolean> getIsPresent() {
        return isPresent;
    }

    public DataSource<String> getSunlight() {
        return sunlight;
    }

    public DataSource<Boolean> getSuggestedBlindsInput1() {
        return suggestedBlindsInput1;
    }

    public DataSource<String> getSuggestedBlindsInput2() {
        return suggestedBlindsInput2;
    }


}
