package ru.javarush.country.dao.hibernate;

import jakarta.persistence.NoResultException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ru.javarush.country.dao.CityDao;
import ru.javarush.country.dao.CountryDao;
import ru.javarush.country.domain.City;
import ru.javarush.country.domain.Country;
import ru.javarush.country.domain.CountryLanguage;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class CityDaoHibernate implements CityDao {

    private final SessionFactory sessionFactory;

    private int step = 500;

    public CityDaoHibernate(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public int getTotalCount() {
        Query<Long> query = getCurrentSession()
                .createQuery("select count(c) from City c", Long.class);
        return Math.toIntExact(query.uniqueResult());
    }

    public Optional<City> getById(Integer id) {
        Optional<City> result;
        Query<City> query = getCurrentSession()
                .createQuery("select c from City c join fetch c.country where c.id = :ID", City.class);
        query.setParameter("ID", id);
        try {
            result = Optional.ofNullable(query.getSingleResult());
        } catch (NoResultException e) {
            result = Optional.empty();
        }
        return result;
    }

    public List<City> getItems(int offset, int limit) {
        Query<City> query = getCurrentSession()
                .createQuery("from City", City.class);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return query.getResultList();
    }

    public List<City> getAll(CountryDao countryDao) {
        try (Session session = getCurrentSession()) {
            List<City> allCities = new ArrayList<>();
            session.beginTransaction();

            List<Country> countries = countryDao.getAll();

            int totalCount = getTotalCount();
            for (int i = 0; i < totalCount; i += step) {
                allCities.addAll(getItems(i, step));
            }
            session.getTransaction().commit();
            return allCities;
        }
    }

    public long timeToGetDataByIdListInMySql(List<Integer> ids) {
        long startMysql = System.currentTimeMillis();
        try (Session session = getCurrentSession()) {
            if(!session.getTransaction().isActive()) {
            session.beginTransaction();
            }
            for (Integer id : ids) {
                Optional<City> city = getById(id);
                if (city.isPresent()) {
                    Set<CountryLanguage> languages = city.get().getCountry().getLanguages();
                }
            }
            session.getTransaction().commit();
        }
        long stopMysql = System.currentTimeMillis();
        return stopMysql - startMysql;
    }

    public Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
}
