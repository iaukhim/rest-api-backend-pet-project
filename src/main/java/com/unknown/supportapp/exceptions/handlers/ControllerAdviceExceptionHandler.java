package com.unknown.supportapp.exceptions.handlers;

import com.unknown.supportapp.exceptions.CausedByUserException;
import com.unknown.supportapp.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;


@ControllerAdvice
public class ControllerAdviceExceptionHandler {

    public ControllerAdviceExceptionHandler() {
    }

    @ExceptionHandler(CausedByUserException.class)
    public ResponseEntity<Response> handleException(CausedByUserException e, HttpServletRequest request){
        Response response = new Response(new Date(System.currentTimeMillis()), e.getHttpStatus().toString(), e.getMessage(), request.getRequestURI());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
