package com.knowledgevault.knowledge.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.knowledgevault.knowledge.model.KnowledgeEntry;

public interface KnowledgeEntryRepository extends JpaRepository<KnowledgeEntry, Long> {

  @Query("SELECT k FROM KnowledgeEntry k " +
      "WHERE LOWER(k.title) LIKE LOWER(CONCAT('%', :keyword, '%'))")
  List<KnowledgeEntry> searchByTitle(@Param("keyword") String keyword);
}