package ru.javarush.dao;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ru.javarush.domain.City;


public class CityDAO extends GenericDAO<City> {
    public CityDAO(SessionFactory sessionFactory) {
        super(City.class, sessionFactory);
    }

    public int getTotalCount() {
        Query<Long> query = getCurrentSession().createQuery("select count(c) from City c", Long.class);
        return Math.toIntExact(query.uniqueResult());
        //TODO: return Math.toIntExact(query.getSingleResult());
    }

    @Override
    public City getById(int id) {
        Query<City> query = getCurrentSession().createQuery("select c from City c join fetch c.country where c.id = :ID", City.class);
        query.setParameter("ID", id);
        return query.getSingleResult();
    }
}
