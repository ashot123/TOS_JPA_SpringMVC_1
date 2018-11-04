package tos.service;

import tos.entity.City;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Ashot Karakhanyan on 28-04-2014
 */
public interface OrderService {

    @Transactional
    void orderTickets(long flightId, String creditCardNumber, int class1TicketsCount, int class2TicketsCount);


}
