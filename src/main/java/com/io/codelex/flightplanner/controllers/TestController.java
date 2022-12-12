package com.io.codelex.flightplanner.controllers;

import com.io.codelex.flightplanner.service.FlightServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TestController {

    private final FlightServiceImpl flightService;

    @PostMapping("/clear")
    public ResponseEntity<?> clear() {
        flightService.clear();
        return ResponseEntity.noContent().build();
    }
}
