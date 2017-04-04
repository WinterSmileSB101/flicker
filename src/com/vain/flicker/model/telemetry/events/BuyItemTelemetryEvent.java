package com.vain.flicker.model.telemetry.events;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.vain.flicker.api.client.deserializers.TelemetryDeserializer;

/**
 * @author Dominic Gunn (dominic@vain.gg)
 */
@JsonSerialize(as = BuyItemTelemetryEvent.class)
@JsonDeserialize(as = BuyItemTelemetryEvent.class)
public class BuyItemTelemetryEvent extends TelemetryEvent {

    private final static String KEY_ITEM = "Item";
    private final static String KEY_COST = "Cost";

    public String getItem() {
        return (String) payload.get(KEY_ITEM);
    }

    public int getCost() {
        return (int) payload.get(KEY_COST);
    }
}
