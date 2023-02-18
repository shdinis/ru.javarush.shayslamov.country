package ru.javarush.country.dao.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public abstract class GenericDao<T> {
    private final Class<T> clazz;
    private SessionFactory sessionFactory;

    protected GenericDao(final Class<T> clazzToSet, SessionFactory sessionFactory) {
        this.clazz = clazzToSet;
        this.sessionFactory = sessionFactory;
    }

    protected Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
}
