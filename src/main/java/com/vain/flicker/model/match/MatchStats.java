package com.vain.flicker.model.match;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Dominic Gunn (dominic@vain.gg)
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MatchStats {

    private String queue;
    private String endGameReason;

    public String getQueue() {
        return queue;
    }

    public String getEndGameReason() {
        return endGameReason;
    }

    @Override
    public String toString() {
        return "MatchStats{" +
                "queue='" + queue + '\'' +
                ", endGameReason='" + endGameReason + '\'' +
                '}';
    }
}
