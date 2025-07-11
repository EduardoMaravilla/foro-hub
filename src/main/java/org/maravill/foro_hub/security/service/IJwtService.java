package org.maravill.foro_hub.security.service;

import jakarta.servlet.http.HttpServletRequest;
import org.maravill.foro_hub.security.dto.RequestAuthentication;

public interface IJwtService {
    String extractJwtTokenFromRequest(HttpServletRequest request);

    boolean validateJwtToken(String jwt);

    String extractUsernameFromJwt(String jwt);

    String generateTokenJwt(RequestAuthentication requestAuthentication);
}
