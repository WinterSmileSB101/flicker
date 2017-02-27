package com.vain.flicker.model.player;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Dominic Gunn (dominic@vain.gg)
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlayerStats {

    private int wins;
    private int winStreak;
    private int lossStreak;

    private int xp;
    private int level;
    private double lifetimeGold;

    private int played;
    private int playedRanked;

    public int getWins() {
        return wins;
    }

    public int getWinStreak() {
        return winStreak;
    }

    public int getLossStreak() {
        return lossStreak;
    }

    public int getXp() {
        return xp;
    }

    public int getLevel() {
        return level;
    }

    public double getLifetimeGold() {
        return lifetimeGold;
    }

    public int getPlayed() {
        return played;
    }

    public int getPlayedRanked() {
        return playedRanked;
    }

    @Override
    public String toString() {
        return "PlayerStats{" +
                "playedRanked=" + playedRanked +
                ", played=" + played +
                ", lifetimeGold=" + lifetimeGold +
                ", level=" + level +
                ", xp=" + xp +
                ", lossStreak=" + lossStreak +
                ", winStreak=" + winStreak +
                ", wins=" + wins +
                '}';
    }
}
