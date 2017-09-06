package com.vain.flicker.model.telemetry.events;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * @author Dominic Gunn (dominic@vain.gg)
 */
@JsonSerialize(as = GoldFromExecutionTelemetryEvent.class)
@JsonDeserialize(as = GoldFromExecutionTelemetryEvent.class)
public class GoldFromExecutionTelemetryEvent extends TelemetryEvent {

    private final static String KEY_AMOUNT = "Amount";

    public int getAmount() {
        return (int) payload.get(KEY_AMOUNT);
    }
}
