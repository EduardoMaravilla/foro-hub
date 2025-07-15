package org.maravill.foro_hub.global.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.maravill.foro_hub.global.dto.ResponseApiError;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@Order
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseApiError> globalUnknowHandler(HttpServletRequest request , Exception ex){
        ResponseApiError errorResponse = generateApiResponseError(request,ex,"Unknown or not yet mapped error");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

    private ResponseApiError generateApiResponseError(HttpServletRequest request, Exception ex, String message) {
        return new ResponseApiError(ex.getLocalizedMessage(),message,request.getRequestURL().toString(),request.getMethod(), LocalDateTime.now());
    }
}
