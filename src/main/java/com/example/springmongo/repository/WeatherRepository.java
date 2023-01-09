package com.example.springmongo.repository;

import com.example.springmongo.entity.Weather;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface WeatherRepository extends MongoRepository<Weather, String> {
    List<Weather> findByCallLetters(String callLetters);
    List<Weather> findByCallLettersStartingWith(String callLettersPattern);
    List<Weather> findByAirTemperatureBetweenOrderByAirTemperatureDesc(int lowTemperature, int highTemperature);

    // We can specify a raw query as a Mongo JSON query string.
    @Query("{ 'callLetters' : ?0 }")
    List<Weather> findWeatherByCallLetters(String callLetters);

    @Query("{ 'callLetters' : { $regex: ?0 } }")
    List<Weather> findWeatherByRegexpCallLetters(String callLettersPattern);

    @Aggregation(pipeline ={
            "{$match: { 'airTemperature' : { $gt: ?0, $lt: ?1 } }}",
            "{$sort: { 'airTemperature' : -1 }}"
    })
    List<Weather> findWeatherByAirTemperatureBetweenDesc(int lowTemperature, int highTemperature);

}
