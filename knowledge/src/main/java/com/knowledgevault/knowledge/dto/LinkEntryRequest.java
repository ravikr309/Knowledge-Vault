package com.knowledgevault.knowledge.dto;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(description = "Request body for creating an Link entry")
public class LinkEntryRequest implements KnowledgeEntryRequest {

  @NotBlank
  private String title;

  @NotBlank
  private String url;

  private LocalDateTime publishedAt;

  @Override
  public String getType() {
    return "LINK";
  }
}
