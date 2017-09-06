package com.vain.flicker.api;

import com.github.jasminb.jsonapi.JSONAPIDocument;
import com.github.jasminb.jsonapi.Link;
import com.github.jasminb.jsonapi.Links;
import com.vain.flicker.api.requests.MatchRequest;
import com.vain.flicker.model.ApiResource;
import com.vain.flicker.model.ApiResponseHelper;
import com.vain.flicker.utils.PaginatedList;
import com.vain.flicker.model.player.Player;
import com.vain.flicker.model.sample.Sample;
import com.vain.flicker.utils.Shard;
import com.vain.flicker.model.match.Match;
import io.netty.handler.codec.http.HttpResponseStatus;
import org.apache.commons.io.IOUtils;
import org.asynchttpclient.Response;

import java.io.IOException;
import java.io.InputStream;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author Dominic Gunn (dominic@vain.gg)
 */
public class FlickerAsyncApi extends AbstractFlickerApi {

    private final static String PLAYERS_ENDPOINT = "/players";
    private final static String MATCHES_ENDPOINT = "/matches";
    private final static String SAMPLES_ENDPOINT = "/samples";

    private Date rateLimitExpiry = null;

    public FlickerAsyncApi(String apiKey) {
        super(apiKey);
    }

    public CompletableFuture<List<Sample>> getSamples() {
        return getSamples(null);
    }

    public CompletableFuture<List<Sample>> getSamples(Shard shard) {
        Shard endShard = shard == null ? getShard() : shard;
        return get((buildShardedUrl(SAMPLES_ENDPOINT, endShard)), Collections.singletonMap("sort", Collections.singletonList("-createdAt"))).thenApply(apiResponse -> {
            checkForCommonFailures(apiResponse);
            if (apiResponse.getStatusCode() == HttpResponseStatus.OK.code()) {
                return readDocumentCollectionAndSetRawResponse(apiResponse.getResponseBodyAsStream(), Sample.class).get();
            }
            throw new FlickerException("Something went wrong when pulling match data from the API, response code was :" + apiResponse.getStatusCode());
        });
    }

    public CompletableFuture<Player> getPlayerById(String playerId) {
        return get(buildShardedUrl(PLAYERS_ENDPOINT + "/" + playerId, getShard()), Collections.emptyMap()).thenApply(apiResponse -> {
            checkForCommonFailures(apiResponse);
            if (apiResponse.getStatusCode() == HttpResponseStatus.OK.code()) {
                return readDocumentAndSetRawResponse(apiResponse.getResponseBodyAsStream(), Player.class).get();
            }
            throw new FlickerException("Something went wrong when pulling player data from the API, response code was :" + apiResponse.getStatusCode());
        });
    }

    public CompletableFuture<Player> getPlayerById(String playerId, Shard shard) {
        return get(buildShardedUrl(PLAYERS_ENDPOINT + "/" + playerId, shard), Collections.emptyMap()).thenApply(apiResponse -> {
            checkForCommonFailures(apiResponse);
            if (apiResponse.getStatusCode() == HttpResponseStatus.OK.code()) {
                return readDocumentAndSetRawResponse(apiResponse.getResponseBodyAsStream(), Player.class).get();
            }
            throw new FlickerException("Something went wrong when pulling player data from the API, response code was :" + apiResponse.getStatusCode());
        });
    }


    public CompletableFuture<Player> getPlayerByName(String playerName) {
        return getPlayerByName(playerName, getShard());
    }

