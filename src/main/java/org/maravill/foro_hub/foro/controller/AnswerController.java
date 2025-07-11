package org.maravill.foro_hub.foro.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.maravill.foro_hub.foro.dto.RequestAnswer;
import org.maravill.foro_hub.foro.dto.ResponseAnswer;
import org.maravill.foro_hub.foro.service.IAnswerService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Validated
@RestController
@RequestMapping("/answers")
@RequiredArgsConstructor
public class AnswerController {

    private final IAnswerService answerService;

    @PostMapping
    public ResponseEntity<ResponseAnswer> createAnswer(@RequestBody @Valid RequestAnswer requestAnswer, UriComponentsBuilder uriBuilder){
       ResponseAnswer responseAnswer = answerService.createNewAnswer(requestAnswer);
        URI uri = uriBuilder.buildAndExpand("/answers/{idAnswer}").expand(responseAnswer.idAnswer()).toUri();
        return ResponseEntity.created(uri).body(responseAnswer);
    }
}
