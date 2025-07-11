package org.maravill.foro_hub.foro.service;

import org.maravill.foro_hub.foro.dto.*;
import org.maravill.foro_hub.foro.models.Answer;
import org.maravill.foro_hub.foro.models.Category;
import org.maravill.foro_hub.foro.models.Course;
import org.maravill.foro_hub.foro.models.Topic;
import org.springframework.stereotype.Service;

@Service
public class ForoMapperService {
    public Topic mapToTopic(RequestTopic requestTopic) {
        return new Topic(requestTopic.idTopic(), requestTopic.title(), requestTopic.message());
    }

    public ResponseTopic mapToTopicResponse(Topic topic) {
        return new ResponseTopic(
                topic.getIdTopic(),
                topic.getTitle(),
                topic.getMessage(),
                topic.getUser().getUsername(),
                topic.getStatusTopic().name(),
                topic.getCreatedAt(),
                mapToCourseResponse(topic.getCourse()));
    }

    public ResponseCourse mapToCourseResponse(Course course) {
        return new ResponseCourse(course.getIdCourse(), course.getName(), course.getCategory().name());
    }

    public Course mapToCourse(RequestCourse requestCourse) {
        return new Course(requestCourse.idCourse(), requestCourse.name(), Category.getCategory(requestCourse.category()));
    }

    public Answer mapToAnswer(RequestAnswer requestAnswer) {
        return new Answer(requestAnswer.idAnswer(), requestAnswer.message());
    }

    public ResponseAnswer mapToAnswerResponse(Answer answer) {
        return new ResponseAnswer(answer.getIdAnswer(), answer.getMessage(),answer.getTopic().getIdTopic(),answer.isSolution());
    }
}
