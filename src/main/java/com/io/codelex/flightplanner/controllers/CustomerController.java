package com.io.codelex.flightplanner.controllers;

import com.io.codelex.flightplanner.dto.PageResult;
import com.io.codelex.flightplanner.model.Airport;
import com.io.codelex.flightplanner.model.Flight;
import com.io.codelex.flightplanner.dto.SearchFlightRequest;
import com.io.codelex.flightplanner.service.FlightServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CustomerController {

    private final FlightServiceImpl flightService;

    @GetMapping("/airports")
    public ResponseEntity<List<Airport>> searchAirports(@RequestParam String search) {
        return ResponseEntity.ok(flightService.getAirports(search));
    }

    @PostMapping("/flights/search")
    public ResponseEntity<PageResult<Flight>> searchFlight(@Valid @RequestBody SearchFlightRequest searchFlightRequest) {
        return ResponseEntity.ok(flightService.searchFlights(searchFlightRequest));
    }

    @GetMapping("/flights/{id}")
    public ResponseEntity<Flight> findFlightById(@PathVariable Long id) {
        return ResponseEntity.ok(flightService.getFlightById(id));
    }

}
