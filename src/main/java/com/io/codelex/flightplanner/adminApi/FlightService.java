package com.io.codelex.flightplanner.adminApi;

import com.io.codelex.flightplanner.Entities.AddFlightRequest;
import com.io.codelex.flightplanner.Entities.Flight;
import org.springframework.stereotype.Service;

@Service
public class FlightService {

    private final FlightRepository flightRepository;

    public FlightService(FlightRepository flightRepository){
        this.flightRepository = flightRepository;
    }

    public Flight getFLightByID(int id){
        return flightRepository.getFlightByID(id);
    }

    public synchronized Flight addFlight(AddFlightRequest flightRequest){
        return flightRepository.addFlight(flightRequest);
    }

    public void deleteFlight(int id){
        flightRepository.deleteFlight(id);
    }

}
