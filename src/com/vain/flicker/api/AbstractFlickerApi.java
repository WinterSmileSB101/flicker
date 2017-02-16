package com.vain.flicker.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.jasminb.jsonapi.ResourceConverter;
import com.vain.flicker.model.match.*;
import com.vain.flicker.model.player.Player;
import com.vain.flicker.model.sample.Sample;
import com.vain.flicker.model.status.Status;
import com.vain.flicker.utils.Shard;
import org.asynchttpclient.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * @author Dominic Gunn (dominic@vain.gg)
 */
public abstract class AbstractFlickerApi {

    protected static final ResourceConverter resourceConverter = new ResourceConverter(
            Match.class, Participant.class, Player.class, Roster.class, Team.class, Status.class,
            Sample.class
    );

    private static final String API_VERSION = "v3.7.1";

    private static final String CONTENT_ENCODING_HEADER = "Content-Encoding";
    private static final String CONTENT_ENCODING_GZIP = "gzip";

    private static final String X_TITLE_ID_HEADER = "X-TITLE-ID";
    private static final String X_TITLE_ID_VALUE = "semc-vainglory";

    private static final String ACCEPT_HEADER = "Accept";
    private static final String APPLICATION_VND_API_JSON = "application/vnd.api+json";

    private static final String AUTHORIZATION_HEADER = "Authorization";

    private static final String BASE_API_URL = "https://api.dc01.gamelockerapp.com/shards/";
    private static final String STATUS_API_URL = "https://api.dc01.gamelockerapp.com/status";

    private static final AsyncHttpClient httpClient = new DefaultAsyncHttpClient(
            new DefaultAsyncHttpClientConfig.Builder()
                .setMaxConnections(10)
                .setMaxConnectionsPerHost(50)
                .setPooledConnectionIdleTimeout(100)
                .setConnectionTtl(500).build()
    );

    private String jwtToken = null;
    private Shard shard = Shard.NA;

    private Status apiStatus;
    private boolean statusChecked;

    public AbstractFlickerApi(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    protected CompletableFuture<Response> get(final String requestUrl, final Map<String, List<String>> params) {
        if (jwtToken == null ) {
            throw new FlickerException("You must set an API Key before the server can be queried!");
        }

        if (!statusChecked) {
            checkStatus();
        }

        return httpClient.prepareGet(BASE_API_URL + requestUrl).setQueryParams(params)
                .addHeader(ACCEPT_HEADER, APPLICATION_VND_API_JSON)
                .addHeader(X_TITLE_ID_HEADER, X_TITLE_ID_VALUE)
                .addHeader(CONTENT_ENCODING_HEADER, CONTENT_ENCODING_GZIP)
                .addHeader(AUTHORIZATION_HEADER, "Bearer " + jwtToken)
                .execute().toCompletableFuture();
    }

    public void checkStatus() {
        statusChecked = true;
        httpClient.prepareGet(STATUS_API_URL).execute().toCompletableFuture().thenAccept(statusResponse -> {
            this.apiStatus = resourceConverter.readDocument(statusResponse.getResponseBodyAsBytes(), Status.class).get();
            if (!apiStatus.getVersion().equals(API_VERSION)) {
                System.out.println("**********************************");
                System.out.println("WARNING: Flicker is out of date");
                System.out.println("Flicker API Version: " + API_VERSION);
                System.out.println("Current API Version: " + apiStatus.getVersion());
                System.out.println("API update occurred: " + apiStatus.getReleasedAt());
                System.out.println("**********************************");
            }
        });
    }

    public Status getApiStatus() {
        return apiStatus;
    }

    public Shard getShard() {
        return shard;
    }

    public void setShard(Shard shard) {
        this.shard = shard;
    }
}
