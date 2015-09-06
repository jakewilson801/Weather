package com.example.jakewilson.weather.clients;

import com.example.jakewilson.weather.models.WeatherResponse;

import retrofit.Call;
import retrofit.http.GET;

/**
 * Created by jakewilson on 9/2/15.
 */
public interface WeatherApi {
    @GET("/data/2.5/forecast/daily?q=Salt%20Lake%20City,usa&units=imperial&APPID=d392b1ed567c03e70abc1f926b168fcf")
    Call<WeatherResponse> getForecast();
}
