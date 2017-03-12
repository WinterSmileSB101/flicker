package com.vain.flicker.model.asset;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.jasminb.jsonapi.annotations.Id;
import com.github.jasminb.jsonapi.annotations.Type;

import java.util.Date;

/**
 * @author Dominic Gunn (dominic@vain.gg)
 */
@Type("asset")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Asset {

    @Id
    private String id;

    @JsonProperty("URL")
    private String url;

    @JsonProperty("filename")
    private String fileName;

    private String name;
    private String description;

    public Date createdAt;
    private String contentType;

    public String getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public String getFileName() {
        return fileName;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public String getContentType() {
        return contentType;
    }

    @Override
    public String toString() {
        return "Asset{" +
                "id='" + id + '\'' +
                ", url='" + url + '\'' +
                ", fileName='" + fileName + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", createdAt=" + createdAt +
                ", contentType='" + contentType + '\'' +
                '}';
    }
}
