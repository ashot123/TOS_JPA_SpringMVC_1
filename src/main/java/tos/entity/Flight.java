package tos.entity;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Ashot Karakhanyan on 24-12-2013
 */

@Entity
@Table(name = "flights")
public class Flight extends AbstractEntity {

    @Column(name = "departure_date")
    private Date departureDate;

    @Column(name = "arrival_date")
    private Date arrivalDate;

    @ManyToOne
    @JoinColumn(name = "departure_city_id")
    private City departureCity;

    @ManyToOne
    @JoinColumn(name = "arrival_city_id")
    private City arrivalCity;

    @ManyToOne/*(cascade={CascadeType.PERSIST, CascadeType.MERGE} )*/
    @JoinColumn(name = "aircraft_id")
    private Aircraft aircraft;

    @Column(name = "class1_price")
    private int class1Price;

    @Column(name = "class2_price")
    private int class2Price;

    @Column(name = "class1_tickets_available")
    private int class1TicketsAvailable;

    @Column(name = "class2_tickets_available")
    private int class2TicketsAvailable;

    public Flight() {
    }

    public Flight(Date departureDate,
                  Date arrivalDate,
                  City departureCity,
                  City arrivalCity,
                  Aircraft aircraft,
                  int class1Price,
                  int class2Price) {

        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.departureCity = departureCity;
        this.arrivalCity = arrivalCity;
        this.aircraft = aircraft;
        this.class1Price = class1Price;
        this.class2Price = class2Price;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public int getClass1Price() {
        return class1Price;
    }

    public void setClass1Price(int class1Price) {
        this.class1Price = class1Price;
    }

    public int getClass2Price() {
        return class2Price;
    }

    public void setClass2Price(int class2Price) {
        this.class2Price = class2Price;
    }

    public City getDepartureCity() {
        return departureCity;
    }

    public void setDepartureCity(City departureCity) {
        this.departureCity = departureCity;
    }

    public City getArrivalCity() {
        return arrivalCity;
    }

    public void setArrivalCity(City arrivalCity) {
        this.arrivalCity = arrivalCity;
    }

    public Aircraft getAircraft() {
        return aircraft;
    }

    public void setAircraft(Aircraft aircraft) {
        this.aircraft = aircraft;
    }


    public int getClass1TicketsAvailable() {
        return class1TicketsAvailable;
    }

    public void setClass1TicketsAvailable(int class1TicketsAvailable) {
        this.class1TicketsAvailable = class1TicketsAvailable;
    }

    public int getClass2TicketsAvailable() {
        return class2TicketsAvailable;
    }

    public void setClass2TicketsAvailable(int class2TicketsAvailable) {
        this.class2TicketsAvailable = class2TicketsAvailable;
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return "Flight{" +
                "departureDate=" + dateFormat.format(departureDate) +
                ", arrivalDate=" + dateFormat.format(arrivalDate) +
                ", departureCity=" + departureCity +
                ", arrivalCity=" + arrivalCity +
                ", aircraft=" + aircraft +
                ", class1Price=" + class1Price +
                ", class2Price=" + class2Price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Flight)) return false;

        Flight flight = (Flight) o;

        if (class1Price != flight.class1Price) return false;
        if (class1TicketsAvailable != flight.class1TicketsAvailable) return false;
        if (class2Price != flight.class2Price) return false;
        if (class2TicketsAvailable != flight.class2TicketsAvailable) return false;
        if (!aircraft.equals(flight.aircraft)) return false;
        if (!arrivalCity.equals(flight.arrivalCity)) return false;
        if (!arrivalDate.equals(flight.arrivalDate)) return false;
        if (!departureCity.equals(flight.departureCity)) return false;
        if (!departureDate.equals(flight.departureDate)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = departureDate.hashCode();
        result = 31 * result + arrivalDate.hashCode();
        result = 31 * result + departureCity.hashCode();
        result = 31 * result + arrivalCity.hashCode();
        result = 31 * result + aircraft.hashCode();
        result = 31 * result + class1Price;
        result = 31 * result + class2Price;
        result = 31 * result + class1TicketsAvailable;
        result = 31 * result + class2TicketsAvailable;
        return result;
    }
}
