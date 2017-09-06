package com.vain.flicker.model.telemetry.events;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * @author Dominic Gunn (dominic@vain.gg)
 */
@JsonSerialize(as = PlayerFirstSpawnTelemetryEvent.class)
@JsonDeserialize(as = PlayerFirstSpawnTelemetryEvent.class)
public class PlayerFirstSpawnTelemetryEvent extends TelemetryEvent {

}
