package com.jetblue.jetblue_server.SERVICE;

import com.jetblue.jetblue_server.DOA.Modules.City;
import com.jetblue.jetblue_server.DOA.Repository.RepositoryCity;
import com.jetblue.jetblue_server.SERVICE.Manager.ManagerCity;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ServiceCity implements ManagerCity {
    //    Inject the Repository
    private final RepositoryCity repositoryCity;

    public ServiceCity(RepositoryCity repositoryCity) {
        this.repositoryCity = repositoryCity;
    }

    // logger
    private static final Logger logger = LoggerFactory.getLogger(ServiceCity.class);

    @Override
    public Optional<List<String>> getAllCities() {
        try {
            List<City> data = repositoryCity.findAll();
            List<String> dataOutput = new ArrayList<>();
            if (!data.isEmpty()) {
                for (City ct : data) {
                    dataOutput.add(ct.getName() + "," + ct.getCountryCode());
                }
                return Optional.of(dataOutput);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public Optional<List<City>> getCitiesByContryCode(String countryCode) {
        try {
            Optional<List<City>> byCountryAndCountryCode = repositoryCity.findByCountryCode(countryCode);
            if (byCountryAndCountryCode.isPresent()) {
                return byCountryAndCountryCode;
            }
            throw new Exception("Country code doesn't exist");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<List<City>> getCitieByName(String cityName) {
        try {
            Optional<List<City>> byName = repositoryCity.findByName(cityName);
            if (byName.isPresent()) {
                return byName;
            }
            throw new Exception("City doesn't exist");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return Optional.empty();

        }
    }

    @Override
    public Optional<List<String>> getCitiesReferenceByCountry(String CountryName) {
        List<String> nameCities = new ArrayList<>();
        try {
            Optional<List<City>> cities = repositoryCity.findByCountry(CountryName);
            if (cities.isPresent()) {
                nameCities = cities.get().stream().map(City::getName).toList();
                return Optional.of(nameCities);
            }
            throw new Exception("Country has any Cities or the country undifined");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<List<String>> getCitiesReferenceByState(String state,String country) {
        try {
            Optional<List<String>> cities = repositoryCity.findByState_NameAndCountry_NameOrderByNameAsc(state , country);
            if (cities.isPresent()) {
                return cities;
            }
            throw new Exception("we can not fin the cities reference to state or country !!!");
        } catch (Exception e) {
            logger.warn(e.getMessage());
            return Optional.empty();
        }
    }
}
