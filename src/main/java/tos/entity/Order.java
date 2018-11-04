package tos.entity;

import javax.persistence.*;

/**
 * Created by Ashot Karakhanyan on 24-12-2013
 */


@Entity
@Table(name = "orders")
public class Order extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "flight_id")
    private Flight flight;

    @Column(name = "class1_tickets_Count")
    private int class1TicketsCount;

    @Column(name = "class2_tickets_Count")
    private int class2TicketsCount;

    @Column(name = "credit_card")
    private String creditCardNumber;


    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
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
}


