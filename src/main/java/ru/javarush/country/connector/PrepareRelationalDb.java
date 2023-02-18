package ru.javarush.country.connector;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class PrepareRelationalDb {

    private final SessionFactory sessionFactory;

    public PrepareRelationalDb(String configureFileName) {
        sessionFactory = new Configuration().configure(configureFileName).buildSessionFactory();
    }

    public SessionFactory getSessionFactory() {
        return this.sessionFactory;
    }

}
