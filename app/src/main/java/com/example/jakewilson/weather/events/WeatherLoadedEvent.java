package com.example.jakewilson.weather.events;

import com.example.jakewilson.weather.models.WeatherResponse;

/**
 * Created by jakewilson on 9/5/15.
 */
public class WeatherLoadedEvent {
    public WeatherResponse forecast;
    public WeatherLoadedEvent(WeatherResponse f){
        this.forecast = f;
    }
}
