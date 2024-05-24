package com.jetblue.jetblue_server.SERVICE.Manager;

import com.jetblue.jetblue_server.DOA.Modules.Country;

import java.util.List;
import java.util.Optional;

public interface ManagerCountry {
    Optional<List<String>> getAllCountry();
    Optional<List<Country>> getCountryByName(String countryName);
    Optional<Country> getCountryByIsoCode(String iso3);
}
