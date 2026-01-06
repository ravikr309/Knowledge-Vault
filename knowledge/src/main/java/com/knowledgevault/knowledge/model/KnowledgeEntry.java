package com.knowledgevault.knowledge.model;

import java.time.LocalDateTime;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.Data;

@Entity
@Data
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "entry_type")
public abstract class KnowledgeEntry {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long entryId;

  private String title;
  
  private LocalDateTime createdAt;

}
