package aeroline.nr.api.services;

import java.util.List;
import java.util.Optional;

import aeroline.nr.api.api.Dto.FlightDto;
import aeroline.nr.api.entities.Flight;

public interface FlightService {
    void delete(Integer id);
    Optional<Flight> fainId(Integer id);
    Optional<Flight> updateFlight(Integer id, Flight flightToUpdate);
    Flight createFlight(Flight newFlight);
    List<FlightDto> findFlightsByDepartureDate(String departureDate);
    List<FlightDto> findFlightsByDepartureDateAndAirports(String departureDate, String departureAirportCode,String arrivalAirportCode);
    List<FlightDto> findFlightsByDepartureDateAndDepartureAirport(String departureDate, String departureAirportCode);
    
}
