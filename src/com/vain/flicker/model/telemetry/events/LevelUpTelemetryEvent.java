package com.vain.flicker.model.telemetry.events;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * @author Dominic Gunn (dominic@vain.gg)
 */
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
