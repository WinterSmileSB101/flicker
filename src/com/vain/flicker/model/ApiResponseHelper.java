package com.vain.flicker.model;

import org.asynchttpclient.HttpResponseBodyPart;
import org.asynchttpclient.HttpResponseHeaders;
import org.asynchttpclient.HttpResponseStatus;
import org.asynchttpclient.Response;
import org.asynchttpclient.netty.NettyResponse;

import java.util.List;

/**
 * @author Dominic Gunn (dominic@vain.gg)
 */
public class ApiResponseHelper {

    private static final String HEADER_REQUEST_ID = "X-Request-Id";
    private static final String HEADER_RATE_LIMIT = "X-Ratelimit-Limit";
    private static final String HEADER_RATE_LIMIT_REMAINING = "X-Ratelimit-Remaining";
    private static final String HEADER_RATE_LIMIT_RESET = "X-Ratelimit-Reset";

    public static String getRequestId(Response response) {
        return response.getHeader(HEADER_REQUEST_ID);
    }

    public static int getRateLimit(Response response) {
        return Integer.parseInt(response.getHeader(HEADER_RATE_LIMIT));
    }

    public static int getRateLimitRemaining(Response response) {
        return Integer.parseInt(response.getHeader(HEADER_RATE_LIMIT_REMAINING));
    }

    public static long getRateLimitReset(Response response) {
        return Long.parseLong(response.getHeader(HEADER_RATE_LIMIT_RESET));
    }
}
