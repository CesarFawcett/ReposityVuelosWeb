package aeroline.nr.api.api.Dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class BookingResponseDto {
    private int id;
    private String bookingReference;
    private String passengerName;
    private int userId;
    private String userFullName;
    private int flightId;
    private String flightNumber;
    private String departureCity;
    private String arrivalCity;
    private String departureDate;
    private String arrivalDate;
    private int ticketPrice;
    private String ticketCurrency;
    private String status;
    private LocalDateTime bookingDate;
}
