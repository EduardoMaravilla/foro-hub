package org.maravill.foro_hub.foro.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@JsonIgnoreProperties(ignoreUnknown = true)
public record RequestAnswer(Long idAnswer, @NotBlank String message, @NotNull Long idTopic){
}
