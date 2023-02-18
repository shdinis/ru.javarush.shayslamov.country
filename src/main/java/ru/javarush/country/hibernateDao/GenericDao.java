package ru.javarush.country.hibernateDao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;


public abstract class GenericDao<T> {
    private final Class<T> clazz;
    private SessionFactory sessionFactory;

    protected GenericDao(final Class<T> clazzToSet, SessionFactory sessionFactory) {
        this.clazz = clazzToSet;
        this.sessionFactory = sessionFactory;
    }

    public T getById(final int id) {
        return Optional.ofNullable(getCurrentSession().get(clazz, id)).orElse(null);
    }

    public List<T> getItems(int offset, int limit) {
        Query query = getCurrentSession().createQuery("from " + clazz.getName(), clazz);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return query.getResultList();
    }

    public List<T> findAll() {
        return getCurrentSession().createQuery("from " + clazz.getName(), clazz).list();
    }

    public T save(final T entity) {
        getCurrentSession().saveOrUpdate(entity);
        return entity;
    }

    public T update(final T entity) {
        return (T) getCurrentSession().merge(entity);
    }

    public void delete(final T entity) {
        getCurrentSession().delete(entity);
    }

    public void deleteById(final int entityId) {
        final T entity = (T) getById(entityId);
        delete(entity);
    }

    protected Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
}
