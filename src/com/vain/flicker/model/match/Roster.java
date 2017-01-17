package com.vain.flicker.model.match;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.jasminb.jsonapi.annotations.Id;
import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;

import java.util.List;

/**
 * @author Dominic Gunn (dominic@vain.gg)
 */
@Type("roster")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Roster {

    @Id
    private String id;

    @JsonProperty("stats")
    private RosterStats rosterStats;

    @Relationship("participants")
    private List<Participant> participants;

    public String getId() {
        return id;
    }

    public RosterStats getRosterStats() {
        return rosterStats;
    }

    public List<Participant> getParticipants() {
        return participants;
    }

    @Override
    public String toString() {
        return "Roster{" +
                "id='" + id + '\'' +
                ", rosterStats=" + rosterStats +
                ", participants=" + participants +
                '}';
    }
}
