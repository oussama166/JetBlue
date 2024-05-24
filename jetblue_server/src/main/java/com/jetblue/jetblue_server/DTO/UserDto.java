package com.jetblue.jetblue_server.DTO;

import com.jetblue.jetblue_server.DOA.Modules.User;
import jakarta.persistence.Access;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link User}
 */
@Data
@NoArgsConstructor // Adds a no-argument constructor
@AllArgsConstructor // Adds an all-argument constructor
public class UserDto {
    private int UserId;
    private String Name;
    private String LastName;
    private String Email;
    private String PhoneNumber;
    private String Address;
    private String City;
    private String State;
    private String Country;
    private com.jetblue.jetblue_server.DOA.Modules.ENUMS.Gender Gender;
    private LocalDate BirthDay;
    private String Token;
 }