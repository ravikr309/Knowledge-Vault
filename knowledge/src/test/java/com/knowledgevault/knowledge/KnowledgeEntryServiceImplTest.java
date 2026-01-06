package com.knowledgevault.knowledge;

import com.knowledgevault.knowledge.dto.ArticleEntryRequest;
import com.knowledgevault.knowledge.dto.KnowledgeEntryRequest;
import com.knowledgevault.knowledge.model.ArticleEntry;
import com.knowledgevault.knowledge.model.KnowledgeEntry;
import com.knowledgevault.knowledge.repository.KnowledgeEntryRepository;
import com.knowledgevault.knowledge.service.KnowledgeEntryServiceImpl;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class KnowledgeEntryServiceImplTest {

  @Mock
  private KnowledgeEntryRepository repository;

  @InjectMocks
  private KnowledgeEntryServiceImpl service;

  @Test
  @DisplayName("Create Article: Success")
  void create_Article_Success() {
    // Arrange
    ArticleEntryRequest request = new ArticleEntryRequest();
    request.setTitle("Test Title");
    request.setAuthor("Author");
    request.setContent("Content");

    ArticleEntry savedEntry = new ArticleEntry();
    savedEntry.setEntryId(1L);
    savedEntry.setTitle("Test Title");

    when(repository.save(any(ArticleEntry.class))).thenReturn(savedEntry);

    // Act
    KnowledgeEntry result = service.create(request);

    // Assert
    assertNotNull(result);
    assertEquals("Test Title", result.getTitle());
    verify(repository, times(1)).save(any(ArticleEntry.class));
  }

  @Test
  @DisplayName("GetById: Throws EntityNotFoundException")
  void getById_NotFound_ThrowsException() {
    // Arrange
    Long id = 99L;
    when(repository.findById(id)).thenReturn(Optional.empty());

    // Act & Assert
    EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
      service.getById(id);
    });

    assertEquals("Knowledge entry not found with id: 99", exception.getMessage());
  }

  @Test
  @DisplayName("Create: Throws IllegalArgumentException for unknown DTO")
  void create_UnknownType_ThrowsException() {
    // Arrange: Using an anonymous implementation of the interface
    KnowledgeEntryRequest unknownRequest = new KnowledgeEntryRequest() {
      @Override
      public String getType() {
        return "UNKNOWN";
      }

      @Override
      public String getTitle() {
        return "Title";
      }
    };

    // Act & Assert
    assertThrows(IllegalArgumentException.class, () -> {
      service.create(unknownRequest);
    });
  }
}