package com.example.springmongo.service;

import com.example.springmongo.entity.Weather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WeatherTemplateService {

    @Autowired
    private MongoTemplate mongoTemplate;

    public void printWeatherByCallLetters(String callLetters){
        Query query = new Query();
        query.addCriteria(Criteria.where("callLetters").is(callLetters));
        List<Weather> weatherList = mongoTemplate.find(query, Weather.class);
        for(Weather entity : weatherList){
            System.out.println(entity);
        }
    }

    public void printWeatherByCallLettersPattern(String callLettersPattern){
        Query query = new Query();
        query.addCriteria(Criteria.where("callLetters").regex(callLettersPattern));
        List<Weather> weatherList = mongoTemplate.find(query, Weather.class);
        for(Weather entity : weatherList){
            System.out.println(entity);
        }
    }

    public void printWeatherBetweenAirTemperatureDesc(int lowTemperature, int highTemperature, int pageNumber, int pageSize){
        final Pageable pageableRequest = PageRequest.of(pageNumber,pageSize);
        Query query = new Query();
        query.with(Sort.by(Sort.Direction.DESC, "airTemperature"));
        query.with(pageableRequest);
        query.addCriteria(Criteria.where("airTemperature").gt(lowTemperature).lt(highTemperature));
        List<Weather> weatherList = mongoTemplate.find(query, Weather.class);
        for(Weather entity : weatherList){
            System.out.println(entity);
        }
    }

    public void printWeatherBetweenAirTemperatureDescWithAggregation(int lowTemperature, int highTemperature){
        AggregationOperation match = Aggregation.match(Criteria.where("airTemperature").gt(lowTemperature).lt(highTemperature));
        AggregationOperation sort = Aggregation.sort(Sort.Direction.DESC, "airTemperature");
        Aggregation aggregation = Aggregation.newAggregation(Weather.class, match,sort);
        AggregationResults<Weather> weatherList = mongoTemplate.aggregate(aggregation, "weather", Weather.class);
        for(Weather entity : weatherList.getMappedResults()){
            System.out.println(entity);
        }
    }

}
