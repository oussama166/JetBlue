package com.jetblue.jetblue_server.DOA.Repository;

import com.jetblue.jetblue_server.DOA.Modules.State;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RepositoryState extends JpaRepository<State, Long> {
    @Query(
            value = "SELECT st.name FROM State st WHERE st.country.name = ?1"
    )
    Optional<List<String>> findByCountry_Name(String name);

    @Query(
            value = "SELECT st.name FROM State st WHERE st.name = ?1 "
    )
    Optional<List<String>> findByName(String name);
    @Query(
            value = "SELECT st.name FROM State st WHERE st.country.iso3 = ?1 OR st.country.iso2 = ?1"
    )
    Optional<List<String>> findByCountry_Iso3(String iso3);

}
