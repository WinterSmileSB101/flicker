package com.vain.flicker.model.telemetry.events;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * @author Dominic Gunn (dominic@vain.gg)
 */
@JsonSerialize(as = SellItemTelemetryEvent.class)
@JsonDeserialize(as = SellItemTelemetryEvent.class)
public class SellItemTelemetryEvent extends TelemetryEvent {

    private final static String KEY_ITEM = "Item";
    private final static String KEY_COST = "Cost";

    public String getItem() {
        return (String) payload.get(KEY_ITEM);
    }

    public int getCost() {
        return (int) payload.get(KEY_COST);
    }
}
