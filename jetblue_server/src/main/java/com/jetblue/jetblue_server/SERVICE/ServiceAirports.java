package com.jetblue.jetblue_server.SERVICE;

import com.jetblue.jetblue_server.DOA.Modules.Airports;
import com.jetblue.jetblue_server.DOA.Repository.RepositoryAirports;
import com.jetblue.jetblue_server.SERVICE.Manager.ManagerAiports;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ServiceAirports implements ManagerAiports {

    private RepositoryAirports repositoryAirports;


    //    Get all the airports
    @Override
    public Optional<List<Airports>> getAirports() {
        return Optional.of(this.repositoryAirports.findAll());
    }

    //    Get Airports by airports credential data
    @Override
    public Optional<Airports> getAirports(Airports airports) {
        try {
            Optional<Airports> airport = repositoryAirports.findByNameAndIso_countryAndMunicipality(
                    airports.getName(),
                    airports.getIso_country(),
                    airports.getMunicipality()
            );
            if (airport.isPresent()) {
                return airport;
            }
            throw new RuntimeException("Airport doesn't exist ...");
        } catch (Exception e) {
            return Optional.empty();
        }

    }

    @Override
    public Optional<List<Airports>> getAirports(String airports) {
        try {
            List<Airports> byName = repositoryAirports.findByName(airports);
            if (!byName.isEmpty()) {
                return Optional.of(byName);
            }
            throw new RuntimeException("Airport doesn't exist ...");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<List<Airports>> getAirportsInCity(String City) {
        try {
            List<Airports> byMunicipality = repositoryAirports.findByMunicipality(City);
            if (!byMunicipality.isEmpty()) {
                return Optional.of(byMunicipality);
            }
            throw new RuntimeException("Municipality doesn't exist ...");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return Optional.empty();
        }

    }

    @Override
    public Optional<List<Airports>> getAirportsInCountry(String Country) {
        try {
            List<Airports> byIsoCountry = repositoryAirports.findByIso_country(Country);
            if (!byIsoCountry.isEmpty()) {
                return Optional.of(byIsoCountry);
            }
            throw new RuntimeException("Iso Country doesn't exist ...");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return Optional.empty();
        }
    }
}
