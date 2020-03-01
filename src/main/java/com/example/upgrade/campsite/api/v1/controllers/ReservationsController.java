package com.example.upgrade.campsite.api.v1.controllers;

import com.example.upgrade.campsite.model.Reservation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("api/campsite/v1")
public class ReservationsController implements ReservationsApi {
    @Override
    public ResponseEntity<Void> deleteReservation(String id) {
        return null;
    }

    @Override
    public ResponseEntity<Reservation> getReservation(String id) {
        return null;
    }

    @Override
    public ResponseEntity<Reservation> getReservations() {
        Reservation reservation = new Reservation();
        reservation.setId(0l);
        reservation.fullname("Rowena Dsouza");
        reservation.setEmail("rocknrowe@gmail.com");
        return ResponseEntity.ok(reservation);
    }

    @Override
    public ResponseEntity<Reservation> modifyReservation(String id) {
        return null;
    }

    @Override
    public ResponseEntity<Reservation> saveReservation(@Valid Reservation body) {
        return null;
    }
}
