package ru.javarush.country.dao;

import ru.javarush.country.domain.CountryLanguage;

import java.util.List;

public interface CountryLanguageDao {
    List<CountryLanguage> getAll();
}
