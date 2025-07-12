package org.maravill.foro_hub.security.service.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.maravill.foro_hub.security.dto.RequestAuthentication;
import org.maravill.foro_hub.security.dto.ResponseAuthentication;
import org.maravill.foro_hub.security.service.IJwtService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Test Authentication Service")
class AuthenticationServiceImplTest {

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private IJwtService jwtService;

    @InjectMocks
    private AuthenticationServiceImpl authenticationService;

    @Test
    @DisplayName("Test Login")
    void loginTest() {
        // Arrange
        RequestAuthentication request = new RequestAuthentication("eduardo", "1234");
        String expectedToken = "mocked-jwt-token";

        Authentication fakeAuth = new UsernamePasswordAuthenticationToken(
                request.username(), request.password());

        when(authenticationManager.authenticate(any(Authentication.class)))
                .thenReturn(fakeAuth);

        when(jwtService.generateTokenJwt(request)).thenReturn(expectedToken);

        // Act
        ResponseAuthentication response = authenticationService.login(request);

        // Assert
        assertNotNull(response);
        assertEquals(expectedToken, response.jwt());
        verify(authenticationManager).authenticate(any(Authentication.class));
        verify(jwtService).generateTokenJwt(request);
    }
}