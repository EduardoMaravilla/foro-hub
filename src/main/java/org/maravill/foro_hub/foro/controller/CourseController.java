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
import org.maravill.foro_hub.foro.dto.RequestCourse;
import org.maravill.foro_hub.foro.dto.ResponseCourse;
import org.maravill.foro_hub.foro.service.ICourseService;
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
@RequestMapping("/courses")
@RequiredArgsConstructor
@Tag(name = "Course Controller",
        description = "Endpoints for managing courses")
public class CourseController implements DefaultForoApiResponses {

    private final ICourseService courseService;

    @GetMapping
    @Operation(summary = "Get all courses",
            description = "Returns a paginated list of courses. You can use 'sort=name,asc' or 'sort=name,desc'",
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
    public ResponseEntity<ResponsePage<ResponseCourse>> getAllCourses(@ParameterObject @PageableDefault(size = 20, sort = {"name"}) Pageable pageable) {
        return ResponseEntity.ok(courseService.findAllCourses(pageable));
    }

    @GetMapping("/{idCourse}")
    @Operation(
            summary = "Get course by ID",
            description = "Retrieve a course using its ID.",
            responses = {
                    @ApiResponse(
                            responseCode = HttpStatusCode.OK,
                            description = HttpStatusCode.OK_VALUE,
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ResponseCourse.class)
                            )
                    )
            }
    )
    public ResponseEntity<ResponseCourse> getCourseById(@PathVariable @Min(1) Long idCourse) {
        return ResponseEntity.ok(courseService.findCourseById(idCourse));
    }

    @PostMapping
    @Operation(
            summary = "Create a new course",
            description = "Creates a new course with the provided data.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = RequestCourse.class)
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = HttpStatusCode.CREATED,
                            description = HttpStatusCode.CREATED_VALUE,
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ResponseCourse.class)
                            )
                    )
            },
            security = {@SecurityRequirement(name = "security-token")}
    )
    public ResponseEntity<ResponseCourse> createCourse(@RequestBody @Valid RequestCourse requestCourse, UriComponentsBuilder uriComponentsBuilder) {
        ResponseCourse responseCourse = courseService.createNewCourse(requestCourse);
        URI uri = uriComponentsBuilder.buildAndExpand("/courses/{idCourse}")
                .expand(responseCourse.idCourse()).toUri();
        return ResponseEntity.created(uri).body(responseCourse);
    }

    @PutMapping("/{idCourse}")
    @Operation(
            summary = "Update a course by ID",
            description = "Updates an existing course using its ID and the provided request body.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = RequestCourse.class)
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = HttpStatusCode.OK,
                            description = HttpStatusCode.OK_VALUE,
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ResponseCourse.class)
                            )
                    )
            },
            security = {@SecurityRequirement(name = "security-token")}
    )
    public ResponseEntity<ResponseCourse> updateCourse(@PathVariable @Min(1) Long idCourse, @RequestBody @Valid RequestCourse requestCourse) {
        return ResponseEntity.ok(courseService.updateCourse(idCourse, requestCourse));
    }

    @DeleteMapping("/{idCourse}")
    @Operation(
            summary = "Delete a course by ID",
            description = "Deletes an existing course by its ID. Requires authentication.",
            responses = {
                    @ApiResponse(
                            responseCode = HttpStatusCode.NO_CONTENT,
                            description = HttpStatusCode.NO_CONTENT_VALUE
                    )
            },
            security = {@SecurityRequirement(name = "security-token")}
    )
    public ResponseEntity<Void> deleteCourse(@PathVariable @Min(1) Long idCourse) {
        courseService.deleteCourse(idCourse);
        return ResponseEntity.noContent().build();
    }

}
