package com.example.upgrade.campsite.domain.exceptions;

public class ReservationDatesInvalidException extends RuntimeException  {

    private static final long serialVersionUID = 1L;

    public ReservationDatesInvalidException() {
        super("The campsite can be reserved for max 3 days & can be reserved minimum 1 day(s) ahead of arrival and up to 1 month in advance");
    }

}
