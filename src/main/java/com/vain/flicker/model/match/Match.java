package com.vain.flicker.model.match;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;
import com.vain.flicker.api.FlickerException;
import com.vain.flicker.api.client.S3WebClient;
import com.vain.flicker.model.ApiResource;
import com.vain.flicker.model.asset.Asset;
import com.vain.flicker.model.telemetry.Telemetry;

import java.util.Date;
import java.util.List;

/**
 * @author Dominic Gunn (dominic@vain.gg)
 */
@Type("match")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Match extends ApiResource {

    private final static String TELEMETRY_ASSET = "telemetry";

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

    private Telemetry telemetry;

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

    public Telemetry getTelemetry() {
        if (telemetry == null) {
            Asset telemetryAsset = getTelemetryAsset();
            if (telemetryAsset == null) {
                throw new FlickerException("No telemetry asset available.");
            }
            telemetry = S3WebClient.getTelemetry(telemetryAsset.getUrl()).join();
        }
        return telemetry;
    }

    private Asset getTelemetryAsset() {
        for (Asset asset : assets) {
            if (TELEMETRY_ASSET.equalsIgnoreCase(asset.getName())) {
                return asset;
            }
        }
        return null;
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
                ", telemetry=" + telemetry +
                '}';
    }
}
