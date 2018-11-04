package tos.controller;


import tos.entity.Flight;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by Ashot Karakhanyan on 30-04-2014
 */
public class OrderForm {

    private String departureCity;
    private String arrivalCity;
    private Date departureDate;
    private Date arrivalDate;

    private int class1TicketsAvailable;

    private int class2TicketsAvailable;

    private long flightId;

    @NotNull @Min(0)
    private int class1TicketsCount;

    @NotNull @Min(0)
    private int class2TicketsCount;

    @Digits(integer = 10, fraction = 0)
    private String creditCardNumber;

    public OrderForm() {
    }

    public long getFlightId() {
        return flightId;
    }

    public void setFlightId(long flightId) {
        this.flightId = flightId;
    }

    public int getClass1TicketsCount() {
        return class1TicketsCount;
    }

    public void setClass1TicketsCount(int class1TicketsCount) {
        this.class1TicketsCount = class1TicketsCount;
    }

    public int getClass2TicketsCount() {
        return class2TicketsCount;
    }

    public void setClass2TicketsCount(int class2TicketsCount) {
        this.class2TicketsCount = class2TicketsCount;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }


    public String getDepartureCity() {
        return departureCity;
    }

    public String getArrivalCity() {
        return arrivalCity;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public int getClass1TicketsAvailable() {
        return class1TicketsAvailable;
    }

    public int getClass2TicketsAvailable() {
        return class2TicketsAvailable;
    }

    public void setClass1TicketsAvailable(int class1TicketsAvailable) {
        this.class1TicketsAvailable = class1TicketsAvailable;
    }

    public void setClass2TicketsAvailable(int class2TicketsAvailable) {
        this.class2TicketsAvailable = class2TicketsAvailable;
    }

    public void initFrom(Flight flight) {
        this.departureCity = flight.getDepartureCity().getName();
        this.arrivalCity = flight.getArrivalCity().getName();
        this.departureDate = flight.getDepartureDate();
        this.arrivalDate = flight.getArrivalDate();
        this.class1TicketsAvailable = flight.getClass1TicketsAvailable();
        this.class2TicketsAvailable = flight.getClass2TicketsAvailable();

    }



}
