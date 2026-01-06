package com.knowledgevault.knowledge.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@DiscriminatorValue("ARTICLE")
@EqualsAndHashCode(callSuper = true)
public class ArticleEntry extends KnowledgeEntry {

  @NotBlank(message = "Author is required")
  private String author;

  @NotBlank(message = "Content is required")
  private String content;

}
