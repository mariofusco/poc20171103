package org.drools.dmnem.poc20171103;

import org.kie.api.runtime.rule.DataSource;
import org.kie.api.runtime.rule.RuleUnit;

public class SuggestedLightDTUnit implements RuleUnit {

    private DataSource<Boolean> suggestedLightInput1;
    private DataSource<String> suggestedLightInput2;

    private DataSource<String> suggestedLightOutput1;

    public SuggestedLightDTUnit() {
        super();
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

}
