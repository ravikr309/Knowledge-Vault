package com.knowledgevault.knowledge.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
// IMPORTANT: Use the Spring Web RequestBody
import org.springframework.web.bind.annotation.RequestBody;

import com.knowledgevault.knowledge.dto.KnowledgeEntryRequest;
import com.knowledgevault.knowledge.model.KnowledgeEntry;
import com.knowledgevault.knowledge.service.KnowledgeEntryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/entries")
@RequiredArgsConstructor
@Tag(name = "Knowledge Entry API", description = "APIs for managing polymorphic knowledge entries")
public class KnowledgeEntryController {

  private final KnowledgeEntryService knowledgeEntryService;

  /**
   * create Entry
   */
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  @Operation(summary = "Create a new entry", description = "Polymorphic endpoint: Send 'type': 'ARTICLE' or 'type': 'LINK'", security = @SecurityRequirement(name = "basicAuth"))
  public KnowledgeEntry create(@Valid @RequestBody KnowledgeEntryRequest request) {
    return knowledgeEntryService.create(request);
  }

  /**
   * get entry with id
   */
  @GetMapping("/{id}")
  @Operation(summary = "Get entry by ID (Public)")
  public KnowledgeEntry getById(@PathVariable Long id) {
    return knowledgeEntryService.getById(id);
  }

  /**
   * search with the querry
   */
  @GetMapping("/querry")
  @Operation(summary = "Search entries (Public)")
  public List<KnowledgeEntry> search(@RequestParam(required = false) String query) {
    return knowledgeEntryService.search(query);
  }
}