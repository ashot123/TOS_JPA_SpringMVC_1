package tos.service.jpa;

import org.springframework.stereotype.Service;
import tos.entity.City;
import tos.service.CityService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Ashot Karakhanyan on 28-04-2014
 */

@Service("cityService")
public class CityServiceImpl implements CityService {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public List<City> selectAll()  {
        TypedQuery<City> query = em.createQuery("select c from City c", City.class);
        return query.getResultList();
    }



}
