package org.maravill.foro_hub.security.exception;

public class SecurityInvalidDataException extends RuntimeException{
    public SecurityInvalidDataException() {
        super();
    }

    public SecurityInvalidDataException(String message) {
        super(message);
    }

    public SecurityInvalidDataException(String message, Throwable cause) {
        super(message, cause);
    }
}
