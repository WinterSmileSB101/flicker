package com.vain.flicker.model.match;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Arrays;
import java.util.Map;

/**
 * @author Dominic Gunn (dominic@vain.gg)
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ParticipantStats {

    private int level;
    private String skinKey;
    private Integer skillTier;

    private int wins;
    private boolean winner;

    private int kills;
    private int deaths;
    private int assists;

    private int karmaLevel;
    private boolean wentAfk;
    private long firstAfkTime;

    private double farm;
    private int minionKills;
    private int jungleKills;
    private int nonJungleMinionKills;

    private int turretCaptures;
    private int krakenCaptures;
    private int goldMineCaptures;
    private int crystalMineCaptures;

    private String[] items;
    private Map<String, Integer> itemUses;
    private Map<String, Integer> itemSells;
    private Map<String, Integer> itemGrants;

    public int getLevel() {
        return level;
    }

    public Integer getSkillTier() {
        return skillTier;
    }

    public String getSkinKey() {
        return skinKey;
    }

    public int getWins() {
        return wins;
    }

    public boolean isWinner() {
        return winner;
    }

    public int getKills() {
        return kills;
    }

    public int getDeaths() {
        return deaths;
    }

    public int getAssists() {
        return assists;
    }

    public int getKarmaLevel() {
        return karmaLevel;
    }

    public boolean isWentAfk() {
        return wentAfk;
    }

    public long getFirstAfkTime() {
        return firstAfkTime;
    }

    public double getFarm() {
        return farm;
    }

    public int getMinionKills() {
        return minionKills;
    }

    public int getJungleKills() {
        return jungleKills;
    }

    public int getNonJungleMinionKills() {
        return nonJungleMinionKills;
    }

    public int getTurretCaptures() {
        return turretCaptures;
    }

    public int getKrakenCaptures() {
        return krakenCaptures;
    }

    public int getGoldMineCaptures() {
        return goldMineCaptures;
    }

    public int getCrystalMineCaptures() {
        return crystalMineCaptures;
    }

    public String[] getItems() {
        return items;
    }

    public Map<String, Integer> getItemUses() {
        return itemUses;
    }

    public Map<String, Integer> getItemSells() {
        return itemSells;
    }

    public Map<String, Integer> getItemGrants() {
        return itemGrants;
    }

    @Override
    public String toString() {
        return "ParticipantStats{" +
                "level=" + level +
                ", skinKey='" + skinKey + '\'' +
                ", wins=" + wins +
                ", winner=" + winner +
                ", kills=" + kills +
                ", deaths=" + deaths +
                ", assists=" + assists +
                ", karmaLevel=" + karmaLevel +
                ", wentAfk=" + wentAfk +
                ", firstAfkTime=" + firstAfkTime +
                ", farm=" + farm +
                ", minionKills=" + minionKills +
                ", jungleKills=" + jungleKills +
                ", nonJungleMinionKills=" + nonJungleMinionKills +
                ", turretCaptures=" + turretCaptures +
                ", krakenCaptures=" + krakenCaptures +
                ", goldMineCaptures=" + goldMineCaptures +
                ", crystalMineCaptures=" + crystalMineCaptures +
                ", items=" + Arrays.toString(items) +
                ", itemUses=" + itemUses +
                ", itemSells=" + itemSells +
                ", itemGrants=" + itemGrants +
                '}';
    }
}
