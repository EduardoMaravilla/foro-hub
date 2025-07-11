package org.maravill.foro_hub.foro.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record ResponseTopic(
        Long idTopic,
        String title,
        String message,
        String author,
        String status,
        @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss") LocalDateTime createdAt,
        ResponseCourse responseCourse) {
}
