package com.knowledgevault.knowledge.model;

import java.time.LocalDateTime;

import org.hibernate.validator.constraints.URL;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@DiscriminatorValue("LINK")
@EqualsAndHashCode(callSuper = true)
public class LinkEntry extends KnowledgeEntry {

  @NotBlank(message = "URL is required")
  @URL(message = "Must be a valid URL")
  private String url;

  private LocalDateTime publishedAt;

}
