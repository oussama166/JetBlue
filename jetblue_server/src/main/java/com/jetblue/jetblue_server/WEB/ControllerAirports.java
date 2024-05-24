package com.jetblue.jetblue_server.WEB;

import com.jetblue.jetblue_server.Constums.AppException;
import com.jetblue.jetblue_server.DOA.Modules.Airports;
import com.jetblue.jetblue_server.SERVICE.Manager.ManagerAiports;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v2")
@CrossOrigin(value = "http://localhost:4401/")
public class ControllerAirports {

    // Inject the airports manager
    private final ManagerAiports managerAiports;

    @GetMapping(
            path = "/getAirports",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> getAirports(@RequestBody Airports airport) {
        try {
            Optional<Airports> airports = managerAiports.getAirports(airport);
            if (airports.isPresent()) {
                return ResponseEntity.ok(airports.get());
            }
            throw new AppException("Airport not found ... !", HttpStatus.BAD_REQUEST);
        } catch (AppException e) {
            return ResponseEntity.status(e.getHttpStatus()).body(e.getMessage());
        }
    }

    @GetMapping(
            path = "/getAirport/{airportname}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> getAirport(@PathVariable("airportname") String airportname) {
        try {
            Optional<List<Airports>> airports = managerAiports.getAirports(airportname);
            if (airports.isPresent()) {
                return ResponseEntity.ok(airports.get());
            }
            throw new AppException("No airport define by this name ... !", HttpStatus.BAD_REQUEST);
        } catch (AppException e) {
            return ResponseEntity.status(e.getHttpStatus()).body(e.getMessage());
        }
    }

    @GetMapping(
            path = "/getAirports/{City}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> getAirports(@PathVariable("City") String City) {
        try {
            Optional<List<Airports>> airportsInCity = managerAiports.getAirportsInCity(City);
            if (airportsInCity.isPresent()) {
                return ResponseEntity.ok(airportsInCity.get());
            }
            throw new AppException("No airport define by this name ... !", HttpStatus.BAD_REQUEST);
        }
        catch (AppException e) {
            return ResponseEntity.status(e.getHttpStatus()).body(e.getMessage());
        }
    }

    @GetMapping(
            path = "/getAirportsCountry/{country}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> getAirportsCountry(@PathVariable("country") String Country) {
        try {
            Optional<List<Airports>> airportsInCountry = managerAiports.getAirportsInCountry(Country);
            if (airportsInCountry.isPresent()) {
                return ResponseEntity.ok(airportsInCountry.get());
            }
            throw new AppException("No airport define by this name ... !", HttpStatus.BAD_REQUEST);
        }
        catch (AppException e) {
            return ResponseEntity.status(e.getHttpStatus()).body(e.getMessage());
        }
    }

}
