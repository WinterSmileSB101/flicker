package com.vain.flicker.model.telemetry.events;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.vain.flicker.api.client.deserializers.TelemetryDeserializer;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Dominic Gunn (dominic@vain.gg)
 */
@JsonDeserialize(using = TelemetryDeserializer.class)
public abstract class TelemetryEvent {

    private final static String KEY_TEAM = "Team";
    private final static String KY_ACTOR = "Actor";

    protected Date time;
    protected String type;
    protected Map<String, Object> payload = new HashMap();

    public TelemetryEvent() {

    }

    public TelemetryEvent(TelemetryEvent telemetryEvent) {
        this.time = telemetryEvent.getTime();
        this.type = telemetryEvent.getType();
        this.payload = telemetryEvent.getPayload();
    }

    public String getTeam() {
        return (String) payload.get(KEY_TEAM);
    }

    public String getActor() {
        return (String) payload.get(KY_ACTOR);
    }

    public Date getTime() {
        return time;
    }

    public String getType() {
        return type;
    }

    public Map<String, Object> getPayload() {
        return payload;
    }

    @Override
    public String toString() {
        return "TelemetryEvent{" +
                "time=" + time +
                ", type='" + type + '\'' +
                ", payload=" + payload +
                '}';
    }
}
