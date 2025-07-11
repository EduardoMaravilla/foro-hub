package org.maravill.foro_hub.security.exception;

public class SecurityInvalidPasswordException extends RuntimeException{
    public SecurityInvalidPasswordException() {
        super();
    }

    public SecurityInvalidPasswordException(String message) {
        super(message);
    }

    public SecurityInvalidPasswordException(String message, Throwable cause) {
        super(message, cause);
    }
}
