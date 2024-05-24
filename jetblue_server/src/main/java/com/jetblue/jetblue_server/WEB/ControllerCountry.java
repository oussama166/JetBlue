package com.jetblue.jetblue_server.WEB;

import com.jetblue.jetblue_server.DOA.Modules.Country;
import com.jetblue.jetblue_server.SERVICE.Manager.ManagerCountry;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/v2")
@CrossOrigin(value = "http://localhost:4401/")
public class ControllerCountry {
    public final ManagerCountry managerCountry;

    public ControllerCountry(ManagerCountry managerCountry) {
        this.managerCountry = managerCountry;
    }

    @GetMapping(
            path = "/getAllCities",
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<?> getAllCities(
    ) {
        try {
            Optional<List<String>> countries= this.managerCountry.getAllCountry();
            if(countries.isPresent()){
                return ResponseEntity.status(200).body(countries.get());
            }
            throw new Exception("Geting country had some issues");
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
}
