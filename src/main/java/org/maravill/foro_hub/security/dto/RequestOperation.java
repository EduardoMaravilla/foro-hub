package org.maravill.foro_hub.security.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@JsonIgnoreProperties(ignoreUnknown = true)
public record RequestOperation(Long idOperation,
                               @NotBlank String name,
                               @NotBlank String httpMethod,
                               String path,
                               @NotNull Boolean permitAll,
                               @NotNull @Valid RequestModule module) {
}
