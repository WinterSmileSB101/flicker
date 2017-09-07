package com.vain.flicker.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeCreator;
import com.github.fge.jackson.JsonLoader;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import com.github.jasminb.jsonapi.JSONAPIDocument;
import com.github.jasminb.jsonapi.Link;
import com.github.jasminb.jsonapi.Links;
import com.github.jasminb.jsonapi.ResourceConverter;
import com.vain.flicker.model.asset.Asset;
import com.vain.flicker.model.match.Match;
import com.vain.flicker.model.match.Participant;
import com.vain.flicker.model.match.Roster;
import com.vain.flicker.model.match.Team;
import com.vain.flicker.model.player.Player;
import com.vain.flicker.model.sample.Sample;
import com.vain.flicker.model.status.Status;
import org.apache.commons.io.IOUtils;
import org.powermock.api.mockito.PowerMockito;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

/**
 * @author Dominic Gunn
 */
public abstract class ApiResourceTest<T extends ApiResource> {

    private final static ResourceConverter RESOURCE_CONVERTER = new ResourceConverter(
            Match.class, Participant.class, Player.class, Roster.class, Team.class, Status.class,
            Sample.class, Asset.class
    );

    protected static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    protected static final JsonSchemaFactory JSON_SCHEMA_FACTORY = JsonSchemaFactory.newBuilder().freeze();

    public void setUp() throws Exception {
        final JsonSchemaFactory jsonSchemaFactory = JsonSchemaFactory.newBuilder().freeze();

        PowerMockito.mockStatic(JsonLoader.class);
        PowerMockito.mockStatic(JsonSchemaFactory.class);

        PowerMockito.when(JsonSchemaFactory.byDefault()).thenReturn(jsonSchemaFactory);
    }

    public T createResource(Map<String, Link> resourceLinks, String rawPayload, Class<T> clazz) {
        final T apiResource = RESOURCE_CONVERTER.readDocument(rawPayload.getBytes(), clazz).get();
        apiResource.setLinks(new Links(resourceLinks));
        return apiResource;
    }

    public List<T> createResourceCollection(Map<String, Link> resourceLinks, String rawPayload, Class<T> clazz) {
        final List<T> resourceList = RESOURCE_CONVERTER.readDocumentCollection(rawPayload.getBytes(), clazz).get();
        for (T apiResource : resourceList) {
            apiResource.setLinks(new Links(resourceLinks));
        }
        return resourceList;
    }

    public String readResource(String resourceName) throws Exception {
        return IOUtils.toString(getClass().getClassLoader().getResourceAsStream(resourceName), StandardCharsets.UTF_8);
    }
}
