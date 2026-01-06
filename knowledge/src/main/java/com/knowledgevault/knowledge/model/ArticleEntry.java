package com.knowledgevault.knowledge.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@DiscriminatorValue("ARTICLE")
@EqualsAndHashCode(callSuper = true)
public class ArticleEntry extends KnowledgeEntry {

  private String author;

  private String content;

}
