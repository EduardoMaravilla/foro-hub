package org.maravill.foro_hub.security.service.impl;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.ECDHDecrypter;
import com.nimbusds.jose.crypto.ECDHEncrypter;
import com.nimbusds.jose.crypto.ECDSASigner;
import com.nimbusds.jose.crypto.ECDSAVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.maravill.foro_hub.security.dto.RequestAuthentication;
import org.maravill.foro_hub.security.exception.SecurityInvalidJwtException;
import org.maravill.foro_hub.security.models.User;
import org.maravill.foro_hub.security.service.IJwtService;
import org.maravill.foro_hub.security.service.IUserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Base64;
import java.util.Date;

@Slf4j
@Service
public class JwtServiceImpl implements IJwtService {

    @Value("${spring.application.name}")
    private String jwtIssuer;
    @Value("${jwt.secret.token.signer.private}")
    private String privateSignerKeyBase64;
    @Value("${jwt.secret.token.signer.public}")
    private String publicSignerKeyBase64;
    @Value("${jwt.secret.token.encrypt.private}")
    private String privateEncryptKeyBase64;
    @Value("${jwt.secret.token.encrypt.public}")
    private String publicEncryptSignerKeyBase64;

    @Value("${jwt.token.expiration-in-minutes}")
    private Long expirationInMinutes;

    private ECPrivateKey ecSignerPrivateKey;
    private ECPublicKey ecSignerPublicKey;
    private ECPrivateKey ecEncryptPrivateKey;
    private ECPublicKey ecEncryptPublicKey;

    private final IUserService userService;

    public JwtServiceImpl(IUserService userService) {
        this.userService = userService;
    }

    @PostConstruct
    public void init() throws NoSuchAlgorithmException, InvalidKeySpecException {
        validateBase64(privateSignerKeyBase64, "Private Signer Key Base64");
        validateBase64(publicSignerKeyBase64, "Public Signer Key Base64");
        validateBase64(privateEncryptKeyBase64, "Private Encrypt Key Base64");
        validateBase64(publicEncryptSignerKeyBase64, "Public Encrypt Key Base64");

        this.ecSignerPrivateKey = getEcPrivateKey(privateSignerKeyBase64);
        this.ecSignerPublicKey = getEcPublicKey(publicSignerKeyBase64);

        this.ecEncryptPrivateKey = getEcPrivateKey(privateEncryptKeyBase64);
        this.ecEncryptPublicKey = getEcPublicKey(publicEncryptSignerKeyBase64);
    }




    @Override
    public String extractJwtTokenFromRequest(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7);
        }
        return null;
    }

    @Override
    public boolean validateJwtToken(String jwt) {
        try {
            // 1. Desencriptar el JWE
            JWEObject jweObject = JWEObject.parse(jwt);
            jweObject.decrypt(new ECDHDecrypter(ecEncryptPrivateKey));

            // 2. Obtener el JWT firmado (JWS)
            SignedJWT signedJWT = jweObject.getPayload().toSignedJWT();
            if (signedJWT == null) return false;

            // 3. Verificar la firma
            boolean signatureValid = signedJWT.verify(new ECDSAVerifier(ecSignerPublicKey));
            if (!signatureValid) return false;

            // 4. Validar claims
            JWTClaimsSet claims = signedJWT.getJWTClaimsSet();
            String username = claims.getSubject();
            User user = userService.findUserByUsername(username);

            LocalDateTime expirationTime = dateToLocalDateTime(claims.getExpirationTime());
            return expirationTime.isAfter(LocalDateTime.now()) &&
                    jwtIssuer.equals(claims.getIssuer()) &&
                    user.getRole().getName().equals(claims.getClaim("role"));
        } catch (Exception e) {
            log.warn("Error validating JWT: {}", e.getLocalizedMessage());
            return false;
        }
    }

    @Override
    public String extractUsernameFromJwt(String jwt) {
        try {
            JWEObject jweObject = JWEObject.parse(jwt);
            jweObject.decrypt(new ECDHDecrypter(ecEncryptPrivateKey));
            SignedJWT signedJWT = jweObject.getPayload().toSignedJWT();
            return signedJWT.getJWTClaimsSet().getSubject();
        } catch (Exception e) {
            throw new SecurityInvalidJwtException("Invalid JWT format", e);
        }
    }

    @Override
    public String generateTokenJwt(RequestAuthentication requestAuthentication) {
        try {
            User user = userService.findUserByUsername(requestAuthentication.username());
            LocalDateTime issuedAt = LocalDateTime.now();
            LocalDateTime expiresAt = issuedAt.plusMinutes(expirationInMinutes);

            // 1. Crear los claims
            JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                    .subject(user.getUsername())
                    .issuer(jwtIssuer)
                    .issueTime(localDateTimeToDate(issuedAt))
                    .expirationTime(localDateTimeToDate(expiresAt))
                    .claim("role", user.getRole().getName())
                    .build();

            // 2. Firmar el JWT
            SignedJWT signedJWT = new SignedJWT(
                    new JWSHeader.Builder(JWSAlgorithm.ES512)
                            .type(JOSEObjectType.JWT)
                            .build(),
                    claimsSet
            );
            signedJWT.sign(new ECDSASigner(ecSignerPrivateKey));

            // 3. Encriptar el JWT firmado (JWE)
            JWEHeader jweHeader = new JWEHeader.Builder(
                    JWEAlgorithm.ECDH_ES_A256KW,
                    EncryptionMethod.A256GCM
            )
                    .contentType("JWT") // indica que el contenido es un JWT
                    .build();

            JWEObject jweObject = new JWEObject(jweHeader, new Payload(signedJWT));
            jweObject.encrypt(new ECDHEncrypter(ecEncryptPublicKey));

            return jweObject.serialize();

        } catch (Exception e) {
            log.error("Error generating encrypted JWT", e);
            throw new SecurityInvalidJwtException("Error generating encrypted JWT", e);
        }
    }

    private ECPrivateKey getEcPrivateKey(String base64PrivateKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] keyBytes = getKeyBytes(base64PrivateKey);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("EC");
        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
        return (ECPrivateKey) privateKey;
    }

    private ECPublicKey getEcPublicKey(String base64PublicKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] keyBytes = getKeyBytes(base64PublicKey);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("EC");
        PublicKey publicKey = keyFactory.generatePublic(keySpec);
        return (ECPublicKey) publicKey;
    }

    private byte[] getKeyBytes(String base64EncodedPem) {
        String pem = new String(Base64.getDecoder().decode(base64EncodedPem));
        pem = pem.replaceAll("-----BEGIN [A-Z ]+-----", "")
                .replaceAll("-----END [A-Z ]+-----", "")
                .replaceAll("\\s+", "");
        return Base64.getDecoder().decode(pem);
    }

    private void validateBase64(String base64, String keyName) {
        if (base64 == null || base64.isEmpty()) {
            throw new IllegalArgumentException(keyName + " cannot be null or empty");
        }
        try {
            Base64.getDecoder().decode(base64);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid Base64 encoding for " + keyName, e);
        }
    }

    private Date localDateTimeToDate(LocalDateTime localDate) {
        return Date.from(localDate.atZone(ZoneId.systemDefault()).toInstant());
    }

    private LocalDateTime dateToLocalDateTime(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }
}
