package com.vain.flicker.api.client;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.vain.flicker.api.FlickerException;
import com.vain.flicker.api.client.deserializers.TelemetryDeserializer;
import com.vain.flicker.model.telemetry.Telemetry;
import com.vain.flicker.model.telemetry.events.TelemetryEvent;
import org.asynchttpclient.Response;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @author Dominic Gunn (dominic@vain.gg)
 */
public class S3WebClient extends AbstractWebClient {

    private final static ObjectMapper objectMapper = new ObjectMapper();
    private final static SimpleModule simpleModule = new SimpleModule();
    {
        simpleModule.addDeserializer(TelemetryEvent.class, new TelemetryDeserializer());
        objectMapper.registerModule(simpleModule);
    }

    public static CompletableFuture<Telemetry> getTelemetry(String telemetryUrl) {
        return get(telemetryUrl).thenApply(s3Response -> {
            try {
                return new Telemetry(objectMapper.readValue(s3Response.getResponseBody(), new TypeReference<List<TelemetryEvent>>(){}));
            } catch (IOException e) {
                throw new FlickerException("Unable to parse telemetry events", e);
            }
        });
    }

    private static CompletableFuture<Response> get(String s3Url) {
        return httpClient.prepareGet(s3Url)
                .execute().toCompletableFuture();
    }
}
