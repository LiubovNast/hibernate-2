package com.movie.dao;

import com.movie.entity.Staff;
import com.movie.entity.Store;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class StaffDAO extends GenericDAO<Staff, Byte> {
    public StaffDAO(SessionFactory sessionFactory) {
        super(Staff.class, sessionFactory);
    }

    public Staff findByStore(Store store) {
        Query<Staff> query = getCurrentSession().createQuery("select s from Staff s where s.store.id = " + store.getId(), Staff.class);
        query.setMaxResults(1);
        return query.getSingleResult();
    }
}
