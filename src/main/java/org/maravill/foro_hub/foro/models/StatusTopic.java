package org.maravill.foro_hub.foro.models;

import org.maravill.foro_hub.foro.exception.ForoInvalidDataException;

public enum StatusTopic {
    OPEN,           // Tópico activo, abierto para respuestas
    ANSWERED,       // El autor marcó una respuesta como solución
    CLOSED,         // Cerrado manualmente, no se aceptan más respuestas
    PENDING,        // En revisión o esperando aprobación/moderación
    DELETED,        // Eliminado por el usuario o moderador (soft delete)
    ARCHIVED;

    public static StatusTopic getStatusTopic(String topic) {
        if (topic == null) {
            throw new ForoInvalidDataException("Status Topic cannot be null");
        }
        try {
            return StatusTopic.valueOf(topic.toUpperCase());
        } catch (Exception _) {
            throw new ForoInvalidDataException("Invalid Status Topic: " + topic);
        }
    }
}
