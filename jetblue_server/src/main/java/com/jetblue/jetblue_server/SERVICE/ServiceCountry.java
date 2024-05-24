package com.jetblue.jetblue_server.SERVICE;

import com.jetblue.jetblue_server.DOA.Modules.Country;
import com.jetblue.jetblue_server.DOA.Repository.RepositoryCountry;
import com.jetblue.jetblue_server.SERVICE.Manager.ManagerCountry;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceCountry implements ManagerCountry {

    private final RepositoryCountry repositoryCountry;

    public ServiceCountry(RepositoryCountry repositoryCountry) {
        this.repositoryCountry = repositoryCountry;
    }

    @Override
    public Optional<List<String>> getAllCountry() {
        try {
            List<String> countries = new ArrayList<>(this.repositoryCountry.findAllBy());
            if (countries.isEmpty()) {
                throw new Exception("No countries fetch up");
            }
            return Optional.of(countries);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<List<Country>> getCountryByName(String countryName) {
        try {
            List<Country> countries = new ArrayList<>(this.repositoryCountry.findByName(countryName));
            if (countries.isEmpty()) {
                throw new Exception("No countrie define by this name : " + countryName);
            }
            return Optional.of(countries);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Country> getCountryByIsoCode(String iso3) {
        try {
            Country country = this.repositoryCountry.findByIso3(iso3);
            if (country == null) {
                throw new Exception("No countrir define by this iso3 : " + iso3);
            }
            return Optional.of(country);
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
