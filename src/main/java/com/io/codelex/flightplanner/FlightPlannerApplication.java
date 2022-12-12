package com.io.codelex.flightplanner;

import com.io.codelex.flightplanner.model.Airport;
import com.io.codelex.flightplanner.model.Flight;
import com.io.codelex.flightplanner.repository.AirportRepository;
import com.io.codelex.flightplanner.repository.FlightRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDateTime;

@SpringBootApplication
public class FlightPlannerApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlightPlannerApplication.class, args);
	}

}
