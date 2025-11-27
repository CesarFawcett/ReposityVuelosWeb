package aeroline.nr.api.services;

import java.util.List;
import aeroline.nr.api.entities.Flight;

public interface FlightService {
        List<Flight> findAll();

        Flight findById(int id);

        Flight save(Flight flight);

        void delete(int id);
}

// List<Flight> findByDepartureCity(String city);