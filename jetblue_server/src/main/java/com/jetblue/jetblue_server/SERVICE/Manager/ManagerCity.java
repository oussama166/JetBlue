package com.jetblue.jetblue_server.SERVICE.Manager;

import com.jetblue.jetblue_server.DOA.Modules.City;
import com.jetblue.jetblue_server.DOA.Modules.Country;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface ManagerCity {
    Optional<List<String>> getAllCities();
    Optional<List<City>> getCitiesByContryCode(String countryCode);
    Optional<List<City>> getCitieByName(String cityName);

    Optional<List<String>> getCitiesReferenceByCountry(String CountryName);

    Optional<List<String>> getCitiesReferenceByState(String state,String country);
}
