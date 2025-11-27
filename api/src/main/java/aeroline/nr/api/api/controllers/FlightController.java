package aeroline.nr.api.api.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import aeroline.nr.api.api.Dto.FlightRequestDto;
import aeroline.nr.api.api.Dto.FlightResponseDto;
import aeroline.nr.api.api.Dto.FlightSearchDto;
import aeroline.nr.api.services.FlightService;

@RestController
@RequestMapping("/catalog")
@CrossOrigin(origins = "*")
public class FlightController {

    @Autowired
    private FlightService flightService;

    @GetMapping
    public ResponseEntity<List<FlightResponseDto>> getAllFlights() {
        List<FlightResponseDto> flights = flightService.getAllFlights();
        return ResponseEntity.ok(flights);
    }

    @GetMapping("/available")
    public ResponseEntity<List<FlightResponseDto>> getAvailableFlights() {
        List<FlightResponseDto> flights = flightService.getAvailableFlights();
        return ResponseEntity.ok(flights);
    }

    @GetMapping("/search")
    public ResponseEntity<List<FlightResponseDto>> searchFlights(
            @RequestParam(required = false) String departureCity,
            @RequestParam(required = false) String arrivalCity,
            @RequestParam(required = false) Integer maxPrice) {

        FlightSearchDto searchDto = new FlightSearchDto();
        searchDto.setDepartureCity(departureCity);
        searchDto.setArrivalCity(arrivalCity);
        searchDto.setMaxPrice(maxPrice);

        List<FlightResponseDto> flights = flightService.searchFlights(searchDto);
        return ResponseEntity.ok(flights);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FlightResponseDto> getFlightById(@PathVariable int id) {
        return flightService.getFlightById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<FlightResponseDto> createFlight(@RequestBody FlightRequestDto flightDto) {
        FlightResponseDto createdFlight = flightService.createFlight(flightDto);
        return ResponseEntity.ok(createdFlight);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FlightResponseDto> updateFlight(
            @PathVariable int id,
            @RequestBody FlightRequestDto flightDto) {
        try {
            FlightResponseDto updatedFlight = flightService.updateFlight(id, flightDto);
            return ResponseEntity.ok(updatedFlight);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFlight(@PathVariable int id) {
        flightService.deleteFlight(id);
        return ResponseEntity.ok().build();
    }
}
