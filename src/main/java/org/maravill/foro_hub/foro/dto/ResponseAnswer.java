package org.maravill.foro_hub.foro.dto;

public record ResponseAnswer(Long idAnswer, String message,Long idTopic, boolean isSolution) {
}

