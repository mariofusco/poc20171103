package org.drools.dmnem.poc20171103;

import org.kie.api.runtime.rule.DataSource;
import org.kie.api.runtime.rule.RuleUnit;

public class SuggestedBlindsDTUnit implements RuleUnit {

    private DataSource<Boolean> suggestedBlindsInput1;
    private DataSource<String> suggestedBlindsInput2;

    private DataSource<String> suggestedBlindsOutput1;

    public SuggestedBlindsDTUnit() {
        super();
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


}
