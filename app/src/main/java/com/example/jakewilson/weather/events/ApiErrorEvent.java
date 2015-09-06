package com.example.jakewilson.weather.events;

/**
 * Created by jakewilson on 9/5/15.
 */
public class ApiErrorEvent {
    private Throwable error;
    public ApiErrorEvent(Throwable t) {
        this.error = t;
    }
}
