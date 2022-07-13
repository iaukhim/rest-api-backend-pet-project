package com.unknown.supportapp.exceptions.handlers;

import com.unknown.supportapp.exceptions.CausedByUserException;
import com.unknown.supportapp.exceptions.NoSuchEntityException;
import com.unknown.supportapp.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ControllerAdviceExceptionHandler {

    private static Map<Class, HttpStatus> httpCodesMap;



    public ControllerAdviceExceptionHandler() {

        httpCodesMap = new HashMap<>();
        httpCodesMap.put(NoSuchEntityException.class, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CausedByUserException.class)
    public ResponseEntity<Response> handleException(CausedByUserException e, HttpServletRequest request){
        Response response = new Response(new Date(System.currentTimeMillis()), httpCodesMap.get(e.getClass()).toString(), e.getMessage(), request.getRequestURI());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
