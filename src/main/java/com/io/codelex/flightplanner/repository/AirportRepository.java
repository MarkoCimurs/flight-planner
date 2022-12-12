package com.io.codelex.flightplanner.repository;

import com.io.codelex.flightplanner.model.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AirportRepository extends JpaRepository<Airport, Long> {

    @Query("SELECT a FROM Airport a WHERE a.airport LIKE  CONCAT('%',:search,'%') OR a.city LIKE CONCAT('%',:search,'%') OR a.country LIKE CONCAT('%',:search,'%')")
    List<Airport> findBySearchParam(@Param("search")String search);
}
