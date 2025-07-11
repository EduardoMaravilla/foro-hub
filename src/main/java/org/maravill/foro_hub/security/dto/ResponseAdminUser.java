package org.maravill.foro_hub.security.dto;

public record ResponseAdminUser(Long idUser,
                                String username,
                                String email,
                                String roleName) {
}
