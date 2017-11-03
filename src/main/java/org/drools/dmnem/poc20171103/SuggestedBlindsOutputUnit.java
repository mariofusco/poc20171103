package org.drools.dmnem.poc20171103;

import org.kie.api.runtime.rule.DataSource;
import org.kie.api.runtime.rule.RuleUnit;

public class SuggestedBlindsOutputUnit implements RuleUnit {

    private DataSource<String> suggestedBlindsOutput1;

    private DataSource<String> suggestedBlinds;

    public SuggestedBlindsOutputUnit() {
        super();
    }

    public DataSource<String> getSuggestedBlindsOutput1() {
        return suggestedBlindsOutput1;
    }

    public DataSource<String> getSuggestedBlinds() {
        return suggestedBlinds;
    }


}
