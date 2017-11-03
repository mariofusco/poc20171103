package org.drools.dmnem.poc20171103;

import org.kie.api.runtime.rule.DataSource;
import org.kie.api.runtime.rule.RuleUnit;

public class SuggestedLightOutputUnit implements RuleUnit {

    private DataSource<String> suggestedLightOutput1;

    private DataSource<String> suggestedLight;

    public SuggestedLightOutputUnit() {
        super();
    }

    public DataSource<String> getSuggestedLightOutput1() {
        return suggestedLightOutput1;
    }

    public DataSource<String> getSuggestedLight() {
        return suggestedLight;
    }

}
