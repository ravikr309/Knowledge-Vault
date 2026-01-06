package com.knowledgevault.knowledge.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KnowledgeEntryRequest {

  private String type;
  private String title;

  // Article fields
  private String author;
  private String content;

  // Link fields
  private String url;
  private LocalDateTime publishedAt;
}
