package com.vain.flicker.utils;

/**
 * @author Dominic Gunn (dominic@vain.gg)
 */
public enum Shard {
    CN("China", "cn"),
    CN_TOURNAMENT("China Tournament", "tournament-cn"),

    EU("Europe", "eu"),
    EU_TOURNAMENT("EU Tournament Shard", "tournament-eu"),

    NA("North America", "na"),
    NA_TOURNAMENT("NA Tournament Shard", "tournament-na"),

    SEA("South East Asia", "sg"),
    SEA_TOURNAMENT("SEA Tournament Shard", "tournament-sg"),

    EA("East Asia", "ea"),
    EA_TOURNAMENT("EA Tournament Shard", "tournament-ea"),

    SA("South America", "sa"),
    SA_TOURNAMENT("SA Tournament Shard", "tournament-sa")

    ;

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
