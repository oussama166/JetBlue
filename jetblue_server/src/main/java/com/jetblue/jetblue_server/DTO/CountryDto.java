package com.jetblue.jetblue_server.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor // Adds a no-argument constructor
@AllArgsConstructor // Adds an all-argument constructor
public class CountryDto {
    private Integer id;
    private String name;
    private String iso3;
}
