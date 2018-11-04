package tos.service;

import tos.entity.City;
import tos.entity.Flight;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Ashot Karakhanyan on 28-04-2014
 */
public interface CityService {
    @Transactional
    List<City> selectAll();


}
