package com.vain.flicker.model.telemetry.events;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * @author Dominic Gunn (dominic@vain.gg)
 */
@JsonSerialize(as = UseAbilityTelemetryEvent.class)
@JsonDeserialize(as = UseAbilityTelemetryEvent.class)
public class UseAbilityTelemetryEvent extends TelemetryEvent {

    private final static String KEY_ABILITY = "Ability";
    private final static String KEY_POSITION = "Position";

    public int getAbility() {
        return (int) payload.get(KEY_ABILITY);
    }

    public int[] getPosition() {
        return (int[]) payload.get(KEY_POSITION);
    }

    public String getTargetActor() {
        return (String) payload.get(KEY_TARGET_ACTOR);
    }

    public int[] getTargetPosition() {
        return (int[]) payload.get(KEY_TARGET_POSITION);
    }
}
