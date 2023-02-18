package ru.javarush.country.dao.hibernate;

import org.hibernate.SessionFactory;
import ru.javarush.country.dao.CountryDao;
import ru.javarush.country.domain.Country;

import java.util.List;

public class CountryDaoHibernate extends GenericDao<Country> implements CountryDao {
    public CountryDaoHibernate(SessionFactory sessionFactory) {
        super(Country.class, sessionFactory);
    }

    @Override
    public List<Country> getAll() {
        return getCurrentSession().createQuery("select c from Country c join fetch c.languages", Country.class).list();
    }
}
