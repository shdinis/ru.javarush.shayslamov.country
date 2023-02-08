package ru.javarush.dao;

import org.hibernate.SessionFactory;
import ru.javarush.domain.CountryLanguage;

public class CountryLanguageDAO extends GenericDAO<CountryLanguage>{
    public CountryLanguageDAO(SessionFactory sessionFactory) {
        super(CountryLanguage.class, sessionFactory);
    }
}
