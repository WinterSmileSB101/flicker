package com.vain.flicker.model.match;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.github.jasminb.jsonapi.annotations.Type;
import com.vain.flicker.model.ApiResource;

/**
 * @author Dominic Gunn (dominic@vain.gg)
 */
@Type("team")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Team extends ApiResource {

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Team{" +
                "id='" + id + '\'' +
                '}';
    }
}
