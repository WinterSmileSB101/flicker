package com.vain.flicker.model.player;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.jasminb.jsonapi.annotations.Id;
import com.github.jasminb.jsonapi.annotations.Type;

/**
 * @author Dominic Gunn (dominic@vain.gg)
 */
@Type("player")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Player {

    @Id
    private String id;

    private String name;

    @JsonProperty("stats")
    private PlayerStats playerStats;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public PlayerStats getPlayerStats() {
        return playerStats;
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
