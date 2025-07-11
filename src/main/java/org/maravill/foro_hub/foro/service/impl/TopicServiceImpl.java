package org.maravill.foro_hub.foro.service.impl;

import lombok.RequiredArgsConstructor;
import org.maravill.foro_hub.foro.dto.RequestTopic;
import org.maravill.foro_hub.foro.dto.ResponseTopic;
import org.maravill.foro_hub.foro.exception.ForoDataNotFoundException;
import org.maravill.foro_hub.foro.exception.ForoInvalidDataException;
import org.maravill.foro_hub.foro.models.Course;
import org.maravill.foro_hub.foro.models.StatusTopic;
import org.maravill.foro_hub.foro.models.Topic;
import org.maravill.foro_hub.foro.repository.ITopicRepository;
import org.maravill.foro_hub.foro.service.ForoMapperService;
import org.maravill.foro_hub.foro.service.ICourseService;
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
public class TopicServiceImpl implements ITopicService {

    private final ITopicRepository topicRepository;
    private final ICourseService courseService;
    private final IUserService userService;
    private final ForoMapperService foroMapperService;

    @Transactional
    @Override
    public ResponseTopic createNewTopic(RequestTopic requestTopic) {
        if (topicRepository.existsByTitleIgnoreCaseAndMessageIgnoreCase(requestTopic.title(), requestTopic.message())) {
            throw new ForoInvalidDataException("Title and message already exist");
        }
        String username = userService.extractUsernameOfSecurityContext();
        User user = userService.findUserByUsername(username);
        Course course = courseService.findEntityCourseById(requestTopic.requestCourse().idCourse());
        Topic topic = foroMapperService.mapToTopic(requestTopic);
        topic.setUser(user);
        topic.setCourse(course);
        topic.setCreatedAt(LocalDateTime.now());
        topic.setStatusTopic(StatusTopic.OPEN);
        return foroMapperService.mapToTopicResponse(topicRepository.save(topic));
    }

    @Transactional(readOnly = true)
    @Override
    public ResponsePage<ResponseTopic> getAllTopicsFiltered(String courseName, Integer year, Pageable pageable) {
        Page<Topic> topics;
        if (courseName != null && year != null) {
            topics = topicRepository.findByCourse_NameIgnoreCaseAndCreatedAtYear(courseName, year, pageable);
        } else if (courseName != null) {
            topics = topicRepository.findByCourse_NameIgnoreCase(courseName, pageable);
        } else {
            topics = topicRepository.findAll(pageable);
        }

        return ResponsePage.from(topics.map(foroMapperService::mapToTopicResponse));
    }

    @Override
    public ResponseTopic getTopicById(Long idTopic) {
        return foroMapperService.mapToTopicResponse(topicRepository.findById(idTopic)
                .orElseThrow(() -> new ForoDataNotFoundException("Topic does not exist")));
    }

    @Transactional
    @Override
    public ResponseTopic updateTopicById(Long idTopic, RequestTopic requestTopic) {
        Topic topic = topicRepository.findById(idTopic)
                .orElseThrow(() -> new ForoDataNotFoundException("Topic does not exist"));

        boolean existsDuplicate = topicRepository
                .existsByTitleIgnoreCaseAndMessageIgnoreCaseAndIdTopicNot(
                        requestTopic.title(),
                        requestTopic.message(),
                        idTopic
                );
        if (existsDuplicate) {
            throw new ForoInvalidDataException("There is already another topic with the same title and description");
        }

        Course course = courseService.findEntityCourseById(requestTopic.requestCourse().idCourse());

        topic.setTitle(requestTopic.title());
        topic.setMessage(requestTopic.message());
        topic.setStatusTopic(StatusTopic.getStatusTopic(requestTopic.status()));
        topic.setCourse(course);

        return foroMapperService.mapToTopicResponse(topicRepository.save(topic));

    }

    @Transactional
    @Override
    public void deleteTopicById(Long idTopic) {
        Topic topic = topicRepository.findById(idTopic)
                .orElseThrow(() -> new ForoDataNotFoundException("Topic does not exist"));

        topicRepository.deleteById(topic.getIdTopic());
    }

    @Override
    public Topic findEntityTopicById(Long idTopic) {
        return topicRepository.findById(idTopic)
                .orElseThrow(() -> new ForoDataNotFoundException("Topic does not exist"));
    }

}
