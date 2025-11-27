package aeroline.nr.api.api.Dto;

import org.springframework.stereotype.Component;
import aeroline.nr.api.entities.Booking;

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

        // Verificar que user no sea null
        if (booking.getUser() != null) {
            dto.setUserId(booking.getUser().getId());
            dto.setUserFullName(booking.getUser().getFullname());
        }

        // Verificar que flight no sea null
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

        dto.setStatus(booking.getStatus());
        dto.setBookingDate(booking.getBookingDate());

        return dto;
    }
}