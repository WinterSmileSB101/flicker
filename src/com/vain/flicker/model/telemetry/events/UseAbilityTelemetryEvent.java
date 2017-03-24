package com.vain.flicker.model.telemetry.events;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * @author Dominic Gunn (dominic@vain.gg)
 */
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
}
