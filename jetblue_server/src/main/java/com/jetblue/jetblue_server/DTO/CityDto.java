package com.jetblue.jetblue_server.DTO;

import com.jetblue.jetblue_server.DOA.Modules.Country;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor // Adds a no-argument constructor
@AllArgsConstructor // Adds an all-argument constructor
public class CityDto {
    private String name;
    private CountryDto Country;
}
