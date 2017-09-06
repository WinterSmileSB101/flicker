package com.vain.flicker.model.telemetry.events;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * @author Dominic Gunn (dominic@vain.gg)
 */
@JsonSerialize(as = DealDamageTelemetryEvent.class)
@JsonDeserialize(as = DealDamageTelemetryEvent.class)
public class DealDamageTelemetryEvent extends TelemetryEvent {

    private final static String KEY_TARGET = "Target";
    private final static String KEY_SOURCE = "Source";

    private final static String KEY_DEALT = "Dealt";
    private final static String KEY_DAMAGE = "Damage";

    private final static String KEY_IS_HERO = "IsHero";
    private final static String KEY_TARGET_IS_HERO = "TargetIsHero";

    public String getTarget() {
        return (String) payload.get(KEY_TARGET);
    }

    public String getSource() {
        return (String) payload.get(KEY_SOURCE);
    }

    public int getDealt() {
        return (int) payload.get(KEY_DEALT);
    }

    public int getDamage() {
        return (int) payload.get(KEY_DAMAGE);
    }

    public boolean isHero() {
        return (boolean) payload.get(KEY_IS_HERO);
    }

    public boolean isTargetHero() {
        return (boolean) payload.get(KEY_TARGET_IS_HERO);
    }
}
