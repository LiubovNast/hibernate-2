package com.movie.dao;

import com.movie.entity.Store;
import org.hibernate.SessionFactory;

public class StoreDAO extends GenericDAO<Store, Byte> {
    public StoreDAO(SessionFactory sessionFactory) {
        super(Store.class, sessionFactory);
    }
}
