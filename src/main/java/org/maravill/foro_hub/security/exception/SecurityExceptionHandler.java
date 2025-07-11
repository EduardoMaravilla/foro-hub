package org.maravill.foro_hub.security.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.maravill.foro_hub.security.dto.ResponseSecurityApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice(basePackages = "org.maravill.foro_hub.security")
public class SecurityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseSecurityApiError> globalSecurityUnknowHandler(HttpServletRequest request , Exception ex){
        ResponseSecurityApiError errorResponse = generateApiResponseError(request,ex,"Security flaw");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

    @ExceptionHandler(SecurityDataNotFoundException.class)
    public ResponseEntity<ResponseSecurityApiError> securityDataNotFoundHandler(HttpServletRequest request, SecurityDataNotFoundException ex){
        ResponseSecurityApiError errorResponse = generateApiResponseError(request,ex,"Safety data not found");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(SecurityInvalidDataException.class)
    public ResponseEntity<ResponseSecurityApiError> securityInvalidDataHandler(HttpServletRequest request, SecurityInvalidDataException ex){
        ResponseSecurityApiError errorResponse = generateApiResponseError(request,ex,"Invalid security data");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(SecurityInvalidPasswordException.class)
    public ResponseEntity<ResponseSecurityApiError> securityInvalidPasswordHandler(HttpServletRequest request, SecurityInvalidPasswordException ex){
        ResponseSecurityApiError errorResponse = generateApiResponseError(request,ex,"Invalid credentials");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    private ResponseSecurityApiError generateApiResponseError(HttpServletRequest request, Exception ex, String message) {
        return new ResponseSecurityApiError(ex.getLocalizedMessage(),message,request.getRequestURL().toString(),request.getMethod(), LocalDateTime.now());
    }
}
