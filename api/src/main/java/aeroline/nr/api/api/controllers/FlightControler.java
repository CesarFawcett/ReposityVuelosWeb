package aeroline.nr.api.api.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import aeroline.nr.api.api.Dto.FlightCreateDto;
import aeroline.nr.api.api.Dto.FlightDto;
import aeroline.nr.api.api.Dto.FlightMapper;
import aeroline.nr.api.entities.Flight;
import aeroline.nr.api.exceptions.DuplicateCodigoException;
import aeroline.nr.api.services.FlightService;

@RestController
@RequestMapping("/catalog")
public class FlightControler {
    @Autowired
    private final FlightService flightService;
    @Autowired
    private final FlightMapper flightMapper;
    
    public FlightControler(FlightService flightService, FlightMapper flightMapper) {
        this.flightService = flightService;
        this.flightMapper = flightMapper;
    }
     //1 Obtiene la lista de todos los vuelos que cumplan con los criterios de búsqueda establecidos en el query parameter
    @GetMapping("/flights")
    ResponseEntity<List<FlightDto>> findFlightsByDepartureDateAndAirports(
                                @RequestParam String departureDate,
                                @RequestParam(required = false) String departureAirportCode,
                                @RequestParam(required = false) String arrivalAirportCode) {
    List<FlightDto> flights;
    if (departureAirportCode != null && arrivalAirportCode != null) {
        flights = flightService.findFlightsByDepartureDateAndAirports(departureDate, departureAirportCode, arrivalAirportCode);
    } else if (departureAirportCode != null) {
        flights = flightService.findFlightsByDepartureDateAndDepartureAirport(departureDate, departureAirportCode);
    } else if (arrivalAirportCode != null) {
        flights = flightService.findFlightsByDepartureDateAndDepartureAirport(departureDate, arrivalAirportCode);
    } else {
        flights = flightService.findFlightsByDepartureDate(departureDate);
    }
    return new ResponseEntity<>(flights, HttpStatus.OK);
    }
     //2 Permite crear un nuevo vuelo
    @PostMapping("/flights")  
    public ResponseEntity<FlightCreateDto> createFlight(@RequestBody FlightCreateDto flight){ 
        Flight newFlight = flightMapper.toEntity(flight);
        Flight flightCreated = null;
         try {
            flightCreated = flightService.createFlight(newFlight);
         } catch (Exception e) {
             throw new DuplicateCodigoException();
         }
         FlightCreateDto flightCreateDto = flightMapper.toFlightCreateDto(flightCreated);
         URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                         .path("/{id}")
                         .buildAndExpand(flightCreateDto.getId())
                         .toUri();
         return ResponseEntity.created(location).body(flightCreateDto);
    }
    //3 Actualiza un vuelo del catálogo por el id
    @PutMapping("/flights/{id}")
    public ResponseEntity<FlightCreateDto> updateFlight(@PathVariable("id") Integer id, @RequestBody FlightCreateDto flight) {
    Optional<Flight> optionalFlight = flightService.fainId(id);
    if (optionalFlight.isEmpty()) {
        throw new ObjectNotFoundException("Flight not found with id: " + id, null);
    }
    Flight flightToUpdate = flightMapper.toEntity(flight);
    Optional<Flight> updatedFlight = flightService.updateFlight(id, flightToUpdate);
    if (updatedFlight.isPresent()) {
        FlightCreateDto updatedDto = flightMapper.toFlightCreateDto(updatedFlight.get());
        return ResponseEntity.ok().body(updatedDto);
    } else {
        FlightCreateDto createdDto = flightMapper.toFlightCreateDto(flightToUpdate);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdDto.getId())
                .toUri();
        return ResponseEntity.created(location).body(createdDto);
    }
    }
    //4 /Elimina un vuelo
    @DeleteMapping("flights/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id){
    flightService.delete(id);
    return ResponseEntity.noContent().build();
    }
    //Ambiguedad
    //5 Obtiene un listado de los vuelos que salen de un aeropuerto en una fecha determinada
    /*@GetMapping("/flights")
    ResponseEntity<List<FlightDto>> findFlightsByDepartureDate(@RequestParam String departureDate) {
    List<FlightDto> flights = flightService.findFlightsByDepartureDate(departureDate);
    return new ResponseEntity<>(flights, HttpStatus.OK);
    }*/
}
