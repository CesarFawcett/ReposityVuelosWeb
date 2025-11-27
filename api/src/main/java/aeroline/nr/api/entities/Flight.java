package aeroline.nr.api.entities;

import javax.persistence.Column;
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

    @Column(name = "departure_date", nullable = false)
    private String departureDate;

    @Column(name = "departure_airport_code", nullable = false)
    private String departureAirportCode;

    @Column(name = "departure_airport_name")
    private String departureAirportName;

    @Column(name = "departure_city")
    private String departureCity;

    @Column(name = "departure_locale")
    private String departureLocale;

    @Column(name = "arrival_date")
    private String arrivalDate;

    @Column(name = "arrival_airport_code", nullable = false)
    private String arrivalAirportCode;

    @Column(name = "arrival_airport_name")
    private String arrivalAirportName;

    @Column(name = "arrival_city")
    private String arrivalCity;

    @Column(name = "arrival_locale")
    private String arrivalLocale;

    @Column(name = "ticket_price")
    private int ticketPrice;

    @Column(name = "ticket_currency")
    private String ticketCurrency;

    @Column(name = "flight_number")
    private int flightNumber;

    @Column(name = "seat_capacity")
    private int seatCapacity;
}