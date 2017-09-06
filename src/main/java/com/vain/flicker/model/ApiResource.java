package com.vain.flicker.model;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jackson.JsonLoader;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchema;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import com.github.jasminb.jsonapi.Link;
import com.github.jasminb.jsonapi.Links;
import com.github.jasminb.jsonapi.annotations.Id;
import com.vain.flicker.api.FlickerException;

import java.io.IOException;
import java.net.URL;

/**
 * @author Dominic Gunn
 */
public abstract class ApiResource {

    private static final JsonSchemaFactory JSON_SCHEMA_FACTORY = JsonSchemaFactory.byDefault();

    protected static final String KEY_LINK_SCHEMA = "schema";

    @Id
    protected String id;

    private String rawApiResponse;

    @com.github.jasminb.jsonapi.annotations.Links
    private Links links;

    public ProcessingReport validateSchema() {
        final Link schemaLink = getLinks().getLink(KEY_LINK_SCHEMA);
        if (schemaLink == null) {
            throw new FlickerException("No schema to validate against.");
        }

        if (rawApiResponse == null) {
            throw new FlickerException("No raw response to validate against.");
        }

        try {
            final URL schemaUrl = new URL(schemaLink.getHref());
            final JsonNode schemaNode = JsonLoader.fromURL(schemaUrl);
            final JsonNode rawResponseNode = JsonLoader.fromString(rawApiResponse);

            final JsonSchema jsonSchema = JSON_SCHEMA_FACTORY.getJsonSchema(schemaNode);

            return jsonSchema.validate(rawResponseNode);
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

    public String getRawApiResponse() {
        return rawApiResponse;
    }

    public void setRawApiResponse(String rawApiResponse) {
        this.rawApiResponse = rawApiResponse;
    }
}
