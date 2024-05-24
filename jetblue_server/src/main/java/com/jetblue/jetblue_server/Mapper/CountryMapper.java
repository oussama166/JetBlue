package com.jetblue.jetblue_server.Mapper;

import com.jetblue.jetblue_server.DOA.Modules.Country;
import com.jetblue.jetblue_server.DOA.Modules.User;
import com.jetblue.jetblue_server.DTO.CountryDto;
import com.jetblue.jetblue_server.DTO.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CountryMapper {
    ModelMapper mm = new ModelMapper();

    public CountryDto toCountryDto(Country country) {
        return mm.map(country, CountryDto.class);
    }

    public Country toCountry(CountryDto countryDto) {
        return mm.map(countryDto, Country.class);
    }
}
