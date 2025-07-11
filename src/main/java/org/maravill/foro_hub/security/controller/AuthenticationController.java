package org.maravill.foro_hub.security.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.maravill.foro_hub.security.dto.RequestAuthentication;
import org.maravill.foro_hub.security.dto.ResponseAuthentication;
import org.maravill.foro_hub.security.service.IAuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authenticate")
@RequiredArgsConstructor
public class AuthenticationController {

    private final IAuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<ResponseAuthentication> login(@RequestBody @Valid RequestAuthentication requestAuthentication){
       return ResponseEntity.ok(authenticationService.login(requestAuthentication));
    }
}
