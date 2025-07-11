package org.maravill.foro_hub.foro.exception;

public class ForoInvalidDataException extends RuntimeException{
    public ForoInvalidDataException() {
        super();
    }

    public ForoInvalidDataException(String message) {
        super(message);
    }

    public ForoInvalidDataException(String message, Throwable cause) {
        super(message, cause);
    }
}
