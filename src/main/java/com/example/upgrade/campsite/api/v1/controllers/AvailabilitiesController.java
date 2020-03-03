package com.example.upgrade.campsite.api.v1.controllers;

import com.example.upgrade.campsite.domain.service.AvailabilityService;
import com.example.upgrade.campsite.domain.service.ReservationService;
import com.example.upgrade.campsite.model.Reservation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("api/campsite/v1")
public class AvailabilitiesController {

    private ReservationService reservationService;
    private AvailabilityService availabilityService;

    public AvailabilitiesController(final ReservationService reservationService,
                                    final AvailabilityService availabilityService){
        this.reservationService = reservationService;
        this.availabilityService = availabilityService;
    }

    @GetMapping(value = "/availabilities", produces = "application/json")
    public ResponseEntity<List<Reservation>> getReservation(@RequestParam(required = true) String startDate, @RequestParam(required = false) String endDate) {
        return ResponseEntity.ok(reservationService.getOverLappingReservations(LocalDate.parse(startDate, DateTimeFormatter.ISO_DATE),
                LocalDate.parse(endDate, DateTimeFormatter.ISO_DATE)));
    }

    @GetMapping(value = "/availabilities/dates", produces = "application/json")
    public ResponseEntity<List<LocalDate>> getAvailabilities(@RequestParam(required = true) String startDate, @RequestParam(required = false) String endDate) {
        return ResponseEntity.ok(availabilityService.getAvailabilities(LocalDate.parse(startDate, DateTimeFormatter.ISO_DATE),
                LocalDate.parse(endDate, DateTimeFormatter.ISO_DATE)));
    }
}
