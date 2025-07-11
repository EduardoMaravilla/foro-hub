package org.maravill.foro_hub.security.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record RequestAuthentication(String username, String password) {
}
