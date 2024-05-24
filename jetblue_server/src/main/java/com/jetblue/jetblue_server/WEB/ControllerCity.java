package com.jetblue.jetblue_server.WEB;

import com.jetblue.jetblue_server.DOA.Modules.City;
import com.jetblue.jetblue_server.SERVICE.Manager.ManagerCity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/v2")
@CrossOrigin(value = "http://localhost:4401/")
public class ControllerCity {
    @Autowired
    private ManagerCity managerCity;

    @GetMapping(
            path = "/allCities",
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<?> getAllCities() {
        List<String> listCities = new ArrayList<>();
        try {
            Optional<List<String>> cities = managerCity.getAllCities();
            if (cities.isPresent()) {
                return ResponseEntity.status(200).body(cities.get());
            }
            throw new Exception("Cities can not be provided");
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @GetMapping(
            path = "/getByName",
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<?> getCitieByName(
            @RequestParam String nameCountry
    ) {
        try {
            Optional<List<City>> cities = managerCity.getCitieByName(nameCountry);
            if (cities.isPresent()) {
                return ResponseEntity.status(200).body(cities.get());
            }
            throw new Exception("city not match in db");
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @GetMapping(
            path = "/getByCountryCode",
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<?> getCityByCountryCode(
            @RequestParam String countryCode
    ) {
        try {
            Optional<List<City>> cities = managerCity.getCitiesByContryCode(countryCode);
            if (cities.isPresent() && !cities.get().isEmpty()) {
                return ResponseEntity.status(200).body(cities.get());
            }
            throw new Exception("This Country Code doesn't exist or he had any city");
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @GetMapping(
            path = "/getCitiesByCountry",
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<?> getCitiesByCountry(
            @RequestParam String nameCountry
    ) {
        try {
            Optional<List<String>> cities = managerCity.getCitiesReferenceByCountry(nameCountry);
            if (cities.isPresent()) {
                return ResponseEntity.status(200).body(cities.get());
            }
            throw new Exception("Country not found");
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @GetMapping(
            path = "/getCitiesNameByCountryAndState/{state},{country}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> getCitiesByNameOfCountryAndState(
            @PathVariable("country") String country,
            @PathVariable("state") String state
    ) {
        try {
            Optional<List<String>> cities = managerCity.getCitiesReferenceByState(state, country);
            if (cities.isPresent()) {
                return ResponseEntity.status(200).body(cities.get());
            }
            throw new Exception("We can not get the cities referenced by name of country : " + country + " and state : " + state);

        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
}
