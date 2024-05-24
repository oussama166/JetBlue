package com.jetblue.jetblue_server.SERVICE.Manager;

import com.jetblue.jetblue_server.DOA.Modules.State;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ManagerState {

    Optional<List<String>> getStateByName(
            String name
    );
    Optional<List<String>> getStateByNameCountry(
            String countryName
    );
    Optional<List<String>> getStateByIsoCountry(
            String isoCountry
    );

}
