package com.vain.flicker.model.match;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.jasminb.jsonapi.JSONAPIDocument;
import com.github.jasminb.jsonapi.annotations.*;
import com.vain.flicker.model.ApiResource;
import com.vain.flicker.model.player.Player;
import com.vain.flicker.utils.ActorHelper;

/**
 * @author Dominic Gunn (dominic@vain.gg)
 */
@Type("participant")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Participant extends ApiResource<Participant> {

    @Relationship("player")
    private Player player;

    private String actor;

    @JsonProperty("stats")
    private ParticipantStats participantStats;

    public Participant() {
    }

    @Override
    protected JSONAPIDocument<Participant> getDocument() {
        return new JSONAPIDocument<>(this);
    }

    public String getActor() {
        return actor;
    }

    public String getCleanActor() { return ActorHelper.cleanActorName(actor); }

    public Player getPlayer() {
        return player;
    }

    public ParticipantStats getParticipantStats() {
        return participantStats;
    }

    @Override
    public String toString() {
        return "Participant{" +
                "id='" + id + '\'' +
                ", player=" + player +
                ", actor='" + actor + '\'' +
                ". cleanActor='" + getCleanActor() + '\'' +
                ", participantStats=" + participantStats +
                '}';
    }
}
