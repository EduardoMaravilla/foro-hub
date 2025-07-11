package org.maravill.foro_hub.foro.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.maravill.foro_hub.foro.dto.RequestTopic;
import org.maravill.foro_hub.foro.dto.ResponseAnswer;
import org.maravill.foro_hub.foro.dto.ResponseTopic;
import org.maravill.foro_hub.foro.service.IAnswerService;
import org.maravill.foro_hub.foro.service.ITopicService;
import org.maravill.foro_hub.global.dto.ResponsePage;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Validated
@RestController
@RequestMapping("/topics")
@RequiredArgsConstructor
public class TopicController {

    private final ITopicService topicService;
    private final IAnswerService answerService;

    @PostMapping
    public ResponseEntity<ResponseTopic> createTopic(
            @RequestBody @Valid RequestTopic requestTopic,
            UriComponentsBuilder uriBuilder) {
      ResponseTopic topic = topicService.createNewTopic(requestTopic);
        URI uri = uriBuilder.buildAndExpand("/topics/{idTopic}").expand(topic.idTopic()).toUri();
        return ResponseEntity.created(uri).body(topic);
    }

    @GetMapping
    public ResponseEntity<ResponsePage<ResponseTopic>> getAllTopics(
            @RequestParam(required = false) String courseName,
            @RequestParam(required = false) Integer year,
            @PageableDefault(size = 10, sort = {"createdAt"}) Pageable pageable) {
        return ResponseEntity.ok(topicService.getAllTopicsFiltered(courseName, year, pageable));
    }

    @GetMapping("/{idTopic}")
    public ResponseEntity<ResponseTopic> getTopicById(
            @PathVariable @Min(1) Long idTopic) {
        return ResponseEntity.ok(topicService.getTopicById(idTopic));
    }

    @PutMapping("/{idTopic}")
    public ResponseEntity<ResponseTopic> updateTopicById(@PathVariable @Min(1) Long idTopic,@RequestBody @Valid RequestTopic requestTopic){
        return ResponseEntity.ok(topicService.updateTopicById(idTopic,requestTopic));
    }

    @DeleteMapping("/{idTopic}")
    public ResponseEntity<Void> deleteTopic(@PathVariable @Min(1) Long idTopic) {
        topicService.deleteTopicById(idTopic);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{idTopic}/answers")
    public ResponseEntity<ResponsePage<ResponseAnswer>> findAllAnswerAboutTopic(@PathVariable @Min(1) Long idTopic,@PageableDefault(size = 20,sort = {"createdAt"}) Pageable pageable){
        return ResponseEntity.ok(answerService.findAllAnswerByIdTopic(idTopic,pageable));
    }
}
