package aeroline.nr.api.services.imp;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import aeroline.nr.api.api.Dto.FlightMapper;
import aeroline.nr.api.api.Dto.FlightRequestDto;
import aeroline.nr.api.api.Dto.FlightResponseDto;
import aeroline.nr.api.api.Dto.FlightSearchDto;
import aeroline.nr.api.entities.Flight;
import aeroline.nr.api.repositories.FlightRepository;
import aeroline.nr.api.services.FlightService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FlightServiceImpl implements FlightService {

    private final FlightRepository flightRepository;
    private final FlightMapper flightMapper;

    public List<FlightResponseDto> getAllFlights() {
        return flightRepository.findAll()
                .stream()
                .map(flightMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<FlightResponseDto> getAvailableFlights() {
        return flightRepository.findBySeatCapacityGreaterThan(0)
                .stream()
                .map(flightMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<FlightResponseDto> searchFlights(FlightSearchDto searchDto) {
        List<Flight> flights;

        if (searchDto.getDepartureCity() != null && searchDto.getArrivalCity() != null) {
            flights = flightRepository.findByDepartureCityAndArrivalCity(
                    searchDto.getDepartureCity(),
                    searchDto.getArrivalCity());
        } else {
            flights = flightRepository.findBySeatCapacityGreaterThan(0);
        }

        // Filtrar por precio máximo si se especifica
        if (searchDto.getMaxPrice() != null) {
            flights = flights.stream()
                    .filter(flight -> flight.getTicketPrice() <= searchDto.getMaxPrice())
                    .collect(Collectors.toList());
        }

        return flights.stream()
                .map(flightMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<FlightResponseDto> getFlightById(int id) {
        return flightRepository.findById(id)
                .map(flightMapper::toDto);
    }

    @Override
    public FlightResponseDto createFlight(FlightRequestDto flightDto) {
        Flight flight = flightMapper.toEntity(flightDto);
        Flight savedFlight = flightRepository.save(flight);
        return flightMapper.toDto(savedFlight);
    }

    @Override
    public FlightResponseDto updateFlight(int id, FlightRequestDto flightDto) {
        Flight existingFlight = flightRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Flight not found with id: " + id));

        flightMapper.updateEntityFromDto(flightDto, existingFlight);
        Flight updatedFlight = flightRepository.save(existingFlight);
        return flightMapper.toDto(updatedFlight);
    }

    @Override
    public void deleteFlight(int id) {
        flightRepository.deleteById(id);
    }
}
