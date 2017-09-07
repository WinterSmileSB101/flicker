package com.vain.flicker.model.sample;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.jasminb.jsonapi.JSONAPIDocument;
import com.github.jasminb.jsonapi.annotations.Type;
import com.vain.flicker.model.ApiResource;
import com.vain.flicker.model.match.Participant;

import java.util.Date;

/**
 * @author Dominic Gunn (dominic@vain.gg)
 */
@Type("sample")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Sample extends ApiResource<Sample> {

    @JsonProperty("URL")
    private String url;

    private String shardId;
    private String titleId;

    private Date createdAt;
    private Date t0;
    private Date t1;

    @Override
    protected JSONAPIDocument<Sample> getDocument() {
        return new JSONAPIDocument<>(this);
    }

    public String getUrl() {
        return url;
    }

    public String getShardId() {
        return shardId;
    }

    public String getTitleId() {
        return titleId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getT0() {
        return t0;
    }

    public Date getT1() {
        return t1;
    }

    @Override
    public String toString() {
        return "Sample{" +
                "t1=" + t1 +
                ", t0=" + t0 +
                ", createdAt=" + createdAt +
                ", titleId='" + titleId + '\'' +
                ", shardId='" + shardId + '\'' +
                ", url='" + url + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
