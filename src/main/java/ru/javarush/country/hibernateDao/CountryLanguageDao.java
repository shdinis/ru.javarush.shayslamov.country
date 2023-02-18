package ru.javarush.country.hibernateDao;

import org.hibernate.SessionFactory;
import ru.javarush.country.domain.CountryLanguage;

public class CountryLanguageDao extends GenericDao<CountryLanguage> {
    public CountryLanguageDao(SessionFactory sessionFactory) {
        super(CountryLanguage.class, sessionFactory);
    }
}
