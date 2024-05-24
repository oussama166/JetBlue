package com.jetblue.jetblue_server.DOA.Repository;

import com.jetblue.jetblue_server.DOA.Modules.Airports;
import com.jetblue.jetblue_server.DOA.Modules.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface RepositoryFligth extends JpaRepository<Flight,Long> {
    @Query(
            value = "SELECT f FROM Flight f WHERE f.DepartureTime = ?1 OR f.ArrivalTime = ?2 OR f.Origin.name Like %?3% OR f.Destination.name LIKE %?4%"
    )
    Optional<List<Flight>> findByDepartureTimeOrArrivalTimeOrOriginOrDestination(
            LocalDateTime DepartureTime,
            LocalDateTime ArrivalTime,
            String Origin,
            String Destination
    );
}
