package org.maravill.foro_hub.security.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;

@JsonIgnoreProperties(ignoreUnknown = true)
public record RequestModule(Long idModule, @NotBlank String moduleName, @NotBlank String basePath){
}

