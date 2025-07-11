package org.maravill.foro_hub.foro.exception;

public class ForoDataNotFoundException extends RuntimeException{
    public ForoDataNotFoundException() {
        super();
    }

    public ForoDataNotFoundException(String message) {
        super(message);
    }

    public ForoDataNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
