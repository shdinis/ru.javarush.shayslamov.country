package ru.javarush.country.hibernateDao;

import org.hibernate.SessionFactory;
import ru.javarush.country.domain.Country;

import java.util.List;

public class CountryDao extends GenericDao<Country> {
    public CountryDao(SessionFactory sessionFactory) {
        super(Country.class, sessionFactory);
    }

    @Override
    public List<Country> findAll() {
        return getCurrentSession().createQuery("select c from Country c join fetch c.languages", Country.class).list();
    }
}
