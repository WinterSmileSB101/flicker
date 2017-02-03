package com.vain.flicker.utils;

/**
 * @author Dominic Gunn (dominic@vain.gg)
 */
public enum Shard {
    NA("North America", "na"), EU("Europe", "eu"), SEA("South East Asia", "sg"),
    EA("East Asia", "ea"), SA("South America", "sa"), CN("China", "cn");

    private String shardRegion;
    private String shortCode;

    Shard(String shardRegion, String shortCode) {
        this.shardRegion = shardRegion;
        this.shortCode = shortCode;
    }

    public String getShortCode() {
        return shortCode;
    }

    public String getShardRegion() {
        return shardRegion;
    }

    public static Shard getShardByShortCode(String shortCode) {
        for (Shard shard : Shard.values()) {
            if (shard.shortCode.equalsIgnoreCase(shortCode)) {
                return shard;
            }
        }
        return null;
    }
}
