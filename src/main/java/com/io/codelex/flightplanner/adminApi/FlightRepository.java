package com.io.codelex.flightplanner.adminApi;

import com.io.codelex.flightplanner.Entities.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Repository
public class FlightRepository {

    private final List<Flight> flightList = new ArrayList<>();
    private final AtomicInteger flightID = new AtomicInteger(0);
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");


    public Flight addFlight(AddFlightRequest flightRequest){
       Flight flight = new Flight(flightID.incrementAndGet(),
                            flightRequest.getFrom(),
                            flightRequest.getTo(),
                            flightRequest.getCarrier(),
                            LocalDateTime.parse(flightRequest.getDepartureTime(), formatter),
                            LocalDateTime.parse(flightRequest.getArrivalTime(), formatter));

       if(checkIfFlightValid(flight)){
           flightList.add(flight);
           return flight;
       } else {
           throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
       }
    }

    public Flight getFlightByID(int id){
        return flightList.stream().filter(flight -> flight.getId() == id).findFirst().orElse(null);
    }

    public PageResult<Flight> searchFlights(SearchFlightRequest request){
        LocalDateTime departureTime = LocalDateTime.parse(request.getDepartureDate(), formatter);

        Predicate<Flight> mathcingDate = flight -> flight.getDepartureTime().equals(departureTime);
        Predicate<Flight> mathcingFrom = flight -> flight.getFrom().getAirport().equals(request.getFrom());
        Predicate<Flight> mathcingTo = flight -> flight.getTo().getAirport().equals(request.getTo());

        List<Flight> filteredFlights = flightList.stream()
                .filter(mathcingDate)
                .filter(mathcingFrom)
                .filter(mathcingTo)
                .collect(Collectors.toList());

        return new PageResult<>(0, filteredFlights.size(), filteredFlights);

    }

    public List<Airport> getAirports(String search){
        return new ArrayList<>();
    }

    public void deleteFlight(int id){
        flightList.removeIf(flight -> flight.getId() == id);
    }

    public void clear(){
        flightList.clear();
    }

    private boolean checkIfFlightValid(Flight flight){
        if(flight.getDepartureTime().isEqual(flight.getArrivalTime()) ||
            flight.getDepartureTime().isAfter(flight.getArrivalTime())){
            return false;
        }

        if(flightList.stream().anyMatch(f -> f.equals(flight))){
            return false;
        }

        if(flight.getTo().equals(flight.getFrom())){
            return false;
        }

        return true;
    }
}
