package com.movie.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import java.util.List;

public abstract class GenericDAO<T, G> {
    private final Class<T> aClass;
    private final SessionFactory sessionFactory;

    public GenericDAO(Class<T> aClass, SessionFactory sessionFactory) {
        this.aClass = aClass;
        this.sessionFactory = sessionFactory;
    }

    public List<T> getAll(int pageNumber, int pageSize) {
        try (Session session = sessionFactory.openSession()) {
            NativeQuery<T> query = session.createNativeQuery("select * from " + aClass.getName(), aClass);
            query.setFirstResult(pageNumber * pageSize);
            query.setMaxResults(pageSize);
            return query.list();
        }
    }

    public T save(T object) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(object);
            transaction.commit();
            return object;
        }
    }

    public T update(T object) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(object);
            transaction.commit();
            return object;
        }
    }

    public T findById(G id) {
        try (Session session = sessionFactory.openSession()) {
            T object = session.find(aClass, id);
            return object;
        }
    }

    public void delete(T object) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(object);
            transaction.commit();
        }
    }

    Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
}
