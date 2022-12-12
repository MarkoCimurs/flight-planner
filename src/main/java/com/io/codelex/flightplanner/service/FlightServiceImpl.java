package com.io.codelex.flightplanner.service;

import com.io.codelex.flightplanner.dto.PageResult;
import com.io.codelex.flightplanner.model.Airport;
import com.io.codelex.flightplanner.model.Flight;
import com.io.codelex.flightplanner.dto.SearchFlightRequest;
import com.io.codelex.flightplanner.repository.AirportRepository;
import com.io.codelex.flightplanner.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional
public class FlightServiceImpl implements FlightService{

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private final FlightRepository flightRepository;
    private final AirportRepository airportRepository;

    @Override
    public Flight getFlightById(Long id) {
        return flightRepository.findById(id).orElse(null);
    }

    @Override
    public Flight createFlight(Flight flight) {
        return flightRepository.save(flight);
    }

    @Override
    public void deleteFlightById(Long id) {
        flightRepository.deleteById(id);
    }

    @Override
    public PageResult<Flight> searchFlights(SearchFlightRequest searchFlightRequest) {
        LocalDateTime departureTime = LocalDateTime.parse(searchFlightRequest.getDepartureDate(), formatter);

        Predicate<Flight> mathcingDate = flight -> flight.getDepartureTime().equals(departureTime);
        Predicate<Flight> mathcingFrom = flight -> flight.getFrom().getAirport().equals(searchFlightRequest.getFrom());
        Predicate<Flight> mathcingTo = flight -> flight.getTo().getAirport().equals(searchFlightRequest.getTo());

        List<Flight> filteredFlights = flightRepository.findAll().stream()
                .filter(mathcingDate)
                .filter(mathcingFrom)
                .filter(mathcingTo).toList();

        return new PageResult<>(0, filteredFlights.size(), filteredFlights);
    }

    @Override
    public List<Airport> getAirports(String search) {
        return airportRepository.findBySearchParam(search);
    }

    @Override
    public void clear() {
        flightRepository.deleteAll();
    }
}
