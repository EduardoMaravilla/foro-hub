package org.maravill.foro_hub.security.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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
@AutoConfigureMockMvc
@ActiveProfiles("test")
@DisplayName("Test Authentication Controller")
class AuthenticationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Test login with valid credentials")
    void loginWithValidCredentials() throws Exception {
        RequestAuthentication request = new RequestAuthentication("user1", "user1");

        var response = mockMvc.perform(
                        post("/authenticate/login")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(request))
                )
                .andReturn()
                .getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());

        ResponseAuthentication authResponse = objectMapper.readValue(response.getContentAsString(), ResponseAuthentication.class);

        assertNotNull(authResponse.jwt());
        assertTrue(authResponse.jwt().startsWith("ey"));
    }

    @Test
    @DisplayName("Test login with invalid credentials")
    void loginWithInvalidCredentials() throws Exception {
        RequestAuthentication request = new RequestAuthentication("invalid_user", "wrong_password");

        var response = mockMvc.perform(
                        post("/authenticate/login")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(request))
                )
                .andReturn()
                .getResponse();

        assertEquals(HttpStatus.UNAUTHORIZED.value(), response.getStatus());
    }

    @Test
    @DisplayName("Test login with empty credentials")
    void loginWithEmptyCredentials() throws Exception {
        RequestAuthentication request = new RequestAuthentication("", "");

        var response = mockMvc.perform(
                        post("/authenticate/login")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(request))
                )
                .andReturn()
                .getResponse();

        assertEquals(HttpStatus.UNAUTHORIZED.value(), response.getStatus());
    }

    @Test
    @DisplayName("Test login with missing fields")
    void loginWithMissingFields() throws Exception {
        String badJson = "{}";

        var response = mockMvc.perform(
                        post("/authenticate/login")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(badJson)
                )
                .andReturn()
                .getResponse();

        assertEquals(HttpStatus.UNAUTHORIZED.value(), response.getStatus());
    }
}