package org.maravill.foro_hub.foro.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.maravill.foro_hub.foro.models.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
@DisplayName("Test Answer Repository")
class IAnswerRepositoryTest {

    @Autowired
    private IAnswerRepository answerRepository;

    @Test
    @DisplayName("Test find all Answer by Topic Id")
    void findAllByTopic_IdTopic() {
        Long testTopicId = 1L;
        Pageable pageable = PageRequest.of(0, 10);
        Page<Answer> answers = answerRepository.findAllByTopic_IdTopic(testTopicId,pageable);
        assertFalse(answers.isEmpty(),"Debe devolver al menos 5 elementos");
        answers.forEach(answer -> assertEquals(testTopicId,answer.getTopic().getIdTopic()));
    }
}