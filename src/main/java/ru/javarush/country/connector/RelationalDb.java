package ru.javarush.country.connector;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class RelationalDb implements AutoCloseable{

    private final SessionFactory sessionFactory;

    public RelationalDb(String configureFileName) {
        sessionFactory = new Configuration().configure(configureFileName).buildSessionFactory();
    }

    public SessionFactory getSessionFactory() {
        return this.sessionFactory;
    }

    @Override
    public void close() {
        sessionFactory.close();
    }
}
