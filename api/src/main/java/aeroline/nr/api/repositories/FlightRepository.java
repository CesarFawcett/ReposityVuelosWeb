package aeroline.nr.api.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import aeroline.nr.api.api.Dto.FlightDto;
import aeroline.nr.api.entities.Flight;

public interface FlightRepository extends JpaRepository<Flight, Integer> {
    List<Flight> findByDepartureDate(String departureDate);
    List<FlightDto> findByDepartureDateAndDepartureAirportCodeAndArrivalAirportCode(String departureDate,String departureAirportCode, String arrivalAirportCode);
    List<FlightDto> findByDepartureDateAndDepartureAirportCode(String departureDate, String departureAirportCode);
}

