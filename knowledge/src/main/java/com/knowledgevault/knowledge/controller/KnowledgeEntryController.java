package com.knowledgevault.knowledge.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.knowledgevault.knowledge.dto.KnowledgeEntryRequest;
import com.knowledgevault.knowledge.model.KnowledgeEntry;
import com.knowledgevault.knowledge.service.KnowledgeEntryService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/entries")
@RequiredArgsConstructor
public class KnowledgeEntryController {

  private final KnowledgeEntryService knowledgeEntryService;

  // Create API
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public KnowledgeEntry create(@Valid @RequestBody KnowledgeEntryRequest request) {
    return knowledgeEntryService.create(request);
  }

  // Get with Id
  @GetMapping("/{id}")
  public KnowledgeEntry getById(@PathVariable Long id) {
    return knowledgeEntryService.getById(id);
  }

}
