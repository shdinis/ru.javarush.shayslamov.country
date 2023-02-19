package ru.javarush.country.dao.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.javarush.country.dao.CountryDao;
import ru.javarush.country.domain.Country;

import java.util.List;

public class CountryDaoHibernate implements CountryDao {

    private final SessionFactory sessionFactory;
    public CountryDaoHibernate(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Country> getAll() {
        return getCurrentSession().createQuery("select c from Country c join fetch c.languages", Country.class).list();
    }

    public Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
}
