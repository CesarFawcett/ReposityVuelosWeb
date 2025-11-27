package aeroline.nr.api.services;

import java.util.List;
import aeroline.nr.api.entities.Booking;

public interface BookingService {
    List<Booking> findAll();

    Booking findById(int id);

    Booking save(Booking booking);

    void delete(int id);
}
