package aeroline.nr.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import aeroline.nr.api.entities.Booking;

public interface BookingRepository extends JpaRepository<Booking, Integer> {

}
