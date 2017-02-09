package com.vain.flicker.api;

import com.vain.flicker.api.requests.MatchRequest;
import com.vain.flicker.model.player.Player;
import com.vain.flicker.utils.Shard;
import com.vain.flicker.model.match.Match;
import io.netty.handler.codec.http.HttpResponseStatus;

import java.util.*;
import java.util.concurrent.CompletableFuture;

/**
 * @author Dominic Gunn (dominic@vain.gg)
 */
public class FlickerAsyncApi extends AbstractFlickerApi {

    private static final String PLAYERS_ENDPOINT = "/players";
    private static final String MATCHES_ENDPOINT = "/matches";

    public FlickerAsyncApi(String apiKey) {
        super(apiKey);
    }

    public CompletableFuture<Player> getPlayerById(String playerId) {
        return get(buildShardedUrl(PLAYERS_ENDPOINT + "/" + playerId, getShard()), Collections.emptyMap()).thenApply(apiResponse -> {
            if (apiResponse.getStatusCode() == HttpResponseStatus.OK.code()) {
                return resourceConverter.readDocument(apiResponse.getResponseBodyAsStream(), Player.class).get();
            }
            throw new FlickerException("Something went wrong when pulling player data from the API, response code was :" + apiResponse.getStatusCode());
        });
    }

    public CompletableFuture<Player> getPlayerById(String playerId, Shard shard) {
        return get(buildShardedUrl(PLAYERS_ENDPOINT + "/" + playerId, shard), Collections.emptyMap()).thenApply(apiResponse -> {
            if (apiResponse.getStatusCode() == HttpResponseStatus.OK.code()) {
                return resourceConverter.readDocument(apiResponse.getResponseBodyAsStream(), Player.class).get();
            }
            throw new FlickerException("Something went wrong when pulling player data from the API, response code was :" + apiResponse.getStatusCode());
        });
    }

    public CompletableFuture<Match> getMatch(String matchId) {
        return getMatch(matchId, getShard());
    }

    public CompletableFuture<Match> getMatch(String matchId, Shard shard) {
        Shard endShard = shard == null ? getShard() : shard;
        return get((buildShardedUrl(MATCHES_ENDPOINT + "/" + matchId, endShard)), Collections.emptyMap()).thenApply(apiResponse -> {
            if (apiResponse.getStatusCode() == HttpResponseStatus.OK.code()) {
                return resourceConverter.readDocument(apiResponse.getResponseBodyAsStream(), Match.class).get();
            }
            throw new FlickerException("Something went wrong when pulling match data from the API, response code was :" + apiResponse.getStatusCode());
        });
    }

    public CompletableFuture<List<Match>> getMatches() {
        return getMatches(new MatchRequest.Builder().build());
    }

    public CompletableFuture<List<Match>> getMatches(MatchRequest matchRequest) {
        Shard shard = matchRequest.getShard() == null ? getShard() : matchRequest.getShard();
        Map<String, List<String>> requestParams = new HashMap<>();

        if (matchRequest.getSortField() != null) {
            requestParams.put("sort", Collections.singletonList(String.valueOf(matchRequest.getSortField())));
        }

        if (matchRequest.getLimit() != null) {
            requestParams.put("page[limit]", Collections.singletonList(String.valueOf(matchRequest.getLimit())));
        }

        if (matchRequest.getOffset() != null) {
            requestParams.put("page[offset]", Collections.singletonList(String.valueOf(matchRequest.getLimit())));
        }

        if (matchRequest.getCreatedAfter() != null) {
            requestParams.put("filter[createdAt-start]", Collections.singletonList(matchRequest.getCreatedAfterString()));
        }

        if (matchRequest.getCreatedBefore() != null) {
            requestParams.put("filter[createdAt-end]",Collections.singletonList(matchRequest.getCreatedBeforeString()));
        }

        if (matchRequest.getPlayerNames() != null) {
            requestParams.put("filter[playerNames]", new ArrayList<>(matchRequest.getPlayerNames()));
        }

        if (matchRequest.getTeamNames() != null) {
            requestParams.put("filter[teamNames]", new ArrayList<>(matchRequest.getTeamNames()));
        }

        return get((buildShardedUrl(MATCHES_ENDPOINT, shard)), requestParams).thenApply(apiResponse -> {
            if (apiResponse.getStatusCode() == HttpResponseStatus.OK.code()) {
                System.out.println(apiResponse.getResponseBody());
                return resourceConverter.readDocumentCollection(apiResponse.getResponseBodyAsStream(), Match.class).get();
            }
            throw new FlickerException("Something went wrong when pulling match data from the API, response code was :" + apiResponse.getStatusCode());
        });
    }

    private String buildShardedUrl(String endPoint, Shard shard) {
        return shard.getShortCode().toLowerCase() + endPoint;
    }
}
