package com.vain.flicker.model.match;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.github.jasminb.jsonapi.annotations.Id;
import com.github.jasminb.jsonapi.annotations.Type;

/**
 * @author Dominic Gunn (dominic@vain.gg)
 */
@Type("team")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Team {

    @Id
    private String id;

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
