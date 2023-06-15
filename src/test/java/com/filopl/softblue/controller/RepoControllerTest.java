package com.filopl.softblue.controller;


import com.filopl.softblue.model.RepositoryDTO;
import com.filopl.softblue.service.RepoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

/**
 * Created by T. Filo Zegarlicki on 10.06.2023
 **/

@SpringBootTest
@AutoConfigureMockMvc
class RepoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RepoService repositoryService;

    @BeforeEach
    public void setUp() {
        when(repositoryService.getRepositoryData(anyString(), anyString()))
                .thenReturn(ResponseEntity.ok(createSampleRepositoryData()));
    }

    @Test
    void testGetRepositoryData() throws Exception {
        String responseJson = mockMvc.perform(MockMvcRequestBuilders.get("/repositories/{owner}/{repositoryName}", "Bella", "sheba-3.9-turbo"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse().getContentAsString();

        System.out.println("Response JSON: " + responseJson);

        mockMvc.perform(MockMvcRequestBuilders.get("/repositories/{owner}/{repositoryName}", "Bella", "sheba-3.9-turbo"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.body.fullName").value("Bella/sheba-3.9-turbo"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.body.description").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.body.cloneUrl").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.body.stars").isNumber())
                .andExpect(MockMvcResultMatchers.jsonPath("$.body.createdAt").exists());
    }

    private RepositoryDTO createSampleRepositoryData() {
        RepositoryDTO repositoryData = new RepositoryDTO();
        repositoryData.setFullName("Bella/sheba-3.9-turbo");
        repositoryData.setDescription("Sample description");
        repositoryData.setCloneUrl("https://github.com/Bella/sheba-3.9-turbo.git");
        repositoryData.setStars(10);
        repositoryData.setCreatedAt("2020-06-01T12:34:56");
        return repositoryData;
    }
}
