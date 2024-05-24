package com.jetblue.jetblue_server.WEB;

import com.jetblue.jetblue_server.DOA.Modules.State;
import com.jetblue.jetblue_server.SERVICE.Manager.ManagerState;
import com.jetblue.jetblue_server.SERVICE.ServiceCity;
import com.jetblue.jetblue_server.SERVICE.ServiceState;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.dialect.SybaseASEDialect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/api/v2")
@CrossOrigin(value = "http://localhost:4401/")
public class ControllerState {

    // Inject the state service
    public final ManagerState managerState;

    public ControllerState(ManagerState managerState) {
        this.managerState = managerState;
    }
    // logger
    public static final Logger logger = LoggerFactory.getLogger(ServiceState.class);

    @GetMapping(
            path = "/getStateByName/{name}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> getStateByName(
            @PathVariable("name") String name
    ) {
        try {
            Optional<List<String>> states = managerState.getStateByName(name);
            if (states.isPresent()) {
                return ResponseEntity.ok(states.get());
            } else {
                return ResponseEntity.notFound().build(); // State not found
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error occurred");
        }
    }


    @GetMapping(
            path = "/getStateByCountry/{country}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> getStateByCountry(
            @PathVariable("country") String country
    ){
        try {
            Optional<List<String>> states = managerState.getStateByNameCountry(country);
            if (states.isPresent()) {
                return ResponseEntity.ok(states.get());
            }
            throw new Exception("This country hasn't any states");
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @GetMapping(
            path = "/getStateByIso/{iso}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> getStateByIso(
            @PathVariable("iso") String iso
    ){
        try {
            Optional<List<String>> states = managerState.getStateByIsoCountry(iso);
            if (states.isPresent()) {
                return ResponseEntity.ok(states.get());
            }
            throw new Exception("This iso hasn't any states");
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }


}
