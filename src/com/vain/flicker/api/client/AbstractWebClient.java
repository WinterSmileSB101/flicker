package com.vain.flicker.api.client;

import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClientConfig;

/**
 * @author Dominic Gunn (dominic@vain.gg)
 */
public abstract class AbstractWebClient {

    protected final static AsyncHttpClient httpClient = new DefaultAsyncHttpClient(
            new DefaultAsyncHttpClientConfig.Builder()
                    .setMaxConnections(100)
                    .setMaxConnectionsPerHost(50)
                    .setPooledConnectionIdleTimeout(100)
                    .setConnectionTtl(500).build()
    );

}
