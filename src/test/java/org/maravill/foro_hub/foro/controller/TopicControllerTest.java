package org.maravill.foro_hub.foro.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.maravill.foro_hub.foro.dto.RequestCourse;
import org.maravill.foro_hub.foro.dto.RequestTopic;
import org.maravill.foro_hub.security.dto.RequestAuthentication;
import org.maravill.foro_hub.security.dto.ResponseAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@DisplayName("Test Topic Controller")
class TopicControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private String jwt;

    @BeforeEach
    void authenticate() throws Exception {
        RequestAuthentication login = new RequestAuthentication("user1", "user1");
        var response = mockMvc.perform(post("/authenticate/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(login)))
                .andReturn().getResponse();

        ResponseAuthentication auth = objectMapper.readValue(response.getContentAsString(), ResponseAuthentication.class);
        jwt = "Bearer " + auth.jwt();
    }

    @Test
    @DisplayName("Test get all topics")
    void shouldReturnAllTopics() throws Exception {
        var response = mockMvc.perform(get("/topics")).andReturn().getResponse();
        assertEquals(200, response.getStatus());
    }

    @Test
    @DisplayName("Test topic does not exist")
    void shouldReturn404ForNonExistentTopic() throws Exception {
        var response = mockMvc.perform(get("/topics/{id}", Long.MAX_VALUE)).andReturn().getResponse();
        assertEquals(404, response.getStatus());
    }

    @Test
    @DisplayName("Test answer about topic by id")
    void shouldReturnAnswersForTopic() throws Exception {
        var response = mockMvc.perform(get("/topics/{id}/answers", 1)).andReturn().getResponse();
        assertEquals(200, response.getStatus());
    }

    @Test
    @DisplayName("Test create new topic")
    void shouldCreateNewTopic() throws Exception {
        RequestTopic newTopic = new RequestTopic(
                null,
                "Nuevo título",
                "Mensaje del tópico",
                new RequestCourse(1L, "Introducción al Foro", "GENERAL"),
                "OPEN"
        );

        var response = mockMvc.perform(post("/topics")
                        .header("Authorization", jwt)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newTopic)))
                .andReturn().getResponse();

        assertEquals(201, response.getStatus());
    }

    @Test
    @DisplayName("Test failed create topic")
    void shouldRejectCreateTopicWithoutAuth() throws Exception {
        var response = mockMvc.perform(post("/topics")).andReturn().getResponse();
        assertEquals(401, response.getStatus());
    }

    @Test
    @DisplayName("Test update topic")
    void shouldUpdateTopic() throws Exception {
        RequestTopic updateTopic = new RequestTopic(
                1L,
                "Título actualizado",
                "Mensaje actualizado",
                new RequestCourse(1L, "Introducción al Foro", "GENERAL"),
                "OPEN"
        );

        var response = mockMvc.perform(put("/topics/{id}", 1)
                        .header("Authorization", jwt)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateTopic)))
                .andReturn().getResponse();

        assertEquals(200, response.getStatus());
    }

    @Test
    @DisplayName("Test delete topic")
    void shouldDeleteTopic() throws Exception {
        var response = mockMvc.perform(delete("/topics/{id}", 5)
                        .header("Authorization", jwt))
                .andReturn().getResponse();

        assertEquals(204, response.getStatus());
    }
}