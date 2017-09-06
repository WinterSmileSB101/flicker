package com.vain.flicker.model.player;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;
import com.vain.flicker.model.ApiResource;
import com.vain.flicker.model.asset.Asset;

import java.util.List;

/**
 * @author Dominic Gunn (dominic@vain.gg)
 */
@Type("player")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Player extends ApiResource {

    private String name;

    @JsonProperty("stats")
    private PlayerStats playerStats;

    @Relationship("assets")
    private List<Asset> assets;

    public String getName() {
        return name;
    }

    public PlayerStats getPlayerStats() {
        return playerStats;
    }

    public List<Asset> getAssets() {
        return assets;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", playerStats=" + playerStats +
                '}';
    }
}
