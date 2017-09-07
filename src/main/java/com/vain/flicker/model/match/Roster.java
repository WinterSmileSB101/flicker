package com.vain.flicker.model.match;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.jasminb.jsonapi.JSONAPIDocument;
import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;
import com.vain.flicker.model.ApiResource;

import java.util.List;

/**
 * @author Dominic Gunn (dominic@vain.gg)
 */
@Type("roster")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Roster extends ApiResource<Roster> {

    @JsonProperty("stats")
    private RosterStats rosterStats;

    @Relationship("participants")
    private List<Participant> participants;

    @Override
    protected JSONAPIDocument<Roster> getDocument() {
        return new JSONAPIDocument<>(this);
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
