package ru.javarush.country.dao;

import ru.javarush.country.domain.Country;

import java.util.List;

public interface CountryDao {
    List<Country> getAll();
}
