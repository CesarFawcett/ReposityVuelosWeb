package aeroline.nr.api.api.Dto;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.stereotype.Component;
import aeroline.nr.api.entities.Booking;
import aeroline.nr.api.entities.BookingStatus;

@Component
public class BookingMapper {

    public BookingResponseDto toDto(Booking booking) {
        if (booking == null) {
            return null;
        }

        BookingResponseDto dto = new BookingResponseDto();
        dto.setId(booking.getId());
        dto.setBookingReference(booking.getBookingReference());
        dto.setPassengerName(booking.getPassengerName());

        if (booking.getUser() != null) {
            dto.setUserId(booking.getUser().getId());
            dto.setUserFullName(booking.getUser().getFullname());
        }

        if (booking.getFlight() != null) {
            dto.setFlightId(booking.getFlight().getId());
            dto.setFlightNumber(String.valueOf(booking.getFlight().getFlightNumber()));
            dto.setDepartureCity(booking.getFlight().getDepartureCity());
            dto.setArrivalCity(booking.getFlight().getArrivalCity());
            dto.setDepartureDate(booking.getFlight().getDepartureDate());
            dto.setArrivalDate(booking.getFlight().getArrivalDate());
            dto.setTicketPrice(booking.getFlight().getTicketPrice());
            dto.setTicketCurrency(booking.getFlight().getTicketCurrency());
        }

        dto.setStatus(booking.getStatus().name());
        dto.setBookingDate(booking.getBookingDate());

        return dto;
    }

    public Booking toEntity(BookingRequestDto dto) {
        if (dto == null) {
            return null;
        }

        Booking booking = new Booking();
        booking.setPassengerName(dto.getPassengerName());
        booking.setStatus(BookingStatus.UNCONFIRMED);
        booking.setBookingDate(LocalDateTime.now());
        booking.setBookingReference(generateTempReference());

        return booking;
    }

    private String generateTempReference() {
        return "TEMP-" + UUID.randomUUID().toString().substring(0, 6).toUpperCase();
    }

    public void updateEntityFromDto(BookingRequestDto dto, Booking booking) {
        if (dto == null || booking == null) {
            return;
        }

        booking.setPassengerName(dto.getPassengerName());
    }
}