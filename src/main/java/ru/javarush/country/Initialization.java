package ru.javarush.country;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.javarush.country.connector.Redis;
import ru.javarush.country.connector.RelationalDb;
import ru.javarush.country.dao.CityDao;
import ru.javarush.country.dao.CountryDao;
import ru.javarush.country.dao.hibernate.CityDaoHibernate;
import ru.javarush.country.dao.hibernate.CountryDaoHibernate;
import ru.javarush.country.dao.hibernate.CountryLanguageDaoHibernate;

public class Initialization {
    private final Redis redis;
    private final CityDao cityDAO;
    private final CountryDao countryDAO;
    private final CountryLanguageDaoHibernate countryLanguageDAOHibernate;
    private final RelationalDb relationalDb;

    public Initialization() {
        this.relationalDb = new RelationalDb("hibernate.cfg.xml");
        this.redis = new Redis("localhost", 6379, new ObjectMapper());
        this.cityDAO = new CityDaoHibernate(relationalDb.getSessionFactory());
        this.countryDAO = new CountryDaoHibernate(relationalDb.getSessionFactory());
        this.countryLanguageDAOHibernate = new CountryLanguageDaoHibernate(relationalDb.getSessionFactory());
    }

    public void shutdown() {
        relationalDb.close();
        redis.close();
    }

    public Redis getRedis() {
        return redis;
    }

    public CityDao getCityDAO() {
        return cityDAO;
    }

    public CountryDao getCountryDAO() {
        return countryDAO;
    }

    public CountryLanguageDaoHibernate getCountryLanguageDAO() {
        return countryLanguageDAOHibernate;
    }

    public RelationalDb getRelationalDb() {
        return relationalDb;
    }
}
