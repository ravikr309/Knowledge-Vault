package com.knowledgevault.knowledge.model;

import java.time.LocalDateTime;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@DiscriminatorValue("LINK")
@EqualsAndHashCode(callSuper = true)
public class LinkEntry extends KnowledgeEntry {

  private String url;

  private LocalDateTime publishedAt;

}
