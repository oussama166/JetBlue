package com.jetblue.jetblue_server.DOA.Repository;

import com.jetblue.jetblue_server.DOA.Modules.FlightBooking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryFlightBooking extends JpaRepository<FlightBooking,Long> {
}
