package org.maravill.foro_hub.foro.service;

import org.maravill.foro_hub.foro.dto.RequestAnswer;
import org.maravill.foro_hub.foro.dto.ResponseAnswer;
import org.maravill.foro_hub.global.dto.ResponsePage;
import org.springframework.data.domain.Pageable;

public interface IAnswerService {
    ResponseAnswer createNewAnswer(RequestAnswer requestAnswer);

    ResponsePage<ResponseAnswer> findAllAnswerByIdTopic(Long idTopic, Pageable pageable);
}
