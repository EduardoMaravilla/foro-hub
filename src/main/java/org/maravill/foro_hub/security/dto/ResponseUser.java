package org.maravill.foro_hub.security.dto;

public record ResponseUser(String username,
                           String email,
                           String roleName) {
}
