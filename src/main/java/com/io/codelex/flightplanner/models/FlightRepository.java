package com.io.codelex.flightplanner.models;


import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Repository
public class FlightRepository {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    List<Flight> flightList = new ArrayList<>();
    List<Airport> airports = new ArrayList<>();
    Long availID = 0L;

    public Flight addFlight(Flight request) {
        if (checkIfFlightIsValid(request)) {
            request.setId(availID);
            availID++;
            flightList.add(request);
            addAirportToList(request);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        return request;
    }

    public Flight getFlightById(Long id) {
        return flightList.stream().filter(flight -> Objects.equals(flight.getId(), id)).findFirst().orElse(null);
    }

    public PageResult<Flight> searchFlight(SearchFlightRequest request) {
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

    public List<Airport> getAirports(String search) {
        String searchStr = search.trim().toLowerCase();
        return airports.stream().filter(airport -> airportContainsSearchStr(airport, searchStr)).collect(Collectors.toList());
    }

    public void deleteFlight(Long id) {
        flightList.removeIf(flight -> Objects.equals(flight.getId(), id));
    }

    public void clear() {
        flightList.clear();
    }

    private boolean checkIfFlightIsValid(Flight flight) {
        if (flight.getDepartureTime().isEqual(flight.getArrivalTime()) ||
                flight.getDepartureTime().isBefore(flight.getArrivalTime())) {
            return false;
        }

        if (flightList.stream().anyMatch(flight::equals)) {
            return false;
        }

        return true;
    }

    private void addAirportToList(Flight flight) {
        if (!airports.stream().anyMatch(flight.getFrom()::equals)) {
            airports.add(flight.getFrom());
        }
        if (!airports.stream().anyMatch(flight.getTo()::equals)) {
            airports.add(flight.getTo());
        }

    }

    private boolean airportContainsSearchStr(Airport airport, String search) {
        return airport.getAirport().contains(search) ||
                airport.getCity().contains(search) ||
                airport.getCountry().contains(search);
    }
}
