package com.example.upgrade.campsite.domain.exceptions;

public class DatesUnavailableException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public DatesUnavailableException(String errorMessage) {
        super(errorMessage);
    }

}
