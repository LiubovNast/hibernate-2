package com.movie.dao;

import com.movie.entity.City;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

public class CityDAO extends GenericDAO<City, Short>{
    public CityDAO(SessionFactory sessionFactory) {
        super(City.class, sessionFactory);
    }

    public City findByNameAndCountry(String name, Short countryId) {
            Query<City> query = getCurrentSession().createQuery("select c from City c where c.city = :NAME  and c.country.id = :ID", City.class);
            query.setParameter("NAME", name);
            query.setParameter("ID", countryId);
            return query.getSingleResult();
    }
}
