package com.vain.flicker.model.telemetry.events;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * @author Dominic Gunn (dominic@vain.gg)
 */
@JsonSerialize(as = ExecutedTelemetryEvent.class)
@JsonDeserialize(as = ExecutedTelemetryEvent.class)
public class ExecutedTelemetryEvent extends TelemetryEvent {

    private final static String KEY_GOLD = "Gold";
    private final static String KEY_POSITION = "Position";

    private final static String KEY_IS_HERO = "IsHero";
    private final static String KEY_TARGET_IS_HERO = "TargetIsHero";

    private final static String KEY_KILLED = "Killed";
    private final static String KEY_KILLED_TEAM = "KilledTeam";

    public int getGold() {
        return (int) payload.get(KEY_GOLD);
    }

    public int[] getPosition() {
        return (int[]) payload.get(KEY_POSITION);
    }

    public boolean isHero() {
        return (boolean) payload.get(KEY_IS_HERO);
    }

    public boolean isTargetHero() {
        return (boolean) payload.get(KEY_TARGET_IS_HERO);
    }

    public String getKilled() {
        return (String) payload.get(KEY_KILLED);
    }

    public String getKilledTeam() {
        return (String) payload.get(KEY_KILLED_TEAM);
    }
}
