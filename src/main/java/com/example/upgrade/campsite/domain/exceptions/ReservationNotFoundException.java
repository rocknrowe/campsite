package com.example.upgrade.campsite.domain.exceptions;

public class ReservationNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ReservationNotFoundException() {
        super("Reservation does not exist");
    }
}
