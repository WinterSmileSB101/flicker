package com.vain.flicker.model.match;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.jasminb.jsonapi.annotations.Id;
import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;
import com.vain.flicker.model.asset.Asset;

import java.util.Date;
import java.util.List;

/**
 * @author Dominic Gunn (dominic@vain.gg)
 */
@Type("match")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Match {

    @Id
    private String id;
    private String titleId;

    @JsonProperty("stats")
    private MatchStats matchStats;

    private Date createdAt;
    private Long duration;

    private String shardId;
    private String gameMode;
    private String patchVersion;

    @Relationship("rosters")
    private List<Roster> roster;

    @Relationship("assets")
    private List<Asset> assets;

    public String getId() {
        return id;
    }

    public String getTitleId() {
        return titleId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Long getDuration() {
        return duration;
    }

    public String getShardId() {
        return shardId;
    }

    public String getGameMode() {
        return gameMode;
    }

    public String getPatchVersion() {
        return patchVersion;
    }

    public List<Roster> getRoster() {
        return roster;
    }

    public List<Asset> getAssets() {
        return assets;
    }

    public MatchStats getMatchStats() {
        return matchStats;
    }

    @Override
    public String toString() {
        return "Match{" +
                "id='" + id + '\'' +
                ", titleId='" + titleId + '\'' +
                ", matchStats=" + matchStats +
                ", createdAt=" + createdAt +
                ", duration=" + duration +
                ", shardId='" + shardId + '\'' +
                ", gameMode='" + gameMode + '\'' +
                ", patchVersion='" + patchVersion + '\'' +
                ", roster=" + roster +
                ", assets=" + assets +
                '}';
    }
}
