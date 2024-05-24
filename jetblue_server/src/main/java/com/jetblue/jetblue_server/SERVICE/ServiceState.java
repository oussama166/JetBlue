package com.jetblue.jetblue_server.SERVICE;

import com.jetblue.jetblue_server.DOA.Repository.RepositoryState;
import com.jetblue.jetblue_server.SERVICE.Manager.ManagerState;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ServiceState implements ManagerState {

    // Inject manger State
    private final RepositoryState repositoryState;

    public ServiceState(RepositoryState repositoryState) {
        this.repositoryState = repositoryState;
    }

    //Logger
    private static final Logger logger = LoggerFactory.getLogger(ServiceState.class);

    @Override
    public Optional<List<String>> getStateByName(String name) {
        try {
            Optional<List<String>> stateList = repositoryState.findByName(name);
            if (stateList.isPresent()) {
                return stateList;
            }
            throw new Exception("State can not be find state by name !!!");
        } catch (Exception e) {
            logger.warn(e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<List<String>> getStateByNameCountry(String countryName) {
        try {
            Optional<List<String>> stateList = repositoryState.findByCountry_Name(countryName);
            if (stateList.isPresent()) {
                return stateList;
            }
            throw new Exception("State can not be find state by name country !!!");
        } catch (Exception e) {
            logger.warn(e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<List<String>> getStateByIsoCountry(String isoCountry) {
        try {
            Optional<List<String>> isCountry = repositoryState.findByCountry_Iso3(isoCountry);
            if (isCountry.isPresent()) {
                return isCountry;
            }
            throw new Exception("State can not be find state by Iso3 !!!");
        } catch (Exception e) {
            logger.warn(e.getMessage());
            return Optional.empty();
        }
    }
}
