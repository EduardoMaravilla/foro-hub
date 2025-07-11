package org.maravill.foro_hub.foro.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;

@JsonIgnoreProperties(ignoreUnknown = true)
public record RequestCourse(Long idCourse, @NotBlank String name, @NotBlank String category) {
}
