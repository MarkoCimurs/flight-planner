package com.io.codelex.flightplanner.controllers;

import com.io.codelex.flightplanner.models.Flight;
import com.io.codelex.flightplanner.services.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/admin-api")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/flights/{id}")
    public synchronized Flight getFlightById(@PathVariable("id") Long id) {
        return adminService.getFlightById(id);
    }

    @PutMapping("/flights")
    @ResponseStatus(HttpStatus.CREATED)
    public Flight addFlight(@Valid @RequestBody Flight flightRequest) {
        return adminService.addFlight(flightRequest);
    }

    @DeleteMapping("/flights/{id}")
    public void deleteFlight(@PathVariable("id") Long id) {
        adminService.deleteFlight(id);
    }
}
