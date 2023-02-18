package ru.javarush.country.dao.hibernate;

import org.hibernate.SessionFactory;
import ru.javarush.country.dao.CountryLanguageDao;
import ru.javarush.country.domain.CountryLanguage;

public class CountryLanguageDaoHibernate extends GenericDao<CountryLanguage> implements CountryLanguageDao {
    public CountryLanguageDaoHibernate(SessionFactory sessionFactory) {
        super(CountryLanguage.class, sessionFactory);
    }
}
