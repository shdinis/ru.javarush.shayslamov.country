package ru.javarush.dao;

import org.hibernate.SessionFactory;
import ru.javarush.domain.Country;

import java.util.List;

public class CountryDAO extends GenericDAO<Country> {
    public CountryDAO(SessionFactory sessionFactory) {
        super(Country.class, sessionFactory);
    }

    @Override
    public List<Country> findAll() {
        return getCurrentSession().createQuery("select c from Country c join fetch c.languages", Country.class).list();
    }
}
