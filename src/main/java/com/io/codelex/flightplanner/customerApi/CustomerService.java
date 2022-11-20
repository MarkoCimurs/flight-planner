package com.io.codelex.flightplanner.customerApi;

import com.io.codelex.flightplanner.Entities.Airport;
import com.io.codelex.flightplanner.Entities.Flight;
import com.io.codelex.flightplanner.Entities.PageResult;
import com.io.codelex.flightplanner.Entities.SearchFlightRequest;
import com.io.codelex.flightplanner.adminApi.FlightRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {

    private final FlightRepository flightRepository;

    public CustomerService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    public List<Airport> getAirports(String search){
        return flightRepository.getAirports(search);
    }

    public PageResult<Flight> searchFlights(SearchFlightRequest request){
        return flightRepository.searchFlights(request);
    }

    public Flight getFlightByID(int id){
        return flightRepository.getFlightByID(id);
    }
}
