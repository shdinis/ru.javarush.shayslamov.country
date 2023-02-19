package ru.javarush.country.dao.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.javarush.country.dao.CountryLanguageDao;
import ru.javarush.country.domain.CountryLanguage;

import java.util.List;

public class CountryLanguageDaoHibernate implements CountryLanguageDao {

    private final SessionFactory sessionFactory;
    public CountryLanguageDaoHibernate(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public List<CountryLanguage> getAll() {
        return getCurrentSession().createQuery("select c from CountryLanguage c join fetch c.country", CountryLanguage.class).list();
    }

    public Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
}
