package com.io.codelex.flightplanner.customerApi;

import com.io.codelex.flightplanner.Entities.Airport;
import com.io.codelex.flightplanner.Entities.Flight;
import com.io.codelex.flightplanner.Entities.PageResult;
import com.io.codelex.flightplanner.Entities.SearchFlightRequest;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerController {

    private final CustomerService customerServiceService;

    public CustomerController(CustomerService customerServiceService){
        this.customerServiceService = customerServiceService;
    }

    @GetMapping("airports")
    public List<Airport> getAirports(@RequestParam("search") String search) {
        return customerServiceService.getAirports(search);
    }

    @PostMapping("/flights/search")
    public PageResult<Flight> searchFlights(@Valid @RequestBody SearchFlightRequest request){
        return customerServiceService.searchFlights(request);
    }

    @GetMapping("/flights/{id}")
    public Flight getFlightByID(@PathVariable int id){
        return customerServiceService.getFlightByID(id);
    }


    //GET - /airports ... can contain query
    //POST -/flights/search ... body can contain date from to
    //GET -/flights/{id}


}
