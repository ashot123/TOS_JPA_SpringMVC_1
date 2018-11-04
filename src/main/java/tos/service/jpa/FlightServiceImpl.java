package tos.service.jpa;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tos.entity.City;
import tos.entity.Flight;
import tos.service.FlightService;
import tos.service.exception.ServiceException;

import javax.persistence.*;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Ashot Karakhanyan on 28-04-2014
 */

@Service("flightService")
@Transactional(readOnly = true)
public class FlightServiceImpl implements FlightService {

    @PersistenceContext
    private EntityManager em;


    @Override
    @Transactional
    public Flight selectById(long id) throws ServiceException {
        try {
            Flight flight = em.createQuery("SELECT u FROM Flight u WHERE u.id=:id", Flight.class)
                    .setParameter("id", id)
                    .getSingleResult();
            return flight;
        } catch (NonUniqueResultException e) {
            throw new ServiceException("Flights found by id " + id + " is not unique", e);

        } catch (NoResultException e) {
            throw new ServiceException("No flight found by id " + id, e);
        }


    }

    @Override
    @Transactional
    public List<Flight> selectFlights(long departureCityId, long arrivalCityId, Date departureDate, Date arrivalDate) {

        TypedQuery<Flight> query;

        query = em.createQuery("select f from Flight f " +
                "where f.departureDate >= :dDate and f.arrivalDate <= :aDate " +
                "and :dcId > 0 and f.departureCity.id = :dcId " +
                "and :acId > 0 and f.arrivalCity.id = :acId", Flight.class);

        query.setParameter("dDate", departureDate);
        query.setParameter("aDate", arrivalDate);

        query.setParameter("dcId", departureCityId);
        query.setParameter("acId", arrivalCityId);


/*
        if (departureCityId != 0 && arrivalCityId != 0) {
            query = em.createQuery("select f from Flight f " +
                    "where f.departureDate >= :dDate and f.arrivalDate <= :aDate " +
                    "and f.departureCity.id = :dcId and f.arrivalCity.id = :acId", Flight.class);
            query.setParameter("dcId", departureCityId);
            query.setParameter("acId", arrivalCityId);

        } else if (departureCityId != 0) {
            query = em.createQuery("select f from Flight f " +
                    "where f.departureDate >= :dDate and f.arrivalDate <= :aDate " +
                    "and f.departureCity.id = :dcId", Flight.class);
            query.setParameter("dcId", departureCityId);

        } else if (arrivalCityId != 0) {
            query = em.createQuery("select f from Flight f " +
                    "where f.departureDate >= :dDate and f.arrivalDate <= :aDate " +
                    "and f.arrivalCity.id = :acId", Flight.class);
            query.setParameter("acId", arrivalCityId);

        } else {
            query = em.createQuery("select f from Flight f " +
                    "where f.departureDate >= :dDate and f.arrivalDate <= :aDate ", Flight.class);
        }

        query.setParameter("dDate", departureDate);
        query.setParameter("aDate", arrivalDate);
*/

        List<Flight> resultList = query.getResultList();
        return resultList;
    }

    @Override
    @javax.transaction.Transactional
    public List<Flight> selectFlightsByCriteria(long departureCityId, long arrivalCityId, Date departureDate, Date arrivalDate) {

        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();

        final CriteriaQuery<Flight> criteriaQuery = criteriaBuilder.createQuery(Flight.class);
        Root<Flight> flightRoot = criteriaQuery.from(Flight.class);

        // Time to define where clause in terms of Predicates
        // This list will contain all Predicates (where clauses)
        List<Predicate> criteriaList = new ArrayList<>();

        if (departureCityId != 0) {
            Predicate departureCityPredicate =
                    criteriaBuilder.equal(flightRoot.get("departureCity").get("id"), departureCityId);

            criteriaList.add(departureCityPredicate);
        }
        if (arrivalCityId != 0) {
            Predicate arrivalCityPredicate =
                    criteriaBuilder.equal(flightRoot.get("arrivalCity").get("id"), arrivalCityId);

            criteriaList.add(arrivalCityPredicate);
        }


        // departure and arrival time range criteria
        // predicates.add(builder.lessThanOrEqualTo(root.<Date>get("dateCreated"), param));

        criteriaList.add(criteriaBuilder.greaterThanOrEqualTo(flightRoot.<Date>get("departureDate"), departureDate));
        criteriaList.add(criteriaBuilder.lessThanOrEqualTo(flightRoot.<Date>get("arrivalDate"), arrivalDate));


        // Pass the criteria list to the where method of criteria query
        criteriaQuery.where(criteriaBuilder.
                and((Predicate[]) criteriaList.toArray(new Predicate[criteriaList.size()])));

        TypedQuery<Flight> typedQuery = em.createQuery(criteriaQuery);
        List<Flight> resultList = typedQuery.getResultList();

        return resultList;
    }
}
