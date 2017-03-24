package com.vain.flicker.model.telemetry.events;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * @author Dominic Gunn (dominic@vain.gg)
 */
@JsonDeserialize(as = EarnXPTelemetryEvent.class)
public class EarnXPTelemetryEvent extends TelemetryEvent {

    private final static String KEY_SOURCE = "Source";
    private final static String KEY_AMOUNT = "Amount";
    private final static String KEY_SHARED_WITH = "Shared With";

    public int getAmount() {
        return (int) payload.get(KEY_AMOUNT);
    }

    public String getSource() {
        return (String) payload.get(KEY_SOURCE);
    }

    public int getSharedWith() {
        return (int) payload.get(KEY_SHARED_WITH);
    }
}
