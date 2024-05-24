package com.jetblue.jetblue_server.SERVICE.Manager;

import com.jetblue.jetblue_server.DOA.Modules.Airports;

import java.util.List;
import java.util.Optional;

public interface ManagerAiports {
    Optional<List<Airports>> getAirports();

    Optional<Airports> getAirports(Airports airports);

    Optional<List<Airports>> getAirports(String airports);

    Optional<List<Airports>> getAirportsInCity(String City);

    Optional<List<Airports>> getAirportsInCountry(String Country);
}
