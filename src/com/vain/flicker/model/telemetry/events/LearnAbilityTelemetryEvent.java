package com.vain.flicker.model.telemetry.events;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * @author Dominic Gunn (dominic@vain.gg)
 */
@JsonSerialize(as = LearnAbilityTelemetryEvent.class)
@JsonDeserialize(as = LearnAbilityTelemetryEvent.class)
public class LearnAbilityTelemetryEvent extends TelemetryEvent {

    private final static String KEY_LEVEL = "Level";
    private final static String KEY_ABILITY = "Ability";

    public int getLevel() {
        return (int) payload.get(KEY_LEVEL);
    }

    public int getAbility() {
        return (int) payload.get(KEY_ABILITY);
    }
}
