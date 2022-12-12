package com.io.codelex.flightplanner.service;

import com.io.codelex.flightplanner.dto.PageResult;
import com.io.codelex.flightplanner.model.Airport;
import com.io.codelex.flightplanner.model.Flight;
import com.io.codelex.flightplanner.dto.SearchFlightRequest;

import java.util.Collection;


public interface FlightService {
    Flight getFlightById(Long id);
    Flight createFlight(Flight flight);
    void deleteFlightById(Long id);
    PageResult<Flight> searchFlights(SearchFlightRequest searchFlightRequest);
    Collection<Airport> getAirports(String search);
    void clear();
}
