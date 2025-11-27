package aeroline.nr.api.api.Dto;

import org.springframework.stereotype.Component;
import aeroline.nr.api.entities.Flight;

@Component
public class FlightMapper {
    public FlightResponseDto toDto(Flight flight) {
        if (flight == null) {
            return null;
        }

        FlightResponseDto dto = new FlightResponseDto();
        dto.setId(flight.getId());
        dto.setDepartureDate(flight.getDepartureDate());
        dto.setDepartureAirportCode(flight.getDepartureAirportCode());
        dto.setDepartureAirportName(flight.getDepartureAirportName());
        dto.setDepartureCity(flight.getDepartureCity());
        dto.setDepartureLocale(flight.getDepartureLocale());
        dto.setArrivalDate(flight.getArrivalDate());
        dto.setArrivalAirportCode(flight.getArrivalAirportCode());
        dto.setArrivalAirportName(flight.getArrivalAirportName());
        dto.setArrivalCity(flight.getArrivalCity());
        dto.setArrivalLocale(flight.getArrivalLocale());
        dto.setTicketPrice(flight.getTicketPrice());
        dto.setTicketCurrency(flight.getTicketCurrency());
        dto.setFlightNumber(flight.getFlightNumber());
        dto.setSeatCapacity(flight.getSeatCapacity());

        return dto;
    }

    public Flight toEntity(FlightRequestDto dto) {
        if (dto == null) {
            return null;
        }

        Flight flight = new Flight();
        flight.setDepartureDate(dto.getDepartureDate());
        flight.setDepartureAirportCode(dto.getDepartureAirportCode());
        flight.setDepartureAirportName(dto.getDepartureAirportName());
        flight.setDepartureCity(dto.getDepartureCity());
        flight.setDepartureLocale(dto.getDepartureLocale());
        flight.setArrivalDate(dto.getArrivalDate());
        flight.setArrivalAirportCode(dto.getArrivalAirportCode());
        flight.setArrivalAirportName(dto.getArrivalAirportName());
        flight.setArrivalCity(dto.getArrivalCity());
        flight.setArrivalLocale(dto.getArrivalLocale());
        flight.setTicketPrice(dto.getTicketPrice());
        flight.setTicketCurrency(dto.getTicketCurrency());
        flight.setFlightNumber(dto.getFlightNumber());
        flight.setSeatCapacity(dto.getSeatCapacity());

        return flight;
    }

    public void updateEntityFromDto(FlightRequestDto dto, Flight flight) {
        if (dto == null || flight == null) {
            return;
        }

        flight.setDepartureDate(dto.getDepartureDate());
        flight.setDepartureAirportCode(dto.getDepartureAirportCode());
        flight.setDepartureAirportName(dto.getDepartureAirportName());
        flight.setDepartureCity(dto.getDepartureCity());
        flight.setDepartureLocale(dto.getDepartureLocale());
        flight.setArrivalDate(dto.getArrivalDate());
        flight.setArrivalAirportCode(dto.getArrivalAirportCode());
        flight.setArrivalAirportName(dto.getArrivalAirportName());
        flight.setArrivalCity(dto.getArrivalCity());
        flight.setArrivalLocale(dto.getArrivalLocale());
        flight.setTicketPrice(dto.getTicketPrice());
        flight.setTicketCurrency(dto.getTicketCurrency());
        flight.setFlightNumber(dto.getFlightNumber());
        flight.setSeatCapacity(dto.getSeatCapacity());
    }
}