package org.maravill.foro_hub.foro.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.maravill.foro_hub.foro.dto.RequestCourse;
import org.maravill.foro_hub.foro.dto.ResponseCourse;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@DisplayName("Test Course Controller")
class CourseControllerTest {

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
        jwt = auth.jwt();
    }

    @Test
    @DisplayName("Test get all courses")
    void getAllCoursesSuccess() throws Exception {
        var response = mockMvc.perform(get("/courses"))
                .andReturn().getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    @DisplayName("Test get all courses with pagination")
    void getAllCoursesPaginated() throws Exception {
        var response = mockMvc.perform(get("/courses?size=5&page=0"))
                .andReturn().getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    @DisplayName("Test get course by valid ID")
    void getCourseByIdSuccess() throws Exception {
        var response = mockMvc.perform(get("/courses/1"))
                .andReturn().getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    @DisplayName("Test get course by invalid ID")
    void getCourseByIdNotFound() throws Exception {
        var response = mockMvc.perform(get("/courses/999999"))
                .andReturn().getResponse();
        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
    }

    @Test
    @DisplayName("Test create course")
    void createCourseSuccess() throws Exception {
        var request = new RequestCourse(null, "Nuevas tecnologías", "MOBILE");
        var response = mockMvc.perform(post("/courses")
                        .header("Authorization", "Bearer " + jwt)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(request)))
                .andReturn().getResponse();

        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
        var body = objectMapper.readValue(response.getContentAsByteArray(), ResponseCourse.class);
        assertEquals(request.name(), body.name());
        assertEquals(request.category(), body.category());
    }

    @Test
    @DisplayName("Test create course with invalid payload")
    void createCourseInvalidPayload() throws Exception {
        var request = new RequestCourse(null, "", "");
        var response = mockMvc.perform(post("/courses")
                        .header("Authorization", "Bearer " + jwt)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(request)))
                .andReturn().getResponse();
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
    }

    @Test
    @DisplayName("Test update course")
    void updateCourseSuccess() throws Exception {
        var request = new RequestCourse(1L, "Actualizado", "MOBILE");
        var response = mockMvc.perform(put("/courses/1")
                        .header("Authorization", "Bearer " + jwt)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(request)))
                .andReturn().getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Test
    @DisplayName("Test update course not found course")
    void updateCourseNotFound() throws Exception {
        var request = new RequestCourse(null, "Nuevo nombre", "MOBILE");
        var response = mockMvc.perform(put("/courses/999999")
                        .header("Authorization", "Bearer " + jwt)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(request)))
                .andReturn().getResponse();
        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
    }

    @Test
    @DisplayName("Test delete course")
    void deleteCourseSuccess() throws Exception {
        var request = new RequestCourse(null, "To Delete", "MOBILE");
        var postResponse = mockMvc.perform(post("/courses")
                        .header("Authorization", "Bearer " + jwt)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(request)))
                .andReturn().getResponse();

        var created = objectMapper.readValue(postResponse.getContentAsByteArray(), ResponseCourse.class);

        var deleteResponse = mockMvc.perform(delete("/courses/" + created.idCourse())
                        .header("Authorization", "Bearer " + jwt))
                .andReturn().getResponse();

        assertEquals(HttpStatus.NO_CONTENT.value(), deleteResponse.getStatus());
    }

    @Test
    @DisplayName("Test delete course with not found value")
    void deleteCourseNotFound() throws Exception {
        var response = mockMvc.perform(delete("/courses/999999")
                        .header("Authorization", "Bearer " + jwt))
                .andReturn().getResponse();
        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
    }
}
