package org.maravill.foro_hub.security.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@JsonIgnoreProperties(ignoreUnknown = true)
public record RequestUserRegister(@NotBlank @Size(min = 5, max = 50) String username,
                                  @NotBlank @Size(min = 8, max = 128) String password,
                                  @NotBlank @Size(min = 8, max = 128) String repeatPassword,
                                  @NotBlank @Size(max = 255) String email) {
}
