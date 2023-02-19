package ru.javarush.country.dao;

import ru.javarush.country.domain.City;

import java.util.List;
import java.util.Optional;

public interface CityDao {
    Optional<City> getById(Integer id);

    List<City> getItems(int offset, int limit);

    List<City> getAll(CountryDao countryDao);

    long timeToGetDataByIdListInMySql(List<Integer> ids);
}
