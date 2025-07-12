package org.maravill.foro_hub.foro.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.maravill.foro_hub.foro.models.Course;
import org.maravill.foro_hub.foro.models.StatusTopic;
import org.maravill.foro_hub.foro.models.Topic;
import org.maravill.foro_hub.security.models.User;
import org.maravill.foro_hub.security.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
@DisplayName("Test Topic Repository")
class ITopicRepositoryTest {

    @Autowired
    private ITopicRepository topicRepository;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private ICourseRepository courseRepository;


    @Test
    @DisplayName("Test exist by title and message")
    void existsByTitleIgnoreCaseAndMessageIgnoreCase() {
        User user = userRepository.findByUsername("user1").orElseThrow();
        Course course = courseRepository.findById(6L).orElseThrow();

        Topic topic = new Topic();
        topic.setTitle("Título de prueba");
        topic.setMessage("Mensaje de prueba");
        topic.setCreatedAt(LocalDateTime.now());
        topic.setStatusTopic(StatusTopic.OPEN);
        topic.setUser(user);
        topic.setCourse(course);
        topicRepository.save(topic);

        boolean exists = topicRepository.existsByTitleIgnoreCaseAndMessageIgnoreCase("título de prueba", "MENSAJE DE PRUEBA");
        assertTrue(exists);
    }

    @Test
    @DisplayName("Test get topics by course name and year")
    void findByCourse_NameIgnoreCaseAndCreatedAtYear() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Topic> topics = topicRepository.findByCourse_NameIgnoreCaseAndCreatedAtYear("Estructuras de Datos", LocalDateTime.now().getYear(), pageable);
        assertTrue(topics.isEmpty());
    }

    @Test
    @DisplayName("Test get topics only by course name")
    void findByCourse_NameIgnoreCase() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Topic> topics = topicRepository.findByCourse_NameIgnoreCase("Estructuras de Datos", pageable);
        assertTrue(topics.isEmpty());
    }

    @Test
    @DisplayName("Test exists by title and message and different topic")
    void existsByTitleIgnoreCaseAndMessageIgnoreCaseAndIdTopicNot() {
        Topic topic = topicRepository.findAll().stream().findFirst().orElseThrow();
        boolean exists = topicRepository.existsByTitleIgnoreCaseAndMessageIgnoreCaseAndIdTopicNot(
                topic.getTitle(), topic.getMessage(), topic.getIdTopic() + 1
        );
        assertTrue(exists);
    }

    @Test
    @DisplayName("Test get topics by year")
    void findByCreatedAtYear() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Topic> topics = topicRepository.findByCreatedAtYear(LocalDateTime.now().getYear(), pageable);
        assertFalse(topics.isEmpty());
    }
}
