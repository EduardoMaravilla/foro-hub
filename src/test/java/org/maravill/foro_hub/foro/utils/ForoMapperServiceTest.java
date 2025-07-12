package org.maravill.foro_hub.foro.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.maravill.foro_hub.foro.dto.*;
import org.maravill.foro_hub.foro.models.*;
import org.maravill.foro_hub.security.models.User;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Test Foro Mapper Service")
class ForoMapperServiceTest {

    private final ForoMapperService mapper = new ForoMapperService();

    @Test
    @DisplayName("Test RequestTopic to Topic")
    void mapToTopic() {
        RequestTopic req = new RequestTopic(1L, "Título", "Mensaje", new RequestCourse(1L, "Java Avanzado", Category.PROGRAMMING.name()), StatusTopic.OPEN.name());
        Topic topic = mapper.mapToTopic(req);

        assertEquals(req.idTopic(), topic.getIdTopic());
        assertEquals(req.title(), topic.getTitle());
        assertEquals(req.message(), topic.getMessage());
    }

    @Test
    @DisplayName("Test Topic to ResponseTopic")
    void mapToTopicResponse() {
        Course course = new Course(1L, "Java Avanzado", Category.PROGRAMMING);
        User user = new User();
        user.setUsername("eduardo");

        Topic topic = new Topic();
        topic.setIdTopic(2L);
        topic.setTitle("Errores con Spring");
        topic.setMessage("No me funciona el contexto");
        topic.setCreatedAt(LocalDateTime.now());
        topic.setStatusTopic(StatusTopic.OPEN);
        topic.setUser(user);
        topic.setCourse(course);

        ResponseTopic response = mapper.mapToTopicResponse(topic);

        assertEquals(topic.getIdTopic(), response.idTopic());
        assertEquals(topic.getTitle(), response.title());
        assertEquals(topic.getMessage(), response.message());
        assertEquals(topic.getUser().getUsername(), response.author());
        assertEquals(topic.getStatusTopic().name(), response.status());
        assertEquals(topic.getCreatedAt(), response.createdAt());
        assertEquals(course.getIdCourse(), response.responseCourse().idCourse());
    }

    @Test
    @DisplayName("Test Course to ResponseCourse")
    void mapToCourseResponse() {
        Course course = new Course(5L, "Estructuras", Category.BACKEND);
        ResponseCourse response = mapper.mapToCourseResponse(course);

        assertEquals(course.getIdCourse(), response.idCourse());
        assertEquals(course.getName(), response.name());
        assertEquals(course.getCategory().name(), response.category());
    }

    @Test
    @DisplayName("Test RequestCourse to Course")
    void mapToCourse() {
        RequestCourse req = new RequestCourse(10L, "Redes", "MOBILE");
        Course course = mapper.mapToCourse(req);

        assertEquals(req.idCourse(), course.getIdCourse());
        assertEquals(req.name(), course.getName());
        assertEquals(Category.MOBILE, course.getCategory());
    }

    @Test
    @DisplayName("Test RequestAnswer to Answer")
    void mapToAnswer() {
        RequestAnswer req = new RequestAnswer(3L, "Ya lo resolví con un bean extra", 1L);
        Answer answer = mapper.mapToAnswer(req);

        assertEquals(req.idAnswer(), answer.getIdAnswer());
        assertEquals(req.message(), answer.getMessage());
    }

    @Test
    @DisplayName("Test Answer to ResponseAnswer")
    void mapToAnswerResponse() {
        Topic topic = new Topic();
        topic.setIdTopic(100L);

        Answer answer = new Answer();
        answer.setIdAnswer(4L);
        answer.setMessage("Solucionado con config extra");
        answer.setTopic(topic);
        answer.setSolution(true);

        ResponseAnswer response = mapper.mapToAnswerResponse(answer);

        assertEquals(answer.getIdAnswer(), response.idAnswer());
        assertEquals(answer.getMessage(), response.message());
        assertEquals(topic.getIdTopic(), response.idTopic());
        assertTrue(response.isSolution());
    }
}
