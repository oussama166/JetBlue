package com.jetblue.jetblue_server.WEB;

import com.jetblue.jetblue_server.Constums.AppException;
import com.jetblue.jetblue_server.DOA.Modules.User;
import com.jetblue.jetblue_server.DTOS.CredentialDto;
import com.jetblue.jetblue_server.DTO.UserDto;
import com.jetblue.jetblue_server.SERVICE.Manager.ManagerUser;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1")
@CrossOrigin(value = "http://localhost:4401/")
public class ControllerUser {

    private ManagerUser ManaUser;
    private static final Logger logger = LoggerFactory.getLogger(ControllerUser.class);


    public ControllerUser(ManagerUser manaUser) {
        ManaUser = manaUser;
    }


    @PostMapping(
            value = "/login",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<UserDto> login(@RequestBody CredentialDto credentialDto) {
        try {
            UserDto user = ManaUser.login(credentialDto);
            return ResponseEntity.ok(user);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping(
            value = "/emailExist/{email}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> emailExist(@PathVariable("email") String email) {
        try {
            boolean emailExist = ManaUser.EmailExist(email);
            logger.info("{}", emailExist);
            if (!emailExist) {
                return ResponseEntity.ok("Email not exist");
            }
            throw new AppException("Email exist", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping(
            path = "/newUser",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<?> setUser(@RequestBody User user) {
        try {
            UserDto setUser = ManaUser.setUser(user);
            logger.warn(setUser.toString());
            return ResponseEntity.ok(setUser);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    //    TODO : I just set up the Post controller to get all the data from the db
    //    we need to get data without password and critic information
    @GetMapping(
            path = "/Users",
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<List<User>> getUsers() {
        List<User> userList = null;
        try {
            userList = ManaUser.Users();
            return ResponseEntity.ok(userList);

        } catch (Exception e) {
            return ResponseEntity.status(404).body(userList);
        }
    }

    // Set List of users
    @PostMapping(
            path = "/setUsers",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<Long>> setUsers(@RequestBody List<User> users) {
        List<Long> usersResponse = null;
        try {
            usersResponse = ManaUser.setUsers(users);
            return ResponseEntity.status(200).body(usersResponse);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(usersResponse);
        }


    }

    @GetMapping(
            path = "/getUser",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<User> getUser(@RequestParam int id) {
        User getUser = new User();
        try {
            getUser = ManaUser.getUser((long) id);
            return ResponseEntity.status(200).body(getUser);
        } catch (Exception e) {
            return ResponseEntity.status(404).body(null);
        }
    }

    @PostMapping(
            path = "/userExistByEmail/{email}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Boolean> userExistByEmail(
            @PathVariable("email") String email
    ) {
        boolean existUser = ManaUser.userExsistByEmail(email);
        if (existUser) {
            return ResponseEntity.status(200).body(true);
        }
        return ResponseEntity.status(400).body(false);

    }
}

