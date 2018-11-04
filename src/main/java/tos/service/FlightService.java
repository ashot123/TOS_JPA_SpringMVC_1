package tos.service;

import tos.entity.Flight;
import tos.service.exception.ServiceException;

import java.util.Date;
import java.util.List;

/**
 * Created by Ashot Karakhanyan on 28-04-2014
 */
public interface FlightService {

    Flight selectById(long id) throws ServiceException;
    List<Flight> selectFlights(long departureCityId, long arrivalCityId, Date departureDate, Date arrivalDate);
    List<Flight> selectFlightsByCriteria(long departureCityId, long arrivalCityId, Date departureDate, Date arrivalDate);
}
