package aeroline.nr.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import aeroline.nr.api.entities.Flight;

public interface FlightRepository extends JpaRepository<Flight, Integer> {

}
