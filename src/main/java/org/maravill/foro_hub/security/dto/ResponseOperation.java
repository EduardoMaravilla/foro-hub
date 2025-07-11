package org.maravill.foro_hub.security.dto;

public record ResponseOperation(Long idOperation,
                                String name,
                                String httpMethod,
                                String path,
                                Boolean permitAll,
                                ResponseModule module) {
}
