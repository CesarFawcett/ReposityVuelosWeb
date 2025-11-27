package aeroline.nr.api.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "flights")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String departureDate;

    private String departureAirportCode;

    private String departureAirportName;

    private String departureCity;

    private String departureLocale;

    private String arrivalDate;

    private String arrivalAirportCode;

    private String arrivalAirportName;

    private String arrivalCity;

    private String arrivalLocale;

    private int ticketPrice;

    private String ticketCurrency;

    private int flightNumber;

    private int seatCapacity;
}