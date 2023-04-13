package aeroline.nr.api.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import aeroline.nr.api.api.Dto.BookingMapper;
import aeroline.nr.api.entities.Booking;
import aeroline.nr.api.entities.BookingStatus;
import aeroline.nr.api.repositories.BookingRepository;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    public BookingRepository bookingRepository;
    public BookingMapper bookingMapper;

    @Override
    public void delete(Integer id) {
        bookingRepository.deleteById(id);
    }

    @Override
    public Booking createBooking(Booking newBooking) {
        return bookingRepository.save(newBooking);
    }

    @Override
    public List<Booking> findBookingsByStatusAndCustomerName(BookingStatus status, String customerName) {
        if (status != null && customerName != null) {
            return bookingRepository.findByStatusAndCustomerNameWithQuery(status, customerName);
        } else if (status != null) {
            return bookingRepository.findByStatus(status);
        } else if (customerName != null) {
            return bookingRepository.findByCustomerName(customerName);
        } else {
            return bookingRepository.findAll();
        }
    }

    @Override
    public List<Booking> findBookingsByStatus(BookingStatus status) {
    return bookingRepository.findByStatus(status);
    }

    @Override
    public List<Booking> findBookingsByCustomerName(String customerName) {
    return bookingRepository.findByCustomerName(customerName);
    }

    @Override
    public List<Booking> findAllBookings() {
    return bookingRepository.findAll();
    }
 
    @Override
    public Optional<Booking> findById(Integer id) {
        return bookingRepository.findById(id);
    }

    @Override
    public List<Booking> findBookingsByFlightId(Integer flightId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findBookingsByFlightId'");
    }
}
