package com.jetblue.jetblue_server.SERVICE.Manager;

import com.jetblue.jetblue_server.DOA.Modules.Airports;
import com.jetblue.jetblue_server.DOA.Modules.Flight;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ManagerFlight {
    Optional<Flight> createFlight(Flight flight);

    List<Flight> createFlights(List<Flight> listFlight);

    int availableSeats(Flight flight);

    List<Flight> getFlightSearch(
            String DepartureTime,
            String ArrivalTime,
            String Origin,
            String Destination
    );

    List<Flight> getFlights();

    Flight updateFlight(Flight flight);

    Flight removeFlight(long flightId);

    Optional<Flight> updateSeat(Flight flight, int seat);


}
