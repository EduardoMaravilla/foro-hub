package org.maravill.foro_hub.security.exception;

public class SecurityInvalidJwtException extends RuntimeException{
    public SecurityInvalidJwtException() {
        super();
    }

    public SecurityInvalidJwtException(String message) {
        super(message);
    }

    public SecurityInvalidJwtException(String message, Throwable cause) {
        super(message, cause);
    }
}
