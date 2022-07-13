package com.unknown.supportapp.exceptions;

public abstract class CausedByUserException extends RuntimeException{
    public CausedByUserException() {
    }

    public CausedByUserException(String message) {
        super(message);
    }

    public CausedByUserException(String message, Throwable cause) {
        super(message, cause);
    }

    public CausedByUserException(Throwable cause) {
        super(cause);
    }

    public CausedByUserException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
