package aeroline.nr.api.api.Dto;

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

        // Convertir el enum BookingStatus a String para el DTO
        dto.setStatus(booking.getStatus().name()); // <- CORRECCIÓN IMPORTANTE
        dto.setBookingDate(booking.getBookingDate());

        return dto;
    }

    // MÉTODO ADICIONAL RECOMENDADO: Para crear entidad desde DTO
    public Booking toEntity(BookingRequestDto dto) {
        if (dto == null) {
            return null;
        }

        Booking booking = new Booking();
        booking.setPassengerName(dto.getPassengerName());
        // NOTA: user y flight se deben establecer en el service mediante IDs
        // booking.setUser() y booking.setFlight() se asignan en el service
        booking.setStatus(BookingStatus.UNCONFIRMED); // Estado por defecto
        booking.setBookingDate(java.time.LocalDateTime.now());

        return booking;
    }

    // MÉTODO ADICIONAL RECOMENDADO: Para actualizar entidad desde DTO
    public void updateEntityFromDto(BookingRequestDto dto, Booking booking) {
        if (dto == null || booking == null) {
            return;
        }

        booking.setPassengerName(dto.getPassengerName());
        // NOTA: user y flight normalmente no se actualizan en una reserva existente
    }
}