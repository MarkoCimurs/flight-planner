package com.io.codelex.flightplanner.services;

import com.io.codelex.flightplanner.models.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private final FlightRepository flightRepository;

    public CustomerService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    public List<Airport> getAirports(String search) {
        return flightRepository.getAirports(search);
    }

    public PageResult<Flight> searchFlights(SearchFlightRequest request) {
        return flightRepository.searchFlight(request);
    }

    public Flight getFlightById(Long id) {
        return flightRepository.getFlightById(id);
    }
}
