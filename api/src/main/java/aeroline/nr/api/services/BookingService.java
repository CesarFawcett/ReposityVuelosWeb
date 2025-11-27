package aeroline.nr.api.services;

import java.util.List;
import java.util.Optional;

import aeroline.nr.api.entities.Booking;

public interface BookingService {
    List<Booking> getAllBookings();

    List<Booking> getBookingsByUserId(int userId);

    Optional<Booking> getBookingById(int id);

    Optional<Booking> getBookingByReference(String bookingReference);

    Booking createBooking(String passengerName, int userId, int flightId);

    Booking updateBookingStatus(int id, String status);

    void deleteBooking(int id);
}
