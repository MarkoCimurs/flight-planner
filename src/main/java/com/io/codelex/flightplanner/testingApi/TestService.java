package com.io.codelex.flightplanner.testingApi;

import com.io.codelex.flightplanner.adminApi.FlightRepository;
import org.springframework.stereotype.Service;

@Service
public class TestService {

    private final FlightRepository flightRepository;

    public TestService(FlightRepository flightRepository){
        this.flightRepository = flightRepository;
    }

    public void clear(){
        flightRepository.clear();
    }
}
