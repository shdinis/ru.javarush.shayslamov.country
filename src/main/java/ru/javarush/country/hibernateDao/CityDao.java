package ru.javarush.country.hibernateDao;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ru.javarush.country.domain.City;

import java.util.Optional;


public class CityDao extends GenericDao<City> {
    public CityDao(SessionFactory sessionFactory) {
        super(City.class, sessionFactory);
    }

    public int getTotalCount() {
        Query<Long> query = getCurrentSession().createQuery("select count(c) from City c", Long.class);
        return Math.toIntExact(query.uniqueResult());
        //TODO: return Math.toIntExact(query.getSingleResult());
    }

    @Override
    public City getById(int id) {
        Query<City> query = getCurrentSession()
                .createQuery("select c from City c join fetch c.country where c.id = :ID", City.class);
        query.setParameter("ID", id);
        return Optional.ofNullable(query.getSingleResult()).orElse(null);
    }
}
