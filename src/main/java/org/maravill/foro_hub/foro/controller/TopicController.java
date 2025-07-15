package org.maravill.foro_hub.foro.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.maravill.foro_hub.foro.dto.RequestTopic;
import org.maravill.foro_hub.foro.dto.ResponseAnswer;
import org.maravill.foro_hub.foro.dto.ResponseTopic;
import org.maravill.foro_hub.foro.service.IAnswerService;
import org.maravill.foro_hub.foro.service.ITopicService;
import org.maravill.foro_hub.global.dto.ResponsePage;
import org.maravill.foro_hub.security.config.docs.DefaultForoApiResponses;
import org.maravill.foro_hub.security.config.docs.HttpStatusCode;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Validated
@RestController
@RequestMapping("/topics")
@RequiredArgsConstructor
@Tag(name = "Topic Controller",
        description = "Endpoints for managing forum topics")
public class TopicController implements DefaultForoApiResponses {

    private final ITopicService topicService;
    private final IAnswerService answerService;

    @PostMapping
    @Operation(
            summary = "Create a new topic",
            description = "Creates a new forum topic with a title and message, associated to a course and user.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = RequestTopic.class)
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = HttpStatusCode.CREATED,
                            description = HttpStatusCode.CREATED_VALUE,
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ResponseTopic.class)
                            )
                    )
            },
            security = {@SecurityRequirement(name = "security-token")}
    )
    public ResponseEntity<ResponseTopic> createTopic(
            @RequestBody @Valid RequestTopic requestTopic,
            UriComponentsBuilder uriBuilder) {
        ResponseTopic topic = topicService.createNewTopic(requestTopic);
        URI uri = uriBuilder.buildAndExpand("/topics/{idTopic}").expand(topic.idTopic()).toUri();
        return ResponseEntity.created(uri).body(topic);
    }

    @GetMapping
    @Operation(
            summary = "Get all topics (filtered)",
            description = "Retrieves a paginated list of topics. Optionally filtered by course name and year.",
            responses = {
                    @ApiResponse(
                            responseCode = HttpStatusCode.OK,
                            description = HttpStatusCode.OK_VALUE,
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ResponsePage.class)
                            )
                    )
            }
    )
    public ResponseEntity<ResponsePage<ResponseTopic>> getAllTopics(
            @RequestParam(required = false) String courseName,
            @RequestParam(required = false) Integer year,
            @ParameterObject @PageableDefault(size = 10, sort = {"createdAt"}) Pageable pageable) {
        return ResponseEntity.ok(topicService.getAllTopicsFiltered(courseName, year, pageable));
    }

    @GetMapping("/{idTopic}")
    @Operation(
            summary = "Get topic by ID",
            description = "Retrieves a specific topic by its ID.",
            responses = {
                    @ApiResponse(
                            responseCode = HttpStatusCode.OK,
                            description = HttpStatusCode.OK_VALUE,
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ResponseTopic.class)
                            )
                    )
            }
    )
    public ResponseEntity<ResponseTopic> getTopicById(
            @PathVariable @Min(1) Long idTopic) {
        return ResponseEntity.ok(topicService.getTopicById(idTopic));
    }

    @PutMapping("/{idTopic}")
    @Operation(
            summary = "Update topic by ID",
            description = "Updates the title or message of a topic identified by its ID.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = RequestTopic.class)
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = HttpStatusCode.OK,
                            description = HttpStatusCode.OK_VALUE,
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ResponseTopic.class)
                            )
                    )
            },
            security = {@SecurityRequirement(name = "security-token")}
    )
    public ResponseEntity<ResponseTopic> updateTopicById(@PathVariable @Min(1) Long idTopic, @RequestBody @Valid RequestTopic requestTopic) {
        return ResponseEntity.ok(topicService.updateTopicById(idTopic, requestTopic));
    }

    @DeleteMapping("/{idTopic}")
    @Operation(
            summary = "Delete topic by ID",
            description = "Deletes a topic permanently. Requires authentication.",
            responses = {
                    @ApiResponse(
                            responseCode = HttpStatusCode.NO_CONTENT,
                            description = HttpStatusCode.NO_CONTENT_VALUE
                    )
            },
            security = {@SecurityRequirement(name = "security-token")}
    )
    public ResponseEntity<Void> deleteTopic(@PathVariable @Min(1) Long idTopic) {
        topicService.deleteTopicById(idTopic);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{idTopic}/answers")
    @Operation(
            summary = "Get answers for a topic",
            description = "Returns a paginated list of answers belonging to the specified topic.",
            responses = {
                    @ApiResponse(
                            responseCode = HttpStatusCode.OK,
                            description = HttpStatusCode.OK_VALUE,
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ResponsePage.class)
                            )
                    )
            }
    )
    public ResponseEntity<ResponsePage<ResponseAnswer>> findAllAnswerAboutTopic(@PathVariable @Min(1) Long idTopic, @PageableDefault(size = 20, sort = {"createdAt"}) Pageable pageable) {
        return ResponseEntity.ok(answerService.findAllAnswerByIdTopic(idTopic, pageable));
    }
}
