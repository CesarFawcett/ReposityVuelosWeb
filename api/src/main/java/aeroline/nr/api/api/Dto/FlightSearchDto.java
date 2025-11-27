package aeroline.nr.api.api.Dto;

import lombok.Data;

@Data
public class FlightSearchDto {
    private String departureCity;
    private String arrivalCity;
    private String departureDate;
    private Integer maxPrice;
}
