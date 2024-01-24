package com.movie.dao;

import com.movie.entity.Country;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class CountryDAO extends GenericDAO<Country, Short> {
    public CountryDAO(SessionFactory sessionFactory) {
        super(Country.class, sessionFactory);
    }

    public Country findByName(String name) {
        Query<Country> query = getCurrentSession().createQuery("select c from Country c where c.country = :NAME", Country.class);
        query.setParameter("NAME", name);
        return query.getSingleResult();
    }
}
