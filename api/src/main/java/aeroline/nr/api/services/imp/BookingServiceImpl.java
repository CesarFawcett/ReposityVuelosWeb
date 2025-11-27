package aeroline.nr.api.services.imp;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import aeroline.nr.api.api.Dto.BookingMapper;
import aeroline.nr.api.entities.Booking;
import aeroline.nr.api.repositories.BookingRepository;
import aeroline.nr.api.services.BookingService;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    public BookingRepository bookingRepository;
    public BookingMapper bookingMapper;

    @Override
    public List<Booking> findAll() {
        return bookingRepository.findAll();
    }

    @Override
    public Booking findById(int id) {
        return bookingRepository.findById(id).orElse(null);
    }

    @Override
    public Booking save(Booking booking) {
        return bookingRepository.save(booking);
    }

    @Override
    public void delete(int id) {
        bookingRepository.deleteById(id);
    }

}
