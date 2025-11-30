package aeroline.nr.api.services;

import java.util.List;
import java.util.Optional;
import aeroline.nr.api.api.Dto.FlightRequestDto;
import aeroline.nr.api.api.Dto.FlightResponseDto;
import aeroline.nr.api.api.Dto.FlightSearchDto;

public interface FlightService {
        List<FlightResponseDto> getAllFlights();

        List<FlightResponseDto> getAvailableFlights();

        List<FlightResponseDto> searchFlights(FlightSearchDto searchDto);

        Optional<FlightResponseDto> getFlightById(int id);

        FlightResponseDto createFlight(FlightRequestDto flightDto);

        FlightResponseDto updateFlight(int id, FlightRequestDto flightDto);

        void deleteFlight(int id);
}

// List<Flight> findByDepartureCity(String city);