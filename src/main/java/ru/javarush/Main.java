package ru.javarush;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import ru.javarush.dao.CityDAO;
import ru.javarush.dao.CountryDAO;
import ru.javarush.dao.CountryLanguageDAO;
import ru.javarush.domain.City;
import ru.javarush.domain.Country;
import ru.javarush.domain.CountryLanguage;

import java.util.Properties;

public class Main {

    private final SessionFactory sessionFactory;

    private CityDAO cityDAO;
    private CountryDAO countryDAO;
    private CountryLanguageDAO countryLanguageDAO;

    public Main() {
        Properties properties = new Properties();
        properties.put(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
        properties.put(Environment.DRIVER, "com.p6spy.engine.spy.P6SpyDriver");
        properties.put(Environment.URL, "jdbc:p6spy:mysql://localhost:3306/world");
        properties.put(Environment.USER, "root");
        properties.put(Environment.PASS, "root");
        properties.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
        properties.put(Environment.HBM2DDL_AUTO, "validate");

        sessionFactory = new Configuration()
                .addAnnotatedClass(City.class)
                .addAnnotatedClass(Country.class)
                .addAnnotatedClass(CountryLanguage.class)
                .addProperties(properties)
                .buildSessionFactory();

        cityDAO = new CityDAO(sessionFactory);
        countryDAO = new CountryDAO(sessionFactory);
        countryLanguageDAO = new CountryLanguageDAO(sessionFactory);

    }

    public static void main(String[] args) {
      Main main = new Main();
    }
}