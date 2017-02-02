package com.vain.flicker.utils;

/**
 * @author Dominic Gunn (dominic@vain.gg)
 */
public enum Shard {
    NA("North America"), EU("Europe"), SEA("South East Asia"), EA("East Asia"), SA("South America"), CN("China");

    private String shardRegion;

    Shard(String shardRegion) {
        this.shardRegion = shardRegion;
    }

    public String getShardRegion() {
        return shardRegion;
    }

    public static Shard getShardByShortCode(String shortCode) {
        for (Shard shard : Shard.values()) {
            if (shard.name().equalsIgnoreCase(shortCode)) {
                return shard;
            }
        }
        return null;
    }
}
