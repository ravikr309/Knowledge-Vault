package com.knowledgevault.knowledge.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import io.swagger.v3.oas.annotations.media.Schema;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
    @JsonSubTypes.Type(value = ArticleEntryRequest.class, name = "ARTICLE"),
    @JsonSubTypes.Type(value = LinkEntryRequest.class, name = "LINK")
})
// This tells Swagger to create a dropdown for the subclasses
@Schema(description = "Request body for Knowledge Entry", oneOf = { ArticleEntryRequest.class,
    LinkEntryRequest.class }, discriminatorProperty = "type")
public interface KnowledgeEntryRequest {
  String getType();

  String getTitle();
}