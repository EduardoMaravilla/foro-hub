package org.maravill.foro_hub.foro.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.maravill.foro_hub.foro.dto.RequestAnswer;
import org.maravill.foro_hub.foro.dto.ResponseAnswer;
import org.maravill.foro_hub.foro.service.IAnswerService;
import org.maravill.foro_hub.security.config.docs.DefaultForoApiResponses;
import org.maravill.foro_hub.security.config.docs.HttpStatusCode;
import org.springframework.http.MediaType;
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
@Tag(name = "Answer Controller"
        , description = "Endpoints for managing answers")
public class AnswerController implements DefaultForoApiResponses {

    private final IAnswerService answerService;

    @PostMapping
    @Operation(
            summary = "Submit an answer to a topic",
            description = "Allows an authenticated user to create a new answer associated with an existing topic. " +
                    "The request must include valid answer content and reference a valid topic ID.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = RequestAnswer.class)
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = HttpStatusCode.CREATED,
                            description = HttpStatusCode.CREATED_VALUE,
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ResponseAnswer.class)
                            )
                    )
            },
            security = {@SecurityRequirement(name = "security-token")}
    )
    public ResponseEntity<ResponseAnswer> createAnswer(@RequestBody @Valid RequestAnswer requestAnswer, UriComponentsBuilder uriBuilder) {
        ResponseAnswer responseAnswer = answerService.createNewAnswer(requestAnswer);
        URI uri = uriBuilder.buildAndExpand("/answers/{idAnswer}").expand(responseAnswer.idAnswer()).toUri();
        return ResponseEntity.created(uri).body(responseAnswer);
    }
}
