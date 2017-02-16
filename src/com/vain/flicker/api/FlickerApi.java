package com.vain.flicker.api;

import com.vain.flicker.api.requests.MatchRequest;
import com.vain.flicker.model.player.Player;
import com.vain.flicker.model.sample.Sample;
import com.vain.flicker.utils.Shard;
import com.vain.flicker.model.match.Match;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author Dominic Gunn (dominic@vain.gg)
 */
public class FlickerApi {

    private FlickerAsyncApi flickerAsyncApi;

    public FlickerApi(String apiKey) {
        flickerAsyncApi = new FlickerAsyncApi(apiKey);
    }

    public List<Sample> getSamples() {
        return forceGet(flickerAsyncApi.getSamples());
    }

    public List<Sample> getSamples(Shard shard) {
        return forceGet(flickerAsyncApi.getSamples(shard));
    }

    public Player getPlayerById(String playerId) {
        return forceGet(flickerAsyncApi.getPlayerById(playerId));
    }

    public Player getPlayerById(String playerId, Shard shard) {
        return forceGet(flickerAsyncApi.getPlayerById(playerId, shard));
    }

    public Match getMatch(String matchId) {
        return forceGet(flickerAsyncApi.getMatch(matchId));
    }

    public Match getMatch(String matchId, Shard shard) {
        return forceGet(flickerAsyncApi.getMatch(matchId, shard));
    }

    public List<Match> getMatches() {
        return forceGet(flickerAsyncApi.getMatches());
    }

    public List<Match> getMatches(MatchRequest matchRequest) {
        return forceGet(flickerAsyncApi.getMatches(matchRequest));
    }

    private static <T> T forceGet(CompletableFuture<T> future) {
        try {
            return future.get();
        } catch (InterruptedException e) {
            throw new FlickerException(e.getMessage(), e);
        } catch (ExecutionException e) {
            if (e.getCause() instanceof FlickerException) {
                throw (FlickerException) e.getCause();
            }
            throw new FlickerException(e.getMessage(), e);
        }
    }

    public Shard getShard() {
        return flickerAsyncApi.getShard();
    }

    public void setShard(Shard shard) {
        flickerAsyncApi.setShard(shard);
    }
}
