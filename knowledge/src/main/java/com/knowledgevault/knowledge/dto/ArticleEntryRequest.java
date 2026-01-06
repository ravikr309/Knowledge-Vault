package com.knowledgevault.knowledge.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(description = "Request body for creating an Article entry")
public class ArticleEntryRequest implements KnowledgeEntryRequest {

  @NotBlank
  private String title;
  @NotBlank
  private String author;
  @NotBlank
  private String content;

  @Override
  public String getType() {
    return "ARTICLE";
  }
}
