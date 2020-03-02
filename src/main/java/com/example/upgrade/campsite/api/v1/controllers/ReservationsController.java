package com.example.upgrade.campsite.api.v1.controllers;

import com.example.upgrade.campsite.domain.service.ReservationService;
import com.example.upgrade.campsite.model.Reservation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/campsite/v1")
public class ReservationsController implements ReservationsApi {

    ReservationService reservationService;

    public ReservationsController(final ReservationService reservationService){
        this.reservationService = reservationService;
    }

    @Override
    public ResponseEntity<Void> deleteReservation(String id) {
        return null;
    }

    @Override
    public ResponseEntity<Reservation> getReservation(String id) {
        return null;
    }

    @Override
    public ResponseEntity<List<Reservation>> getReservations() {
        return ResponseEntity.ok(reservationService.getReservations());
    }

    @Override
    public ResponseEntity<Reservation> modifyReservation(String id) {
        return null;
    }

    @Override
    public ResponseEntity<Reservation> saveReservation(@Valid Reservation body) {
        return ResponseEntity.ok(reservationService.saveReservations());
    }
}
