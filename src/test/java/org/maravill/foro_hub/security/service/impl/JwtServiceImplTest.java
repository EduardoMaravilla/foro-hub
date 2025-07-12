package org.maravill.foro_hub.security.service.impl;

import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.maravill.foro_hub.security.dto.RequestAuthentication;
import org.maravill.foro_hub.security.models.Role;
import org.maravill.foro_hub.security.models.User;
import org.maravill.foro_hub.security.service.IUserService;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Field;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.spec.ECGenParameterSpec;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Test Jwt Service")
class JwtServiceImplTest {

    @Mock
    private IUserService userService;

    private JwtServiceImpl jwtService;

    @BeforeEach
    void setUp() throws Exception {
        jwtService = new JwtServiceImpl(userService);

        // 🔧 Usamos curva P-521 para ES512
        KeyPairGenerator signerKeyGen = KeyPairGenerator.getInstance("EC");
        signerKeyGen.initialize(new ECGenParameterSpec("secp521r1")); // P-521
        KeyPair signerKeyPair = signerKeyGen.generateKeyPair();

        KeyPairGenerator encryptKeyGen = KeyPairGenerator.getInstance("EC");
        encryptKeyGen.initialize(new ECGenParameterSpec("secp256r1")); // P-256 (puede ser otra para encriptar)
        KeyPair encryptKeyPair = encryptKeyGen.generateKeyPair();

        // Inyectar las claves a la instancia de prueba
        injectPrivateField(jwtService, "ecSignerPrivateKey", signerKeyPair.getPrivate());
        injectPrivateField(jwtService, "ecSignerPublicKey", signerKeyPair.getPublic());
        injectPrivateField(jwtService, "ecEncryptPrivateKey", encryptKeyPair.getPrivate());
        injectPrivateField(jwtService, "ecEncryptPublicKey", encryptKeyPair.getPublic());
        injectPrivateField(jwtService, "jwtIssuer", "foro-hub");
        injectPrivateField(jwtService, "expirationInMinutes", 10L);
    }

    @Test
    @DisplayName("Test generate and validate JWT")
    void generateAndValidateJwt() {
        // Arrange
        String username = "user1";
        Role role = new Role(1L, "USER");
        User user = new User();
        user.setUsername(username);
        user.setEmail("test@email.com");
        user.setRole(role);

        RequestAuthentication authRequest = new RequestAuthentication(username, "pass123");

        when(userService.findUserByUsername(username)).thenReturn(user);

        // Act
        String jwt = jwtService.generateTokenJwt(authRequest);

        // Assert
        assertNotNull(jwt);
        assertTrue(jwtService.validateJwtToken(jwt));
        assertEquals(username, jwtService.extractUsernameFromJwt(jwt));
    }

    @Test
    @DisplayName("Test extract Jwt Token From Request")
    void extractJwtTokenFromRequest() {
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getHeader("Authorization")).thenReturn("Bearer abc.def.ghi");

        String token = jwtService.extractJwtTokenFromRequest(request);

        assertEquals("abc.def.ghi", token);
    }

    // Utilidad para inyectar campos privados
    private void injectPrivateField(Object target, String fieldName, Object value) throws Exception {
        Field field = target.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(target, value);
    }
}