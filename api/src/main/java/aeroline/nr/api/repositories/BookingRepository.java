package aeroline.nr.api.repositories;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import aeroline.nr.api.entities.Booking;

public interface BookingRepository extends JpaRepository<Booking, Integer> {
    List<Booking> findAllByOrderByBookingDateDesc();

    Optional<Booking> findByBookingReference(String bookingReference);

    List<Booking> findByUserId(int userId);

    List<Booking> findByPassengerNameContainingIgnoreCase(String passengerName);
}
