package aeroline.nr.api.services.imp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import aeroline.nr.api.entities.Booking;
import aeroline.nr.api.entities.Flight;
import aeroline.nr.api.entities.User;
import aeroline.nr.api.repositories.BookingRepository;
import aeroline.nr.api.repositories.FlightRepository;
import aeroline.nr.api.repositories.UserRepository;
import aeroline.nr.api.services.BookingService;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Booking> getAllBookings() {
        return bookingRepository.findAllByOrderByBookingDateDesc();
    }

    @Override
    public List<Booking> getBookingsByUserId(int userId) {
        return bookingRepository.findByUserId(userId);
    }

    @Override
    public Optional<Booking> getBookingById(int id) {
        return bookingRepository.findById(id);
    }

    @Override
    public Optional<Booking> getBookingByReference(String bookingReference) {
        return bookingRepository.findByBookingReference(bookingReference);
    }

    @Override
    public Booking createBooking(String passengerName, int userId, int flightId) {
        // Verificar usuario
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        // Verificar vuelo
        Flight flight = flightRepository.findById(flightId)
                .orElseThrow(() -> new RuntimeException("Flight not found with id: " + flightId));

        // Verificar disponibilidad
        if (flight.getSeatCapacity() <= 0) {
            throw new RuntimeException("No available seats on this flight");
        }

        // Generar referencia única
        String bookingReference = generateUniqueBookingReference();

        // Crear booking
        Booking booking = new Booking();
        booking.setBookingReference(bookingReference);
        booking.setPassengerName(passengerName);
        booking.setUser(user);
        booking.setFlight(flight);
        booking.setStatus("CONFIRMED");
        booking.setBookingDate(LocalDateTime.now());

        // Actualizar capacidad del vuelo
        flight.setSeatCapacity(flight.getSeatCapacity() - 1);
        flightRepository.save(flight);

        return bookingRepository.save(booking);
    }

    @Override
    public Booking updateBookingStatus(int id, String status) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found with id: " + id));

        booking.setStatus(status);
        return bookingRepository.save(booking);
    }

    @Override
    public void deleteBooking(int id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found with id: " + id));

        // Si la reserva estaba confirmada, liberar el asiento
        if ("CONFIRMED".equals(booking.getStatus())) {
            Flight flight = booking.getFlight();
            flight.setSeatCapacity(flight.getSeatCapacity() + 1);
            flightRepository.save(flight);
        }

        bookingRepository.delete(booking);
    }

    private String generateUniqueBookingReference() {
        String reference;
        do {
            reference = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        } while (bookingRepository.findByBookingReference(reference).isPresent());

        return reference;
    }
}
