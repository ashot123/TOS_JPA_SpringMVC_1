package tos.service.jpa;

import org.springframework.stereotype.Service;
import tos.entity.Flight;
import tos.entity.Order;
import tos.service.OrderService;
import tos.service.exception.ServiceException;

import javax.persistence.*;
import javax.transaction.Transactional;

/**
 * Created by Ashot Karakhanyan on 28-04-2014
 */

@Service("orderService")
public class OrderServiceImpl implements OrderService {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public void orderTickets(long flightId, String creditCardNumber, int class1TicketsCount, int class2TicketsCount) {

        try {

            //update flights (remove tickets)
            Flight flight = em.createQuery("SELECT u FROM Flight u WHERE u.id=:id", Flight.class)
                    .setParameter("id", flightId)
                    .getSingleResult();

            if(class1TicketsCount > 0) {
                flight.setClass1TicketsAvailable(flight.getClass1TicketsAvailable() - class1TicketsCount);
            }

            if(class2TicketsCount > 0) {
                flight.setClass2TicketsAvailable(flight.getClass2TicketsAvailable() - class2TicketsCount);
            }

            em.persist(flight);

            //create order
            Order order = new Order();
            order.setFlight(flight);
            order.setCreditCardNumber(creditCardNumber);
            order.setClass1TicketsCount(class1TicketsCount);
            order.setClass2TicketsCount(class2TicketsCount);

            em.persist(order);

        } catch (NonUniqueResultException e) {
            throw new ServiceException("Flights found by id " + flightId + " is not unique", e);

        } catch (NoResultException e) {
            throw new ServiceException("No flight found by id " + flightId, e);
        } catch (PersistenceException e) {
            throw new ServiceException("zzz", e);
        }



        // insert new order into order

        //return null;



/*        TypedQuery<Flight> query;

        query = em.createQuery("select f from Flight f " +
                "where f.departureDate >= :dDate and f.arrivalDate <= :aDate " +
                "and :dcId > 0 and f.departureCity.flightId = :dcId " +
                "and :acId > 0 and f.arrivalCity.flightId = :acId", Flight.class);

        query.setParameter("dDate", departureDate);
        query.setParameter("aDate", arrivalDate);

        query.setParameter("dcId", departureCityId);
        query.setParameter("acId", arrivalCityId);*/

    }


}
