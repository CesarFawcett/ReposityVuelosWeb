package aeroline.nr.api.services.imp;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import aeroline.nr.api.entities.Flight;
import aeroline.nr.api.repositories.FlightRepository;
import aeroline.nr.api.services.FlightService;

@Service
public class FlightServiceImpl implements FlightService {

    @Autowired
    private FlightRepository flightRepository;

    @Override
    public List<Flight> findAll() {
        return flightRepository.findAll();
    }

    @Override
    public Flight findById(int id) {
        return flightRepository.findById(id).orElse(null);
    }

    @Override
    public Flight save(Flight flight) {
        return flightRepository.save(flight);
    }

    @Override
    public void delete(int id) {
        flightRepository.deleteById(id);
    }

}
