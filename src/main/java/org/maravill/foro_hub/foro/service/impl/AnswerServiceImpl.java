package org.maravill.foro_hub.foro.service.impl;

import lombok.RequiredArgsConstructor;
import org.maravill.foro_hub.foro.dto.RequestAnswer;
import org.maravill.foro_hub.foro.dto.ResponseAnswer;
import org.maravill.foro_hub.foro.models.Answer;
import org.maravill.foro_hub.foro.models.Topic;
import org.maravill.foro_hub.foro.repository.IAnswerRepository;
import org.maravill.foro_hub.foro.service.ForoMapperService;
import org.maravill.foro_hub.foro.service.IAnswerService;
import org.maravill.foro_hub.foro.service.ITopicService;
import org.maravill.foro_hub.global.dto.ResponsePage;
import org.maravill.foro_hub.security.models.User;
import org.maravill.foro_hub.security.service.IUserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AnswerServiceImpl implements IAnswerService {

    private final IAnswerRepository answerRepository;
    private final ITopicService topicService;
    private final IUserService userService;
    private final ForoMapperService  foroMapperService;

    @Transactional
    @Override
    public ResponseAnswer createNewAnswer(RequestAnswer requestAnswer) {
        String username = userService.extractUsernameOfSecurityContext();
        User user = userService.findUserByUsername(username);
        Topic topic = topicService.findEntityTopicById(requestAnswer.idTopic());
        Answer answer = foroMapperService.mapToAnswer(requestAnswer);
        answer.setCreatedAt(LocalDateTime.now());
        answer.setTopic(topic);
        answer.setUser(user);
        return foroMapperService.mapToAnswerResponse(answerRepository.save(answer));
    }

    @Override
    public ResponsePage<ResponseAnswer> findAllAnswerByIdTopic(Long idTopic, Pageable pageable) {
        Page<Answer> answers = answerRepository.findAllByTopic_IdTopic(idTopic,pageable);
        return ResponsePage.from(answers.map(foroMapperService::mapToAnswerResponse));
    }
}
