package com.movie.dao;

import com.movie.entity.Address;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class AddressDAO extends GenericDAO<Address, Short> {
    public AddressDAO(SessionFactory sessionFactory) {
        super(Address.class, sessionFactory);
    }

    public Address findByAddressDistrictCodeAndPhone(String address, String district, String postalCode, String phone) {
        Query<Address> query = getCurrentSession().createQuery("select a from Address a where a.address = :NAME " +
                " and a.district = :DISTR and a.code = :CODE and a.phone = :PHONE", Address.class);
        query.setParameter("NAME", address);
        query.setParameter("DISTR", district);
        query.setParameter("CODE", postalCode);
        query.setParameter("PHONE", phone);
        return query.getSingleResult();
    }
}
