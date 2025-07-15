package org.maravill.foro_hub.security.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.maravill.foro_hub.security.config.docs.DefaultSecurityApiResponses;
import org.maravill.foro_hub.security.config.docs.HttpStatusCode;
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
@Tag(name = "Authentication Controller",
        description = "Endpoints for managing authentication")
public class AuthenticationController implements DefaultSecurityApiResponses {

    private final IAuthenticationService authenticationService;

    @PostMapping("/login")
    @Operation(
            summary = "User login",
            description = "Authenticates a user and returns a JWT token if the credentials are valid.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = RequestAuthentication.class)
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = HttpStatusCode.OK,
                            description = HttpStatusCode.OK_VALUE,
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ResponseAuthentication.class)
                            )
                    )
            }
    )
    public ResponseEntity<ResponseAuthentication> login(@RequestBody @Valid RequestAuthentication requestAuthentication) {
        return ResponseEntity.ok(authenticationService.login(requestAuthentication));
    }
}
