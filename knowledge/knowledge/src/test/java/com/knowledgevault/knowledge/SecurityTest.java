package com.knowledgevault.knowledge;

import com.knowledgevault.knowledge.controller.KnowledgeEntryController;
import com.knowledgevault.knowledge.service.KnowledgeEntryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(KnowledgeEntryController.class)
class SecurityTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private KnowledgeEntryService service;

  @Test
  void getEntries_IsPublic_Returns200() throws Exception {
    mockMvc.perform(get("/api/v1/entries"))
        .andExpect(status().isOk());
  }

  @Test
  void postEntry_WithoutAuth_Returns401() throws Exception {
    mockMvc.perform(post("/api/v1/entries"))
        .andExpect(status().isUnauthorized());
  }
}