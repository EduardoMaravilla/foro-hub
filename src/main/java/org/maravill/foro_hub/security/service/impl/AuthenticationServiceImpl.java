package org.maravill.foro_hub.security.service.impl;

import lombok.RequiredArgsConstructor;
import org.maravill.foro_hub.security.dto.RequestAuthentication;
import org.maravill.foro_hub.security.dto.ResponseAuthentication;
import org.maravill.foro_hub.security.service.IAuthenticationService;
import org.maravill.foro_hub.security.service.IJwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements IAuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final IJwtService jwtService;

    @Override
    public ResponseAuthentication login(RequestAuthentication requestAuthentication) {
        authenticateCurrentUser(requestAuthentication);
        String token = jwtService.generateTokenJwt(requestAuthentication);
        return new ResponseAuthentication(token);
    }

    private void authenticateCurrentUser(RequestAuthentication requestAuthentication){
        Authentication authentication = new UsernamePasswordAuthenticationToken(requestAuthentication.username(),requestAuthentication.password());
        authenticationManager.authenticate(authentication);
    }
}
