package com.vain.flicker.model.match;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Dominic Gunn (dominic@vain.gg)
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RosterStats {

    private int gold;
    private String side;

    private int acesEarned;
    private int heroKills;

    private int turretKills;
    private int turretsRemaining;
    private int krakenCaptures;

    public int getGold() {
        return gold;
    }

    public String getSide() {
        return side;
    }

    public int getAcesEarned() {
        return acesEarned;
    }

    public int getHeroKills() {
        return heroKills;
    }

    public int getTurretKills() {
        return turretKills;
    }

    public int getTurretsRemaining() {
        return turretsRemaining;
    }

    public int getKrakenCaptures() {
        return krakenCaptures;
    }

    @Override
    public String toString() {
        return "RosterStats{" +
                "gold=" + gold +
                ", side='" + side + '\'' +
                ", acesEarned=" + acesEarned +
                ", heroKills=" + heroKills +
                ", turretKills=" + turretKills +
                ", turretsRemaining=" + turretsRemaining +
                ", krakenCaptures=" + krakenCaptures +
                '}';
    }
}
