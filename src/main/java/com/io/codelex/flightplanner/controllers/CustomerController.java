package com.io.codelex.flightplanner.controllers;

import com.io.codelex.flightplanner.models.Airport;
import com.io.codelex.flightplanner.models.Flight;
import com.io.codelex.flightplanner.models.PageResult;
import com.io.codelex.flightplanner.models.SearchFlightRequest;
import com.io.codelex.flightplanner.services.CustomerService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/airports")
    public List<Airport> getAirports(@RequestParam("search") String search) {
        return customerService.getAirports(search);
    }

    @PostMapping("/flights/search")
    public PageResult<Flight> searchFlights(@Valid @RequestBody SearchFlightRequest flightRequest) {
        return customerService.searchFlights(flightRequest);
    }

    @GetMapping("/flights/{id}")
    public Flight getFlightById(@PathVariable("id") Long id) {
        return customerService.getFlightById(id);
    }

}
