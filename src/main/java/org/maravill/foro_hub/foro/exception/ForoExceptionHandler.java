package org.maravill.foro_hub.foro.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.maravill.foro_hub.foro.dto.ResponseForoApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice(basePackages = "org.maravill.foro_hub.foro")
public class ForoExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseForoApiError> globalUnknowHandler(HttpServletRequest request , Exception ex){
        ResponseForoApiError errorResponse = generateApiResponseError(request,ex,"Error Foro global");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

    @ExceptionHandler(ForoDataNotFoundException.class)
    public ResponseEntity<ResponseForoApiError> foroDataNotFoundHandler(HttpServletRequest request, ForoDataNotFoundException ex){
        ResponseForoApiError errorResponse = generateApiResponseError(request,ex,"Foro Data not found");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(ForoInvalidDataException.class)
    public ResponseEntity<ResponseForoApiError> foroInvalidDataHandler(HttpServletRequest request, ForoInvalidDataException ex){
        ResponseForoApiError errorResponse = generateApiResponseError(request,ex,"Invalid foro data");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    private ResponseForoApiError generateApiResponseError(HttpServletRequest request, Exception ex, String message) {
        return new ResponseForoApiError(ex.getLocalizedMessage(),message,request.getRequestURL().toString(),request.getMethod(), LocalDateTime.now());
    }
}
