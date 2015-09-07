package com.example.jakewilson.weather.clients;

import com.example.jakewilson.weather.models.WeatherResponse;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by jakewilson on 9/2/15.
 */
public interface WeatherApi {
    //lat=40.7478475&lon=-111.8865232
    //lat={lat}&lon={lon}&units={units}
    @GET("/data/2.5/forecast/daily?APPID=d392b1ed567c03e70abc1f926b168fcf")
    Call<WeatherResponse> getForecast(@Query("lat") String lat, @Query("lon") String lon, @Query("units") String units);
}
