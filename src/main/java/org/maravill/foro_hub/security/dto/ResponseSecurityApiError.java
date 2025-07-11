package org.maravill.foro_hub.security.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record ResponseSecurityApiError(String backendMessage,
                                       String message,
                                       String url,
                                       String method,
                                       @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss") LocalDateTime localDateTime) {
}
