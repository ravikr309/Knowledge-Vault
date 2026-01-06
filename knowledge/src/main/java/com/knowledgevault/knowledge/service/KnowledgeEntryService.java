package com.knowledgevault.knowledge.service;

import com.knowledgevault.knowledge.dto.KnowledgeEntryRequest;
import com.knowledgevault.knowledge.model.KnowledgeEntry;

import java.util.List;

public interface KnowledgeEntryService {

  KnowledgeEntry create(KnowledgeEntryRequest request);

  KnowledgeEntry getById(Long id);

  List<KnowledgeEntry> search(String query);
}
