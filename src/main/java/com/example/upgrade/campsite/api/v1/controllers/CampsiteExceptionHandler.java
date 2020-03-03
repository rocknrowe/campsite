package com.example.upgrade.campsite.api.v1.controllers;

import com.example.upgrade.campsite.domain.exceptions.DatesUnavailableException;
import com.example.upgrade.campsite.model.Error;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CampsiteExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { DatesUnavailableException.class })
    protected ResponseEntity<Object> handleConflict(
            RuntimeException ex, WebRequest request) {

        Error error = new Error();
        error.setMessage("Could not save. Conflict with existing resource");

        return handleExceptionInternal(ex, error,
                new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

}
