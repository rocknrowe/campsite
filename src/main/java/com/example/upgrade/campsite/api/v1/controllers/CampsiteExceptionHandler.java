package com.example.upgrade.campsite.api.v1.controllers;

import com.example.upgrade.campsite.domain.exceptions.DatesUnavailableException;
import com.example.upgrade.campsite.domain.exceptions.ReservationDatesInvalidException;
import com.example.upgrade.campsite.domain.exceptions.ReservationNotFoundException;
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
            DatesUnavailableException ex, WebRequest request) {

        Error error = new Error();
        error.setCode(String.valueOf(HttpStatus.CONFLICT.value()));
        error.setMessage("Could not save. Conflict with existing resource");

        return handleExceptionInternal(ex, error,
                new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(value = { ReservationDatesInvalidException.class })
    protected ResponseEntity<Object> handleConflict(
            ReservationDatesInvalidException ex, WebRequest request) {

        Error error = new Error();
        error.setCode(String.valueOf(HttpStatus.BAD_REQUEST.value()));
        error.setMessage(ex.getMessage());

        return handleExceptionInternal(ex, error,
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = { ReservationNotFoundException.class })
    protected ResponseEntity<Object> handleConflict(
            ReservationNotFoundException ex, WebRequest request) {

        Error error = new Error();
        error.setCode(String.valueOf(HttpStatus.NOT_FOUND.value()));
        error.setMessage(ex.getMessage());

        return handleExceptionInternal(ex, error,
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

}
