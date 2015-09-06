package com.example.jakewilson.weather.events;

import com.example.jakewilson.weather.models.List;

/**
 * Created by jakewilson on 9/5/15.
 */
public class WeatherLoadedEvent {
    public java.util.List<List> forecast;
    public WeatherLoadedEvent(java.util.List<List> f){
        this.forecast = f;
    }
}
