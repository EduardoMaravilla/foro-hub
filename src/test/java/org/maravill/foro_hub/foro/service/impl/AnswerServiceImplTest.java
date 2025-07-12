package org.maravill.foro_hub.foro.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.maravill.foro_hub.foro.dto.RequestAnswer;
import org.maravill.foro_hub.foro.dto.ResponseAnswer;
import org.maravill.foro_hub.foro.models.Answer;
import org.maravill.foro_hub.foro.models.Topic;
import org.maravill.foro_hub.foro.repository.IAnswerRepository;
import org.maravill.foro_hub.foro.service.ITopicService;
import org.maravill.foro_hub.foro.utils.ForoMapperService;
import org.maravill.foro_hub.global.dto.ResponsePage;
import org.maravill.foro_hub.security.models.User;
import org.maravill.foro_hub.security.service.IUserService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Test Answer Service")
class AnswerServiceImplTest {

    @InjectMocks
    private AnswerServiceImpl answerService;

    @Mock
    private IAnswerRepository answerRepository;

    @Mock
    private ITopicService topicService;

    @Mock
    private IUserService userService;

    @Mock
    private ForoMapperService foroMapperService;

    private RequestAnswer requestAnswer;
    private Answer answer;
    private ResponseAnswer responseAnswer;
    private User user;
    private Topic topic;
    private Pageable pageable;

    @BeforeEach
    void setUp() {
        requestAnswer = new RequestAnswer(1L, "Contenido de la respuesta",1L);
        answer = new Answer();
        responseAnswer = new ResponseAnswer(1L, "Contenido de la respuesta", 1L, false);
        user = new User(1L, "usuario", "encodedPwd", "user@mail.com");
        topic = new Topic();
        pageable = PageRequest.of(0, 10);
    }

    @Test
    @DisplayName("Test create new answer")
    void createNewAnswer() {
        when(userService.extractUsernameOfSecurityContext()).thenReturn("usuario");
        when(userService.findUserByUsername("usuario")).thenReturn(user);
        when(topicService.findEntityTopicById(1L)).thenReturn(topic);
        when(foroMapperService.mapToAnswer(requestAnswer)).thenReturn(answer);
        when(answerRepository.save(answer)).thenReturn(answer);
        when(foroMapperService.mapToAnswerResponse(answer)).thenReturn(responseAnswer);

        ResponseAnswer result = answerService.createNewAnswer(requestAnswer);

        assertNotNull(result);
        assertEquals("Contenido de la respuesta", result.message());
        verify(answerRepository).save(answer);
    }

    @Test
    @DisplayName("Test find all answer by topic id")
    void findAllAnswerByIdTopic() {
        Page<Answer> page = new PageImpl<>(List.of(answer));
        when(answerRepository.findAllByTopic_IdTopic(1L, pageable)).thenReturn(page);
        when(foroMapperService.mapToAnswerResponse(answer)).thenReturn(responseAnswer);

        ResponsePage<ResponseAnswer> result = answerService.findAllAnswerByIdTopic(1L, pageable);

        assertEquals(1, result.content().size());
        assertEquals("Contenido de la respuesta", result.content().getFirst().message());
        verify(answerRepository).findAllByTopic_IdTopic(1L, pageable);
    }
}
