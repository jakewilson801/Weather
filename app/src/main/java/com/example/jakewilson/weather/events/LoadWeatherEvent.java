package com.example.jakewilson.weather.events;

/**
 * Created by jakewilson on 9/5/15.
 */
public class LoadWeatherEvent {
    private String lat;
    private String lon;

    public LoadWeatherEvent(String la, String lo){
        this.lat = la;
        this.lon = lo;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }
}