    public CompletableFuture<Player> getPlayerByName(String playerName, Shard shard) {
        return get(buildShardedUrl(PLAYERS_ENDPOINT, shard), Collections.singletonMap("filter[playerNames]", Collections.singletonList(playerName))).thenApply(apiResponse -> {
            checkForCommonFailures(apiResponse);
            if (apiResponse.getStatusCode() == HttpResponseStatus.OK.code()) {
                List<Player> playerList = readDocumentCollectionAndSetRawResponse(apiResponse.getResponseBodyAsStream(), Player.class).get();
                if (playerList.isEmpty()) {
                    throw new FlickerException("Something went wrong when pulling player data from the API, no player with name " + playerName + ", in shard " + shard + ", was found.");
                }
                return playerList.get(0);
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
            checkForCommonFailures(apiResponse);
            if (apiResponse.getStatusCode() == HttpResponseStatus.OK.code()) {
                return readDocumentAndSetRawResponse(apiResponse.getResponseBodyAsStream(), Match.class).get();
            }
            throw new FlickerException("Something went wrong when pulling match data from the API, response code was :" + apiResponse.getStatusCode());
        });
    }

    public CompletableFuture<PaginatedList<Match>> getMatches() {
        return getMatches(new MatchRequest.Builder().build());
    }

    public CompletableFuture<PaginatedList<Match>> getMatches(MatchRequest matchRequest) {
        Shard shard = matchRequest.getShard() == null ? getShard() : matchRequest.getShard();
        Map<String, List<String>> requestParams = new HashMap<>();

        if (matchRequest.getSortField() != null) {
            requestParams.put("sort", Collections.singletonList(String.valueOf(matchRequest.getSortField())));
        }

        if (matchRequest.getLimit() != null) {
            requestParams.put("page[limit]", Collections.singletonList(String.valueOf(matchRequest.getLimit())));
        }

        if (matchRequest.getOffset() != null) {
            requestParams.put("page[offset]", Collections.singletonList(String.valueOf(matchRequest.getOffset())));
        }

        if (matchRequest.getCreatedAfter() != null) {
            requestParams.put("filter[createdAt-start]", Collections.singletonList(matchRequest.getCreatedAfterString()));
        }

        if (matchRequest.getCreatedBefore() != null) {
            requestParams.put("filter[createdAt-end]", Collections.singletonList(matchRequest.getCreatedBeforeString()));
        }

        if (matchRequest.getPlayerNames() != null) {
            requestParams.put("filter[playerNames]", new ArrayList<>(matchRequest.getPlayerNames()));
        }

        if (matchRequest.getPlayerIds() != null) {
            requestParams.put("filter[playerIds]", new ArrayList<>(matchRequest.getPlayerIds()));
        }

        if (matchRequest.getTeamNames() != null) {
            requestParams.put("filter[teamNames]", new ArrayList<>(matchRequest.getTeamNames()));
        }

        if (matchRequest.getGameMode() != null) {
            requestParams.put("filter[gameMode]", Collections.singletonList(matchRequest.getGameModeIdentifier()));
        }

        return get((buildShardedUrl(MATCHES_ENDPOINT, shard)), requestParams).thenApply(apiResponse -> {
            checkForCommonFailures(apiResponse);
            if (apiResponse.getStatusCode() == HttpResponseStatus.OK.code()) {
                JSONAPIDocument<List<Match>> matchDocument = readDocumentCollectionAndSetRawResponse(apiResponse.getResponseBodyAsStream(), Match.class);
                final Links links = matchDocument.getLinks();
                final Link next = links.getNext();
                final String nextHref = next == null ? null : next.getHref();
                final String selfHref = links.getSelf().getHref();
                return new PaginatedList<>(matchDocument.get(),
                                nextHref, selfHref);
            }
            throw new FlickerException("Something went wrong when pulling match data from the API, response code was :" + apiResponse.getStatusCode() + " seconds");
        });
    }

    private <T extends ApiResource> JSONAPIDocument<List<T>> readDocumentCollectionAndSetRawResponse(InputStream dataStream, Class<T> clazz) {
        try {
            final byte[] rawResponse = IOUtils.toByteArray(dataStream);
            final JSONAPIDocument<List<T>> responseCollection = resourceConverter.readDocumentCollection(rawResponse, clazz);
            for (T entity : responseCollection.get()) {
                entity.setRawApiResponse(new String(rawResponse));
            }
            return responseCollection;
        } catch (IOException ex) {
            throw new FlickerException("Unable to serialize document");
        }
    }

    private <T extends ApiResource> JSONAPIDocument<T> readDocumentAndSetRawResponse(InputStream dataStream, Class<T> clazz) {
        try {
            final byte[] rawResponse = IOUtils.toByteArray(dataStream);
            final JSONAPIDocument<T> responseDocument = resourceConverter.readDocument(rawResponse, clazz);

            responseDocument.get().setRawApiResponse(new String(rawResponse));
            return responseDocument;
        } catch (IOException ex) {
            throw new FlickerException("Unable to serialize document");
        }
    }

    protected boolean hasReachedLimit() {
        return rateLimitExpiry != null && Instant.now().isBefore(rateLimitExpiry.toInstant());
    }

    @Override
    public CompletableFuture<Response> get(final String requestUrl, final Map<String, List<String>> params) {
        failFastIfRateLimited();
        return super.get(requestUrl, params);
    }

    private void failFastIfRateLimited() {
        if (rateLimitExpiry != null) {
            Date currentDate = new Date();
            if (currentDate.before(rateLimitExpiry)) {
                throw new FlickerException("We're still operating under rate limit, try again in " + ChronoUnit.SECONDS.between(currentDate.toInstant(), rateLimitExpiry.toInstant()));
            }
            rateLimitExpiry = null;
        }
    }
    
    private void checkForCommonFailures(Response apiResponse) {
        if (apiResponse.getStatusCode() == HttpResponseStatus.TOO_MANY_REQUESTS.code()) {
            rateLimitExpiry = Date.from(Instant.now().plusNanos(ApiResponseHelper.getRateLimitReset(apiResponse)));
            throw new FlickerException("Rate limit exceeded, limit resets in " + TimeUnit.NANOSECONDS.toSeconds(ApiResponseHelper.getRateLimitReset(apiResponse)) + " seconds");
        }
    }

    private String buildShardedUrl(String endPoint, Shard shard) {
        return shard.getShortCode().toLowerCase() + endPoint;
    }
}