package com.jetblue.jetblue_server.DOA.Repository;

import com.jetblue.jetblue_server.DOA.Modules.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RepositoryCountry extends JpaRepository<Country, Long> {

    @Query(
            value = "SELECT ct.name FROM Country ct"
    )
    List<String> findAllBy();

    @Query(
            value = "SELECT ct.name FROM Country ct WHERE ct.name = ?1"
    )
    List<Country> findByName(String name);

    @Query(
            value = "SELECT ct.name FROM Country ct WHERE ct.iso3 = ?1"
    )
    Country findByIso3(String iso3);
}
