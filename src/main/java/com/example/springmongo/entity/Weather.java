package com.example.springmongo.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

public class Weather {

    @Id
    private String id;
    private String callLetters;
    private Integer elevation;
    private String type;
    @Field("airTemperature.value")
    private Integer airTemperature;
    @Field("pressure.value")
    private Integer pressure;

    public Weather(String id, String callLetters, Integer elevation, String type, Integer airTemperature, Integer pressure) {
        this.id = id;
        this.callLetters = callLetters;
        this.elevation = elevation;
        this.type = type;
        this.airTemperature = airTemperature;
        this.pressure = pressure;
    }

    @Override
    public String toString() {
        return String.format("Weather[id=%s, callLetters=%s, elevation=%d, type=%s, airTemperature=%d, pressure=%d]",
                id, callLetters, elevation, type, airTemperature, pressure);
    }
}
