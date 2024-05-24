package com.jetblue.jetblue_server.SERVICE;

import com.jetblue.jetblue_server.DOA.Modules.Flight;
import com.jetblue.jetblue_server.DOA.Repository.RepositoryFligth;
import com.jetblue.jetblue_server.SERVICE.Manager.ManagerFlight;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceFlight implements ManagerFlight {

    private final RepositoryFligth repositoryFligth;


    public ServiceFlight(RepositoryFligth repositoryFligth) {
        this.repositoryFligth = repositoryFligth;
    }

    @Override
    public Optional<Flight> createFlight(Flight flight) {
        try {
            Flight flight1 = new Flight(
                    flight.getFlightId(),
                    flight.getDepartureTime(),
                    flight.getArrivalTime(),
                    flight.getOrigin(),
                    flight.getDestination(),
                    flight.getPlane(),
                    flight.getArrivalTerminal(),
                    flight.getDepartureTerminal(),
                    flight.getNumberPlace()
            );
            this.repositoryFligth.save(flight1);
            return Optional.of(flight1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return Optional.empty();

        }
    }

    @Override
    public List<Flight> createFlights(List<Flight> listFlight) {
        try {
            repositoryFligth.saveAll(listFlight);
            return listFlight;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public int availableSeats(Flight flight) {
        try {
            Optional<Flight> flightOptional = repositoryFligth.findById((long) flight.getFlightId());
            if (flightOptional.isPresent()) {
                return flightOptional.get().getNumberPlace();
            }
            throw new IllegalArgumentException("Flight doesn't exist !!!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }

    @Override
    public List<Flight> getFlightSearch(
            String DepartureTime,
            String ArrivalTime,
            String Origin,
            String Destination
    ) {
        if (DepartureTime == null & ArrivalTime == null & Origin == null & Destination == null) {
            throw new IllegalArgumentException("You must pass at least one param valid to make the search");
        }
        // Define the custom date-time formatter
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS");

        try {
            // Parse the date-time strings using the custom formatter
            LocalDateTime depar = LocalDateTime.parse(DepartureTime, formatter);
            LocalDateTime ariv = LocalDateTime.parse(ArrivalTime, formatter);
            Optional<List<Flight>> flightSearchResult = repositoryFligth.findByDepartureTimeOrArrivalTimeOrOriginOrDestination(
                    depar,
                    ariv,
                    Origin,
                    Destination
            );
            if (flightSearchResult.isPresent()) {
                return flightSearchResult.get();
            }
            throw new Exception("No flight in this time to your destination ...");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }

    }

    @Override
    public List<Flight> getFlights() {
        return repositoryFligth.findAll();
    }

    @Override
    public Flight updateFlight(Flight flight) {
        try {
            Optional<Flight> TestFlight = repositoryFligth.findById((long) flight.getFlightId());
            if (TestFlight.isEmpty()) {
                throw new Exception("Flight not found to update");
            }
            TestFlight.get().setDepartureTime(flight.getDepartureTime());
            TestFlight.get().setDepartureTerminal(flight.getDepartureTerminal());
            TestFlight.get().setArrivalTime(flight.getArrivalTime());
            TestFlight.get().setArrivalTerminal(flight.getArrivalTerminal());
            TestFlight.get().setOrigin(flight.getOrigin());
            TestFlight.get().setDestination(flight.getDestination());
            TestFlight.get().setPlane(flight.getPlane());
            TestFlight.get().setNumberPlace(flight.getNumberPlace());
            repositoryFligth.save(TestFlight.get());
            return TestFlight.get();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return flight;
        }
    }

    @Override
    public Flight removeFlight(long flightId) {
        if (flightId < 0) {
            throw new IllegalArgumentException("Flight Id should be grate than 0");
        }
        try {
            Optional<Flight> flightExist = repositoryFligth.findById(flightId);
            if (flightExist.isEmpty()) {
                throw new Exception("Flight doesn't exist at this id : " + flightId);
            }
            repositoryFligth.delete(flightExist.get());
            return flightExist.get();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Optional<Flight> updateSeat(Flight flight, int seat) {
        try {
            Optional<Flight> byId = repositoryFligth.findById((long) flight.getFlightId());
            if (byId.isPresent()) {
                int availableSeats = this.availableSeats(flight);
                if (availableSeats <= 0) {
                    throw new Exception("No available seats in this time to your destination ...");
                }
                int seatUpdated = flight.getNumberPlace() - seat;
                if (seatUpdated < 0) {
                    seatUpdated = 0;
                }
                byId.get().setNumberPlace(seatUpdated);
                repositoryFligth.save(byId.get());
                return byId;
            }
            throw new Exception("No seat found at this id : " + flight.getFlightId());
        } catch (Exception e) {
            return Optional.empty();
        }

    }
}
