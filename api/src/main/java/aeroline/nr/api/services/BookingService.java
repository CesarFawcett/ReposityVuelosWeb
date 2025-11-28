package aeroline.nr.api.services;

import java.util.List;
import java.util.Optional;

import aeroline.nr.api.api.Dto.BookingRequestDto;
import aeroline.nr.api.api.Dto.BookingResponseDto;

public interface BookingService {
    List<BookingResponseDto> getAllBookings();

    List<BookingResponseDto> getBookingsByUserId(int userId);

    Optional<BookingResponseDto> getBookingById(int id);

    Optional<BookingResponseDto> getBookingByReference(String bookingReference);

    BookingResponseDto createBooking(BookingRequestDto requestDto); // <- Cambiar parámetro

    BookingResponseDto updateBookingStatus(int id, String status);

    void deleteBooking(int id);
}
