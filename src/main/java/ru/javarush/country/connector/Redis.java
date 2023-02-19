package ru.javarush.country.connector;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import io.lettuce.core.api.sync.RedisStringCommands;
import ru.javarush.country.redis.CityCountry;

import java.util.List;
import java.util.Optional;

public class Redis implements AutoCloseable {

    private final RedisClient redisClient;
    private final ObjectMapper mapper;

    public Redis(String host, int port, ObjectMapper mapper) {
        this.mapper = mapper;
        this.redisClient = RedisClient.create(RedisURI.create(host, port));
        try (StatefulRedisConnection<String, String> connection = redisClient.connect()) {
            System.out.println("\nConnected to Redis\n");
        }
    }

    @Override
    public void close() {
        redisClient.shutdown();
    }

    public void pushToRedis(List<CityCountry> data) {
        try (StatefulRedisConnection<String, String> connection = redisClient.connect()) {
            RedisStringCommands<String, String> sync = connection.sync();
            for (CityCountry cityCountry : data) {
                try {
                    sync.set(String.valueOf(cityCountry.getId()), mapper.writeValueAsString(cityCountry));
                } catch (JsonProcessingException e) {
                    e.toString();
                }
            }
        }
    }

    public long timeToGetDataByIdListInRedis(List<Integer> ids) {
        long startRedis = System.currentTimeMillis();
        try (StatefulRedisConnection<String, String> connection = redisClient.connect()) {
            RedisCommands<String, String> sync = connection.sync();
            for (Integer id : ids) {
                Optional<String> value =Optional.ofNullable(sync.get(String.valueOf(id)));
                try {
                    if (value.isPresent()){
                    mapper.readValue(value.get(), CityCountry.class);}
                } catch (JsonProcessingException e) {
                    e.toString();
                }
            }
        }
        long stopRedis = System.currentTimeMillis();
        return stopRedis - startRedis;
    }
}
