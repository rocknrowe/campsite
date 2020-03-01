package com.example.upgrade.campsite.api.v1.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/campsite/v1")
public class ReservationsController {
    @GetMapping(value = "/reservations", produces = { "application/json" })
    public ResponseEntity<String> getReservations() {
        return ResponseEntity.ok("Hello");
    }
}
