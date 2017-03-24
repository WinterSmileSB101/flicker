package com.vain.flicker.model.telemetry.events;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * @author Dominic Gunn (dominic@vain.gg)
 */
@JsonDeserialize(as = PlayerFirstSpawnTelemetryEvent.class)
public class PlayerFirstSpawnTelemetryEvent extends TelemetryEvent {

}
