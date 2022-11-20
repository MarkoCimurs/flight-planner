package com.io.codelex.flightplanner.adminApi;


import com.io.codelex.flightplanner.Entities.AddFlightRequest;
import com.io.codelex.flightplanner.Entities.Flight;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/admin-api")
public class FlightController {

    private final FlightService flightService;

    public FlightController(FlightService flightService){
        this.flightService = flightService;
    }

    @GetMapping("/flights/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Flight getFlightByID(@PathVariable("id") int id){
        return flightService.getFLightByID(id);
    }

    @PutMapping("/flights")
    @ResponseStatus(HttpStatus.CREATED)
    public Flight addFlight(@Valid @RequestBody AddFlightRequest flightRequest){
        return flightService.addFlight(flightRequest);
    }

    @DeleteMapping("/flights/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteFlight(@PathVariable("id") int id){
        flightService.deleteFlight(id);
    }

}
