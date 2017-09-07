package com.vain.flicker.model;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jackson.JsonLoader;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchema;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import com.github.jasminb.jsonapi.JSONAPIDocument;
import com.github.jasminb.jsonapi.Link;
import com.github.jasminb.jsonapi.Links;
import com.github.jasminb.jsonapi.annotations.Id;
import com.vain.flicker.api.FlickerException;

import java.io.IOException;
import java.net.URL;

/**
 * @author Dominic Gunn
 */
public abstract class ApiResource<T> {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private static final JsonSchemaFactory JSON_SCHEMA_FACTORY = JsonSchemaFactory.byDefault();

    public static final String KEY_LINK_SCHEMA = "schema";

    @Id
    protected String id;

    @com.github.jasminb.jsonapi.annotations.Links
    private Links links;

    protected abstract JSONAPIDocument<T> getDocument();

    /**
     * This method is a little bit busy, so we'll try and explain what's going on.
     * Each JSONAPIDocument contains a URL (Link) to it's Schema on Github.
     * We pull down the schema, and create a JsonSchema object from it, to validate our document with.
     *
     * In our current state, we are no longer a JSONAPIDocument, and are instead a concrete (Match/Player/XX) class,
     * so we abstract ourselves back into a document via getDocument(), and then we perform schema validation against that.
     *
     * @return ProcessingReport Response from validation.
     */
    public ProcessingReport validateSchema() {
        final Link schemaLink = getLinks().getLink(KEY_LINK_SCHEMA);
        if (schemaLink == null) {
            throw new FlickerException("No schema to validate against.");
        }

        try {
            final URL schemaUrl = new URL(schemaLink.getHref());
            final JsonNode schemaNode = JsonLoader.fromURL(schemaUrl);
            final JsonSchema jsonSchema = JSON_SCHEMA_FACTORY.getJsonSchema(schemaNode);
            final JsonNode resourceNode = JsonLoader.fromString(OBJECT_MAPPER.writeValueAsString(getDocument()));
            return jsonSchema.validate(resourceNode);
        } catch (ProcessingException | IOException e) {
            throw new FlickerException("Unable to validate schema", e);
        }
    }

    public String getId() {
        return id;
    }

    public Links getLinks() {
        return links;
    }

    protected void setLinks(Links links) {
        this.links = links;
    }
}
