package aeroline.nr.api.api.Dto;

import lombok.Data;

@Data
public class BookingRequestDto {
    private String passengerName;
    private int userId;
    private int flightId;
}
