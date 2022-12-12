package com.io.codelex.flightplanner.controllers;


import com.io.codelex.flightplanner.model.Flight;
import com.io.codelex.flightplanner.service.FlightServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/admin-api")
@RequiredArgsConstructor
public class AdminController {
    private final FlightServiceImpl flightService;

    @GetMapping("/flights/{id}")
    public synchronized ResponseEntity<Flight> getFlightById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(flightService.getFlightById(id));
    }

    @PutMapping("/flights")
    public ResponseEntity<Flight> addFlight(@Valid @RequestBody Flight flight) {
        return ResponseEntity.ok(flightService.createFlight(flight));
    }

    @DeleteMapping("/flights/{id}")
    public ResponseEntity<?> deleteFlight(@PathVariable("id") Long id) {
        flightService.deleteFlightById(id);
        return ResponseEntity.noContent().build();
    }
}
