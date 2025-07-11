package org.maravill.foro_hub.security.exception;

public class SecurityDataNotFoundException extends RuntimeException{

    public SecurityDataNotFoundException() {
    }

    public SecurityDataNotFoundException(String message) {
        super(message);
    }

    public SecurityDataNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
