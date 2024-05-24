package com.jetblue.jetblue_server.DOA.Repository;

import com.jetblue.jetblue_server.DOA.Modules.Airports;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RepositoryAirports extends JpaRepository<Airports, Integer> {
    @Query(
            value = "SELECT air FROM Airports air WHERE air.name=?1 AND air.iso_country = ?2 AND air.municipality = ?3"
    )
    Optional<Airports> findByNameAndIso_countryAndMunicipality(String name, String iso_country, String municipality);

    @Query(
            value = "SELECT air FROM Airports air WHERE air.name LIKE %?1%"
    )
    List<Airports> findByName(String name);

    @Query(
            value = "SELECT air FROM Airports air WHERE air.municipality LIKE %?1% AND air.type = 'large_airport' OR air.type = 'small_airport'"
    )
    List<Airports> findByMunicipality(String municipality);

    @Query(
            value = "SELECT air FROM Airports air WHERE air.iso_country = ?1 AND (air.type LIKE 'large_airport' OR air.type LIKE 'small_airport')"
    )
    List<Airports> findByIso_country(String iso_country);
}
