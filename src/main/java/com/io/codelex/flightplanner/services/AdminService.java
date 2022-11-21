package com.io.codelex.flightplanner.services;

import com.io.codelex.flightplanner.models.Flight;
import com.io.codelex.flightplanner.models.FlightRepository;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    private final FlightRepository flightRepository;

    public AdminService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    public Flight getFlightById(Long id) {
        return flightRepository.getFlightById(id);
    }

    public Flight addFlight(Flight request) {
        return flightRepository.addFlight(request);
    }

    public void deleteFlight(Long id) {
        flightRepository.deleteFlight(id);
    }
}
