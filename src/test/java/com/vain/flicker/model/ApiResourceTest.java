package com.vain.flicker.model;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jackson.JsonLoader;
import com.github.fge.jsonschema.core.report.ProcessingMessage;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import com.github.jasminb.jsonapi.Link;
import com.github.jasminb.jsonapi.Links;
import com.vain.flicker.api.FlickerException;
import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

/**
 * @author Dominic Gunn
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ JsonSchemaFactory.class, JsonLoader.class })
public class ApiResourceTest {

    private static final String RAW_SINGLE_URL = "http://vainglorygame.com/single";
    private static final String RAW_COLLECTION_URL = "http://vainglorygame.com/collection";

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private static final Link VALID_SINGLE_LINK = new Link(RAW_SINGLE_URL);
    private static final Link VALID_COLLECTION_LINK = new Link(RAW_COLLECTION_URL);

    private static final Map<String, Link> INVALID_LINK_MAP = Collections.singletonMap(ApiResource.KEY_LINK_SCHEMA, null);
    private static final Map<String, Link> VALID_SINGLE_LINK_MAP = Collections.singletonMap(ApiResource.KEY_LINK_SCHEMA, VALID_SINGLE_LINK);
    private static final Map<String, Link> VALID_COLLECTION_LINK_MAP = Collections.singletonMap(ApiResource.KEY_LINK_SCHEMA, VALID_COLLECTION_LINK);

    @Before
    public void setUp() throws Exception {
        final JsonSchemaFactory jsonSchemaFactory = JsonSchemaFactory.newBuilder().freeze();

        PowerMockito.mockStatic(JsonLoader.class);
        PowerMockito.mockStatic(JsonSchemaFactory.class);

        PowerMockito.when(JsonSchemaFactory.byDefault()).thenReturn(jsonSchemaFactory);

        final JsonNode schemaNode = OBJECT_MAPPER.readTree(readResource("fixtures/match-schema.json"));
        PowerMockito.when(JsonLoader.fromURL(any(URL.class))).thenReturn(schemaNode);
    }

    @Test
    public void shouldValidateMatchCollection() throws Exception {
        final JsonNode matchCollectionNode = OBJECT_MAPPER.readTree(readResource("fixtures/match-collection-response.json"));
        PowerMockito.when(JsonLoader.fromString(anyString())).thenReturn(matchCollectionNode);

        final ApiResource apiResource = createApiResource(VALID_COLLECTION_LINK_MAP, readResource("fixtures/match-collection-response.json"));
        final ProcessingReport processingReport = apiResource.validateSchema();

        assertTrue(processingReport.isSuccess());
    }

    @Test
    public void shouldValidateMatchSingle() throws Exception {
        final JsonNode matchCollectionNode = OBJECT_MAPPER.readTree(readResource("fixtures/match-single-response.json"));
        PowerMockito.when(JsonLoader.fromString(anyString())).thenReturn(matchCollectionNode);

        final ApiResource apiResource = createApiResource(VALID_SINGLE_LINK_MAP, readResource("fixtures/match-single-response.json"));
        final ProcessingReport processingReport = apiResource.validateSchema();

        assertTrue(processingReport.isSuccess());
    }

    @Test
    public void shouldFailValidationWithMissingShard() throws Exception {
        final JsonNode matchCollectionNode = OBJECT_MAPPER.readTree(readResource("fixtures/match-missing-shard-response.json"));
        PowerMockito.when(JsonLoader.fromString(anyString())).thenReturn(matchCollectionNode);

        final ApiResource apiResource = createApiResource(VALID_SINGLE_LINK_MAP, readResource("fixtures/match-missing-shard-response.json"));
        final ProcessingReport processingReport = apiResource.validateSchema();

        assertFalse(processingReport.isSuccess());

        final Iterator<ProcessingMessage> processingMessageIterator = processingReport.iterator();
        final ProcessingMessage processingMessage = processingMessageIterator.next();

        // TODO: This isn't very useful, we should try and find a way to clean up responses from ProcessingMessage.
        assertEquals("instance failed to match exactly one schema (matched 0 out of 3)", processingMessage.getMessage());
    }

    @Test(expected = FlickerException.class)
    public void shouldThrowFlickerExceptionWithNoLink() throws Exception {
        final ApiResource apiResource = createApiResource(INVALID_LINK_MAP, readResource("fixtures/match-single-response.json"));
        apiResource.validateSchema();
    }

    private ApiResource createApiResource(Map<String, Link> resourceLinks, String rawResponse) {
        final ApiResource apiResource = new TestResource();
        apiResource.setLinks(new Links(resourceLinks));
        apiResource.setRawApiResponse(rawResponse);
        return apiResource;
    }

    private String readResource(String resourceName) throws Exception {
        return IOUtils.toString(getClass().getClassLoader().getResourceAsStream(resourceName), StandardCharsets.UTF_8);
    }
}
