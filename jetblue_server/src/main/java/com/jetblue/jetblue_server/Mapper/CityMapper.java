package com.jetblue.jetblue_server.Mapper;

import com.jetblue.jetblue_server.DOA.Modules.City;
import com.jetblue.jetblue_server.DTO.CityDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CityMapper {
    ModelMapper mm = new ModelMapper();

    public CityDto toCityDto(CityDto city) {
        return mm.map(city, CityDto.class);
    }

    public City toCity(CityDto cityDto) {
        return mm.map(cityDto, City.class);
    }
}
