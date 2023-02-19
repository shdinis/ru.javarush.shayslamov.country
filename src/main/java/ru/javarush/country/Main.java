package ru.javarush.country;

import ru.javarush.country.domain.City;
import ru.javarush.country.redis.CityCountry;
import ru.javarush.country.util.Converter;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        Initialization initialization = new Initialization();

        List<City> allCities = initialization.getCityDAO().getAll(initialization.getCountryDAO());
        List<CityCountry> preparedData = Converter.convertDataToRedis(allCities);
        initialization.getRedis().pushToRedis(preparedData);

        List<Integer> ids = List.of(1, 3, 2545, 123, 4, 189, 89, 3458, 1189, 10, 102);

        System.out.printf("%s:\t%d ms\n", "MySQL", initialization.getCityDAO().timeToGetDataByIdListInMySql(ids));
        System.out.printf("%s:\t%d ms\n", "Redis", initialization.getRedis().timeToGetDataByIdListInRedis(ids));

        initialization.shutdown();
    }
}
