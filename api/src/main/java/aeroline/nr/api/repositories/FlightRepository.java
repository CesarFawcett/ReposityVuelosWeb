package aeroline.nr.api.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import aeroline.nr.api.entities.Flight;

public interface FlightRepository extends JpaRepository<Flight, Integer> {
    List<Flight> findByDepartureCityAndArrivalCity(String departureCity, String arrivalCity);

    List<Flight> findBySeatCapacityGreaterThan(int minCapacity);

    List<Flight> findByFlightNumber(int flightNumber);
}
