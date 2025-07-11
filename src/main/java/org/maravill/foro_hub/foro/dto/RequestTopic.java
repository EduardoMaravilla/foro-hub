package org.maravill.foro_hub.foro.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@JsonIgnoreProperties(ignoreUnknown = true)
public record RequestTopic(
        Long idTopic,
        @NotBlank String title,
        @NotBlank String message,
        @NotNull @Valid RequestCourse requestCourse,
        @NotBlank String status) {
}
