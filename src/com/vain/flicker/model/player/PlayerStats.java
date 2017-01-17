package com.vain.flicker.model.player;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Dominic Gunn (dominic@vain.gg)
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlayerStats {

    private int winStreak;
    private int lossStreak;

    private int lifetimeGold;

    public int getWinStreak() {
        return winStreak;
    }

    public int getLossStreak() {
        return lossStreak;
    }

    public int getLifetimeGold() {
        return lifetimeGold;
    }

    @Override
    public String toString() {
        return "PlayerStats{" +
                "winStreak=" + winStreak +
                ", lossStreak=" + lossStreak +
                ", lifetimeGold=" + lifetimeGold +
                '}';
    }
}
