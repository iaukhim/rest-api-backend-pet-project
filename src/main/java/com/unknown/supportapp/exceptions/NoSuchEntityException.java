package com.unknown.supportapp.exceptions;

import org.springframework.http.HttpStatus;

public class NoSuchEntityException extends CausedByUserException{

    private HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
    public NoSuchEntityException() {
    }

    public NoSuchEntityException(String message) {
        super(message);
    }

    public NoSuchEntityException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchEntityException(Throwable cause) {
        super(cause);
    }

    public NoSuchEntityException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
