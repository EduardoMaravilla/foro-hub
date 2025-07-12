package org.maravill.foro_hub.foro.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.maravill.foro_hub.foro.dto.RequestCourse;
import org.maravill.foro_hub.foro.dto.RequestTopic;
import org.maravill.foro_hub.foro.dto.ResponseCourse;
import org.maravill.foro_hub.foro.dto.ResponseTopic;
import org.maravill.foro_hub.foro.models.Course;
import org.maravill.foro_hub.foro.models.StatusTopic;
import org.maravill.foro_hub.foro.models.Topic;
import org.maravill.foro_hub.foro.repository.ITopicRepository;
import org.maravill.foro_hub.foro.service.ICourseService;
import org.maravill.foro_hub.foro.utils.ForoMapperService;
import org.maravill.foro_hub.security.models.User;
import org.maravill.foro_hub.security.service.IUserService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Test Topic Service")
class TopicServiceImplTest {

    @Mock
    private ITopicRepository topicRepository;
    @Mock
    private ICourseService courseService;
    @Mock
    private IUserService userService;
    @Mock
    private ForoMapperService foroMapperService;
    @InjectMocks
    private TopicServiceImpl topicService;

    private Topic topic;
    private Course course;
    private User user;
    private RequestTopic requestTopic;
    private ResponseTopic responseTopic;
    private Pageable pageable;

    @BeforeEach
    void setUp() {
        user = new User(1L, "testuser", "password", "email@test.com");
        course = new Course();
        course.setIdCourse(1L);
        topic = new Topic(1L, "Title", "Message");
        requestTopic = new RequestTopic(1L,"Title", "Message", new RequestCourse(1L,"CLOUD","PROGRAMING"),"OPEN");
        responseTopic = new ResponseTopic(1L, "Title", "Message", "user1", StatusTopic.OPEN.name(), LocalDateTime.now(), new ResponseCourse(1L,"CLOUD","PROGRAMING"));
        pageable = PageRequest.of(0, 10);
    }

    @Test
    @DisplayName("Test create new topic")
    void createNewTopic() {
        when(topicRepository.existsByTitleIgnoreCaseAndMessageIgnoreCase("Title", "Message")).thenReturn(false);
        when(userService.extractUsernameOfSecurityContext()).thenReturn("testUser");
        when(userService.findUserByUsername("testUser")).thenReturn(user);
        when(courseService.findEntityCourseById(1L)).thenReturn(course);
        when(foroMapperService.mapToTopic(requestTopic)).thenReturn(topic);
        when(topicRepository.save(topic)).thenReturn(topic);
        when(foroMapperService.mapToTopicResponse(topic)).thenReturn(responseTopic);

        ResponseTopic result = topicService.createNewTopic(requestTopic);

        assertNotNull(result);
        assertEquals("Title", result.title());
    }

    @Test
    @DisplayName("Test get all topics")
    void getAllTopicsFiltered() {
        Page<Topic> page = new PageImpl<>(List.of(topic));
        when(topicRepository.findAll(pageable)).thenReturn(page);
        when(foroMapperService.mapToTopicResponse(topic)).thenReturn(responseTopic);

        var result = topicService.getAllTopicsFiltered(null, null, pageable);

        assertEquals(1, result.content().size());
    }

    @Test
    @DisplayName("Test get topic by id")
    void getTopicById() {
        when(topicRepository.findById(1L)).thenReturn(Optional.of(topic));
        when(foroMapperService.mapToTopicResponse(topic)).thenReturn(responseTopic);

        ResponseTopic result = topicService.getTopicById(1L);

        assertEquals("Title", result.title());
    }

    @Test
    @DisplayName("Test update topic by id")
    void updateTopicById() {
        when(topicRepository.findById(1L)).thenReturn(Optional.of(topic));
        when(topicRepository.existsByTitleIgnoreCaseAndMessageIgnoreCaseAndIdTopicNot("Title", "Message", 1L)).thenReturn(false);
        when(courseService.findEntityCourseById(1L)).thenReturn(course);
        when(topicRepository.save(any(Topic.class))).thenReturn(topic);
        when(foroMapperService.mapToTopicResponse(any())).thenReturn(responseTopic);

        ResponseTopic result = topicService.updateTopicById(1L, requestTopic);

        assertEquals("Title", result.title());
    }

    @Test
    @DisplayName("Test delete topic by id")
    void deleteTopicById() {
        when(topicRepository.findById(1L)).thenReturn(Optional.of(topic));

        topicService.deleteTopicById(1L);

        verify(topicRepository).deleteById(1L);
    }

    @Test
    @DisplayName("Test find entity topic by id")
    void findEntityTopicById() {
        when(topicRepository.findById(1L)).thenReturn(Optional.of(topic));

        Topic result = topicService.findEntityTopicById(1L);

        assertEquals(1L, result.getIdTopic());
    }
}