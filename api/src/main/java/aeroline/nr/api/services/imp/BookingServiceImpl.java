package aeroline.nr.api.services.imp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import aeroline.nr.api.api.Dto.BookingMapper;
import aeroline.nr.api.api.Dto.BookingRequestDto;
import aeroline.nr.api.api.Dto.BookingResponseDto;
import aeroline.nr.api.entities.Booking;
import aeroline.nr.api.entities.BookingStatus;
import aeroline.nr.api.entities.Flight;
import aeroline.nr.api.entities.User;
import aeroline.nr.api.repositories.BookingRepository;
import aeroline.nr.api.repositories.FlightRepository;
import aeroline.nr.api.repositories.UserRepository;
import aeroline.nr.api.services.BookingService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final FlightRepository flightRepository;
    private final UserRepository userRepository;
    private final BookingMapper bookingMapper;

    @Override
    public List<BookingResponseDto> getAllBookings() {
        return bookingRepository.findAllByOrderByBookingDateDesc()
                .stream()
                .map(bookingMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookingResponseDto> getBookingsByUserId(int userId) {
        return bookingRepository.findByUserId(userId)
                .stream()
                .map(bookingMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<BookingResponseDto> getBookingById(int id) {
        return bookingRepository.findById(id)
                .map(bookingMapper::toDto);
    }

    @Override
    public Optional<BookingResponseDto> getBookingByReference(String bookingReference) {
        return bookingRepository.findByBookingReference(bookingReference)
                .map(bookingMapper::toDto);
    }

    @Override
    public BookingResponseDto createBooking(BookingRequestDto requestDto) {

        User user = userRepository.findById(requestDto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + requestDto.getUserId()));

        Flight flight = flightRepository.findById(requestDto.getFlightId())
                .orElseThrow(() -> new RuntimeException("Flight not found with id: " + requestDto.getFlightId()));

        if (flight.getSeatCapacity() <= 0) {
            throw new RuntimeException("No available seats on this flight");
        }

        String bookingReference = generateUniqueBookingReference();

        Booking booking = new Booking();
        booking.setBookingReference(bookingReference);
        booking.setPassengerName(requestDto.getPassengerName());
        booking.setUser(user);
        booking.setFlight(flight);
        booking.setStatus(BookingStatus.CONFIRMED);
        booking.setBookingDate(LocalDateTime.now());

        flight.setSeatCapacity(flight.getSeatCapacity() - 1);
        flightRepository.save(flight);

        Booking savedBooking = bookingRepository.save(booking);
        return bookingMapper.toDto(savedBooking);
    }

    @Override
    public BookingResponseDto updateBookingStatus(int id, String status) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found with id: " + id));

        try {
            BookingStatus newStatus = BookingStatus.valueOf(status.toUpperCase());
            booking.setStatus(newStatus);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid status: " + status);
        }

        Booking updatedBooking = bookingRepository.save(booking);
        return bookingMapper.toDto(updatedBooking);
    }

    @Override
    public void deleteBooking(int id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found with id: " + id));

        if (booking.getStatus() == BookingStatus.CONFIRMED) {
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
