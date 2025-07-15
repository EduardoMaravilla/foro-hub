package org.maravill.foro_hub.security.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.maravill.foro_hub.security.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@DisplayName("Test User Controller")
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private String jwt;

    void loginAsUser(String username, String password) throws Exception {
        RequestAuthentication login = new RequestAuthentication(username, password);
        var result = mockMvc.perform(post("/authenticate/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(login)))
                .andReturn().getResponse();

        jwt = "Bearer " + objectMapper.readValue(result.getContentAsString(), ResponseAuthentication.class).jwt();
    }

    @Test
    @DisplayName("Test register new user")
    void shouldRegisterUserSuccessfully() throws Exception {
        var request = new RequestUserRegister("new_user", "New_User", "New_User", "new_user@mail.com");

        var result = mockMvc.perform(
                post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
        ).andReturn().getResponse();

        assertEquals(HttpStatus.CREATED.value(), result.getStatus());
        var response = objectMapper.readValue(result.getContentAsByteArray(), ResponseUser.class);
        assertEquals("new_user", response.username());
        assertEquals("USER",response.roleName());
    }

    @Test
    @DisplayName("Test register new user with invalid data")
    void shouldFailToRegisterUserInvalidData() throws Exception {
        var request = new RequestUserRegister(null, "", "", "");

        var result = mockMvc.perform(
                post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
        ).andReturn().getResponse();

        assertEquals(HttpStatus.BAD_REQUEST.value(), result.getStatus());
    }

    @Test
    @DisplayName("Test change password")
    void shouldChangePasswordSuccessfully() throws Exception {
        loginAsUser("user3","user3");
        var request = new ChangePasswordRequest("user3", "newPassword123", "newPassword123");

        var result = mockMvc.perform(
                put("/users/change-password")
                        .header("Authorization", jwt)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
        ).andReturn().getResponse();

        assertEquals(HttpStatus.OK.value(), result.getStatus());
    }

    @Test
    @DisplayName("Test failed change password")
    void shouldFailChangePasswordWithoutToken() throws Exception {
        var request = new ChangePasswordRequest("user3", "newPassword123", "newPassword123");

        var result = mockMvc.perform(
                put("/users/change-password")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
        ).andReturn().getResponse();

        assertEquals(HttpStatus.UNAUTHORIZED.value(), result.getStatus());
    }

    @Test
    @DisplayName("Test disable user successfully")
    void shouldDisableUserSuccessfully() throws Exception {
        loginAsUser("user2","user2");
        var request = new RequestDelete("user2");

        var result = mockMvc.perform(
                put("/users/disable")
                        .header("Authorization", jwt)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
        ).andReturn().getResponse();

        assertEquals(HttpStatus.OK.value(), result.getStatus());
    }

    @Test
    @DisplayName("Test error disabling user without token")
    void shouldFailDisableUserWithoutToken() throws Exception {
        var request = new RequestDelete("user3");

        var result = mockMvc.perform(
                put("/users/disable")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
        ).andReturn().getResponse();

        assertEquals(HttpStatus.UNAUTHORIZED.value(), result.getStatus());
    }
}
