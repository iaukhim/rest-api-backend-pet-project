package com.unknown.supportapp.exceptions;

import org.springframework.http.HttpStatus;

public class MailServiceException extends CausedByUserException{
    public MailServiceException() {
        super();
    }

    public MailServiceException(String message) {
        super(message);
    }

    public MailServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public MailServiceException(Throwable cause) {
        super(cause);
    }

    public MailServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return super.getHttpStatus();
    }
}
