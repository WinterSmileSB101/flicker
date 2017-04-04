package com.vain.flicker.model.telemetry.events;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * @author Dominic Gunn (dominic@vain.gg)
 */
@JsonSerialize(as = LevelUpTelemetryEvent.class)
@JsonDeserialize(as = LevelUpTelemetryEvent.class)
public class LevelUpTelemetryEvent extends TelemetryEvent {

    private final static String KEY_LEVEL = "Level";
    private final static String KEY_LIFETIME_GOLD = "LifetimeGold";

    public int getLevel() {
        return (int) payload.get(KEY_LEVEL);
    }

    public int getLifeTimeGold() {
        return (int) payload.get(KEY_LIFETIME_GOLD);
    }
}
