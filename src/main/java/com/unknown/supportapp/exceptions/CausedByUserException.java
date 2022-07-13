package com.unknown.supportapp.exceptions;

import org.springframework.http.HttpStatus;

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

    public HttpStatus getHttpStatus() {
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }

}
