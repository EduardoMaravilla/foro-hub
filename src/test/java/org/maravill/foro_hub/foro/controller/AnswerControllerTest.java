package org.maravill.foro_hub.foro.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.maravill.foro_hub.foro.dto.RequestAnswer;
import org.maravill.foro_hub.foro.dto.ResponseAnswer;
import org.maravill.foro_hub.security.dto.RequestAuthentication;
import org.maravill.foro_hub.security.dto.ResponseAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@DisplayName("Test Answer Controller")
class AnswerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private ResponseAuthentication responseAuthentication;

    @BeforeEach
    void setUp() throws Exception {
        RequestAuthentication requestAuthentication = new RequestAuthentication("user1","user1");
        var responseJwt = mockMvc.perform(
                        post("/authenticate/login")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(requestAuthentication))
                )
                .andReturn().getResponse();
        responseAuthentication = objectMapper
                .readValue(responseJwt.getContentAsString(), ResponseAuthentication.class);
    }

    @Test
    @DisplayName("Test Create Answer without token ")
    void createAnswerWithoutToken() throws Exception {
       var response = mockMvc.perform(post("/api/v1/answers")).andReturn().getResponse();

       assertEquals(HttpStatus.UNAUTHORIZED.value(),response.getStatus());
    }

    @Test
    @DisplayName("Test Create Answer with token")
    void createAnswerWithToken() throws Exception{
        RequestAnswer requestAnswer = new RequestAnswer(null,"Se pueden añadir mas respuestas",1L);
        var response = mockMvc
                .perform(post("/answers")
                        .header("Authorization","Bearer " + responseAuthentication.jwt())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(requestAnswer))
                )
                .andReturn().getResponse();
        ResponseAnswer responseAnswer = objectMapper
                .readValue(response.getContentAsByteArray(), ResponseAnswer.class);

        assertEquals(HttpStatus.CREATED.value(),response.getStatus());
        assertNotNull(responseAnswer.idAnswer());
        assertEquals(requestAnswer.message(),responseAnswer.message());
        assertEquals(requestAnswer.idTopic(),responseAnswer.idTopic());
    }

    @Test
    @DisplayName("Test Create Answer without full data")
    void createAnswerWithoutFullData() throws Exception{
        RequestAnswer requestAnswer = new RequestAnswer(null,null,null);
        var response = mockMvc
                .perform(post("/answers")
                        .header("Authorization","Bearer " + responseAuthentication.jwt())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(requestAnswer))
                )
                .andReturn().getResponse();
        assertEquals(HttpStatus.BAD_REQUEST.value(),response.getStatus());
    }

    @Test
    @DisplayName("Test Create Answer with imposible topic")
    void createAnswerWithImposibleTopic() throws Exception{
        RequestAnswer requestAnswer = new RequestAnswer(null,"Se pueden añadir mas respuestas",Long.MAX_VALUE);
        var response = mockMvc
                .perform(post("/answers")
                        .header("Authorization","Bearer " + responseAuthentication.jwt())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(requestAnswer))
                )
                .andReturn().getResponse();
        assertEquals(HttpStatus.NOT_FOUND.value(),response.getStatus());
    }


}