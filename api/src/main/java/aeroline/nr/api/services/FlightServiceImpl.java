package aeroline.nr.api.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aeroline.nr.api.api.Dto.FlightDto;
import aeroline.nr.api.api.Dto.FlightMapper;
import aeroline.nr.api.entities.Flight;
import aeroline.nr.api.repositories.FlightRepository;

@Service
public class FlightServiceImpl implements FlightService {

    @Autowired
    private FlightRepository flightRepository;
    @Override
    public void delete(Integer id) {
        flightRepository.deleteById(id);
    }
    @Override
    public Optional<Flight> fainId(Integer id) {
        return flightRepository.findById(id);
    }
    @Override
    public Optional<Flight> updateFlight(Integer id, Flight flightToUpdate) {
        return flightRepository.findById(id).map(existingFlight -> {
            existingFlight.setDepartureDate(flightToUpdate.getDepartureDate());
            existingFlight.setDepartureAirportCode(flightToUpdate.getDepartureAirportCode());
            existingFlight.setDepartureCity(flightToUpdate.getDepartureCity());
            existingFlight.setArrivalDate(flightToUpdate.getArrivalDate());
            existingFlight.setArrivalAirportCode(flightToUpdate.getArrivalAirportCode());
            existingFlight.setArrivalCity(flightToUpdate.getArrivalCity());
            existingFlight.setTicketPrice(flightToUpdate.getTicketPrice());
            existingFlight.setTicketCurrency(flightToUpdate.getTicketCurrency());
            existingFlight.setFlightNumber(flightToUpdate.getFlightNumber());
            existingFlight.setSeatCapacity(flightToUpdate.getSeatCapacity()); 
            
            return flightRepository.save(existingFlight);
        });
    }   
    @Override
    public Flight createFlight(Flight newFlight) {
        return flightRepository.save(newFlight);
    }
    public List<FlightDto> findFlightsByDepartureDate(String departureDate) {
        List<Flight> flights = flightRepository.findByDepartureDate(departureDate);
        return FlightMapper.toDtoList(flights);
    }
    @Override
    public List<FlightDto> findFlightsByDepartureDateAndAirports(String departureDate, String departureAirportCode,
            String arrivalAirportCode) {
        return flightRepository.findByDepartureDateAndDepartureAirportCodeAndArrivalAirportCode(departureDate, departureAirportCode, arrivalAirportCode);
            }
    @Override
    public List<FlightDto> findFlightsByDepartureDateAndDepartureAirport(String departureDate,
            String departureAirportCode) {
        return flightRepository.findByDepartureDateAndDepartureAirportCode(departureDate, departureAirportCode);
    }
}
