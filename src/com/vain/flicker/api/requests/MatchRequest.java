package com.vain.flicker.api.requests;

import com.vain.flicker.utils.Shard;

import java.util.Collection;
import java.util.Collections;

/**
 * @author Dominic Gunn (dominic@vain.gg)
 */
public class MatchRequest {

    private Shard shard;

    private Integer offset;
    private Integer limit;

    private String matchId;
    private String sortField;

    private Collection<String> playerNames;
    private Collection<String> teamNames;

    public MatchRequest(Builder builder) {
        this.shard = builder.shard;
        this.offset = builder.offset;
        this.limit = builder.limit;
        this.matchId = builder.matchId;
        this.sortField = builder.sortField;
        this.playerNames = builder.playerNames;
        this.teamNames = builder.teamNames;
    }

    public Shard getShard() {
        return shard;
    }

    public Integer getOffset() {
        return offset;
    }

    public Integer getLimit() {
        return limit;
    }

    public String getMatchId() {
        return matchId;
    }

    public String getSortField() {
        return sortField;
    }

    public Collection<String> getPlayerNames() {
        return playerNames;
    }

    public Collection<String> getTeamNames() {
        return teamNames;
    }

    public static class Builder {

        private Shard shard;
        private Integer offset;
        private Integer limit;

        private String matchId;
        private String sortField;

        private Collection<String> playerNames;
        private Collection<String> teamNames;

        public Builder shard(Shard shard) {
            this.shard = shard;
            return this;
        }

        public Builder offset(int offset) {
            this.offset = offset;
            return this;
        }

        public Builder limit(int limit) {
            this.limit = limit;
            return this;
        }

        public Builder matchId(String matchId) {
            this.matchId = matchId;
            return this;
        }

        public Builder sortField(String sortField) {
            this.sortField = sortField;
            return this;
        }

        public Builder playerName(String playerName) {
            this.playerNames = Collections.singletonList(playerName);
            return this;
        }

        public Builder playerNames(Collection<String> playerNames) {
            this.playerNames = playerNames;
            return this;
        }

        public Builder teamName(String teamName) {
            this.teamNames = Collections.singletonList(teamName);
            return this;
        }

        public Builder teamNames(Collection<String> teamNames) {
            this.teamNames = teamNames;
            return this;
        }

        public MatchRequest build() {
            return new MatchRequest(this);
        }
    }
}
