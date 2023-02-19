package ru.javarush.country.util;

import ru.javarush.country.domain.City;
import ru.javarush.country.domain.Country;
import ru.javarush.country.domain.CountryLanguage;
import ru.javarush.country.redis.CityCountry;
import ru.javarush.country.redis.Language;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Converter {
    private Converter() {
    }

    public static List<CityCountry> convertDataToRedis(List<City> cities) {
        List<CityCountry> result = new ArrayList<>();
        for (City city : cities) {
            CityCountry cityCountry = new CityCountry();
            cityCountry.setId(city.getId());
            cityCountry.setName(city.getName());
            cityCountry.setPopulation(city.getPopulation());
            cityCountry.setDistrict(city.getDistrict());

            Country country = city.getCountry();
            cityCountry.setAlternativeCountryCode(country.getCode2());
            cityCountry.setContinent(country.getContinent());
            cityCountry.setCountryCode(country.getCode());
            cityCountry.setCountryName(country.getName());
            cityCountry.setCountryPopulation(country.getPopulation());
            cityCountry.setCountryRegion(country.getRegion());
            cityCountry.setCountrySurfaceArea(country.getSurfaceArea());

            Set<Language> languages = new HashSet<>();
            for (CountryLanguage countryLanguage : country.getLanguages()) {
                Language language = new Language();
                language.setTitle(countryLanguage.getLanguage());
                language.setOfficial(countryLanguage.getOfficial());
                language.setPercentage(countryLanguage.getPercentage());
                languages.add(language);
            }
            cityCountry.setLanguages(languages);
            result.add(cityCountry);
        }
        return result;
    }
}
