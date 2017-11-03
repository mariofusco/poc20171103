package org.drools.dmnem.poc20171103;

import java.time.LocalDateTime;

import org.kie.api.runtime.rule.DataSource;
import org.kie.api.runtime.rule.RuleUnit;

public class MainDRDUnit implements RuleUnit {

    private DataSource<LocalDateTime> now;
    private DataSource<Boolean> isPresent;

    private DataSource<String> sunlight;
    private DataSource<String> suggestedLight;
    private DataSource<String> suggestedBlinds;

    public MainDRDUnit() {
        super();
    }

    public MainDRDUnit(DataSource<LocalDateTime> now, DataSource<Boolean> isPresent) {
        super();
        this.now = now;
        this.isPresent = isPresent;
    }

    public DataSource<LocalDateTime> getNow() {
        return now;
    }

    public DataSource<Boolean> getIsPresent() {
        return isPresent;
    }

    public DataSource<String> getSunlight() {
        return sunlight;
    }

    public DataSource<String> getSuggestedLight() {
        return suggestedLight;
    }

    public DataSource<String> getSuggestedBlinds() {
        return suggestedBlinds;
    }

    @Override
    public String toString() {
        return "MainDRDUnit [now=" + now.iterator().next() + ", isPresent=" + isPresent.iterator().next() + ", sunlight=" + sunlight.iterator().next() + ", suggestedLight=" + suggestedLight.iterator().next() +
               ", suggestedBlinds=" + suggestedBlinds.iterator().next() + "]";
    }

}
