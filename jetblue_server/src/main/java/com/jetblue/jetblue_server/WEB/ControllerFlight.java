package com.jetblue.jetblue_server.WEB;

import com.jetblue.jetblue_server.Constums.AppException;
import com.jetblue.jetblue_server.DOA.Modules.Flight;
import com.jetblue.jetblue_server.SERVICE.Manager.ManagerFlight;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v2")
@CrossOrigin(value = "http://localhost:4401/")
public class ControllerFlight {

    private final ManagerFlight managerFlight;

    public ControllerFlight(ManagerFlight managerFlight) {
        this.managerFlight = managerFlight;
    }

    @PostMapping(
            path = "/createFlight",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Flight> createFlight(
            @RequestBody Flight flight
    ) {
        try {
            System.out.println(flight);
            Optional<Flight> flightOptional = managerFlight.createFlight(flight);
            if (flightOptional.isPresent()) {
                return ResponseEntity.status(200).body(flightOptional.get());
            }
            throw new Exception("Flight can't be added");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(400).body(null);
        }
    }

    @GetMapping(
            path = "/getFlights/{origin}/{arrival}/{departureTime}/{arrivalTime}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> getFlights(
            @PathVariable("origin") String origin,
            @PathVariable("arrival") String arrival,
            @PathVariable("departureTime") String departureTime,
            @PathVariable("arrivalTime") String arrivalTime
    ) {
        try {

            List<Flight> flights = this.managerFlight.getFlightSearch(
                    departureTime,
                    arrivalTime,
                    origin,
                    arrival
            );
            if (!flights.isEmpty()) {
                return ResponseEntity.status(404).body(flights);
            }
            throw new AppException("Not found any flights !!!", HttpStatus.BAD_REQUEST);

        } catch (Exception e) {
            return ResponseEntity.status(400).body(null);
        }
    }
}
