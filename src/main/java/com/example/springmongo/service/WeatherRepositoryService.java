package com.example.springmongo.service;

import com.example.springmongo.repository.WeatherRepository;
import com.example.springmongo.entity.Weather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WeatherRepositoryService {

    @Autowired
    private WeatherRepository repository;

    // For using MongoRepository
    public void printWeatherByCallLetters(String callLetters){
        List<Weather> weatherList = repository.findByCallLetters(callLetters);
        for(Weather entity : weatherList){
            System.out.println(entity);
        }
    }

    public void printWeatherByCallLettersPrefix(String callLettersPrefix){
        List<Weather> weatherList = repository.findByCallLettersStartingWith(callLettersPrefix);
        for(Weather entity : weatherList){
            System.out.println(entity);
        }
    }

    public void printWeatherBetweenAirTemperatureDesc(int lowTemperature, int highTemperature){
        List<Weather> weatherList = repository.findByAirTemperatureBetweenOrderByAirTemperatureDesc(lowTemperature,highTemperature);
        for(Weather entity : weatherList){
            System.out.println(entity);
        }
    }

    //-------------------------------------------------------
    // For using Mongo JSON Query
    public void printWeatherByCallLettersWithJson(String callLetters){
        List<Weather> weatherList = repository.findWeatherByCallLetters(callLetters);
        for(Weather entity : weatherList){
            System.out.println(entity);
        }
    }

    public void printWeatherByCallLettersRegexWithJson(String callLettersPattern){
        List<Weather> weatherList = repository.findWeatherByRegexpCallLetters(callLettersPattern);
        for(Weather entity : weatherList){
            System.out.println(entity);
        }
    }

    public void printWeatherBetweenAirTemperatureDescWithJson(int lowTemperature, int highTemperature){
        List<Weather> weatherList = repository.findWeatherByAirTemperatureBetweenDesc(lowTemperature,highTemperature);
        for(Weather entity : weatherList){
            System.out.println(entity);
        }
    }

}
