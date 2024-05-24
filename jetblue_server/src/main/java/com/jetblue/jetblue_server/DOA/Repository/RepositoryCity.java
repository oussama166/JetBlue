package com.jetblue.jetblue_server.DOA.Repository;

import com.jetblue.jetblue_server.DOA.Modules.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RepositoryCity extends JpaRepository<City, Long> {
    @Query(
            value = "Select ci from City ci WHERE ci.name LIKE ?1%"
    )
    Optional<List<City>> findByName(String nameCity);

    Optional<List<City>> findByCountryCode(String countryCode);

    @Query(
            value = "SELECT ci FROM City ci  INNER JOIN Country co ON ci.country.id = co.id WHERE co.name = ?1"
    )
    Optional<List<City>> findByCountry(String couString);

    @Query(
            value = "SELECT ci.name FROM City ci WHERE ci.country.name = ?2 AND ci.state.name = ?1"
    )
    Optional<List<String>> findByState_NameAndCountry_NameOrderByNameAsc(String nameState, String nameCountry);
}
