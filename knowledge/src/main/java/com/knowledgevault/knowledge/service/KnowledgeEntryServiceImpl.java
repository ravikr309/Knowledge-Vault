package com.knowledgevault.knowledge.service;

import com.knowledgevault.knowledge.dto.ArticleEntryRequest;
import com.knowledgevault.knowledge.dto.KnowledgeEntryRequest;
import com.knowledgevault.knowledge.dto.LinkEntryRequest;
import com.knowledgevault.knowledge.model.ArticleEntry;
import com.knowledgevault.knowledge.model.KnowledgeEntry;
import com.knowledgevault.knowledge.model.LinkEntry;
import com.knowledgevault.knowledge.repository.KnowledgeEntryRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class KnowledgeEntryServiceImpl implements KnowledgeEntryService {

  private final KnowledgeEntryRepository repository;

  @Override
  @Transactional // Ensure atomicity
  public KnowledgeEntry create(KnowledgeEntryRequest request) {
    // Pattern Matching replaces the Switch/Type check
    if (request instanceof ArticleEntryRequest articleReq) {
      ArticleEntry article = new ArticleEntry();
      article.setTitle(articleReq.getTitle());
      article.setAuthor(articleReq.getAuthor());
      article.setContent(articleReq.getContent());
      article.setCreatedAt(LocalDateTime.now());
      return repository.save(article);
    }

    else if (request instanceof LinkEntryRequest linkReq) {
      LinkEntry link = new LinkEntry();
      link.setTitle(linkReq.getTitle());
      link.setUrl(linkReq.getUrl());
      // Use the DTO date if provided, otherwise default to now
      link.setPublishedAt(linkReq.getPublishedAt() != null ? linkReq.getPublishedAt() : LocalDateTime.now());
      link.setCreatedAt(LocalDateTime.now()); // Assuming LinkEntry also has this
      return repository.save(link);
    }

    throw new IllegalArgumentException("Unsupported request type: " + request.getClass().getName());
  }

  @Override
  public KnowledgeEntry getById(Long id) {
    return repository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Knowledge entry not found with id: " + id));
  }

  @Override
  public List<KnowledgeEntry> search(String query) {
    if (!StringUtils.hasText(query)) {
      return repository.findAll();
    }
    return repository.searchByTitle(query);
  }
}
