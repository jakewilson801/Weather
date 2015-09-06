package com.example.jakewilson.weather.services;

import android.util.Log;

import com.example.jakewilson.weather.clients.WeatherApi;
import com.example.jakewilson.weather.events.ApiErrorEvent;
import com.example.jakewilson.weather.events.LoadWeatherEvent;
import com.example.jakewilson.weather.events.WeatherLoadedEvent;
import com.example.jakewilson.weather.models.List;
import com.example.jakewilson.weather.models.WeatherResponse;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import retrofit.Callback;
import retrofit.Response;

/**
 * Created by jakewilson on 9/5/15.
 */
public class WeatherService {
    private WeatherApi mApi;
    private Bus mBus;
    public WeatherService(WeatherApi api, Bus bus){
        mApi = api;
        mBus = bus;
    }

    @Subscribe
    public void onLoadWeather(LoadWeatherEvent event){
        mApi.getForecast().enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Response<WeatherResponse> response) {
                for (List l : response.body().getList()) {
                    // Log.d("QA", new DateTime(l.getDt() * 1000).toString());
                    Log.d("QA", String.valueOf(l.getTemp().getDay()));
                }
                mBus.post(new WeatherLoadedEvent(response.body().getList()));
            }

            @Override
            public void onFailure(Throwable t) {
                mBus.post(new ApiErrorEvent(t));
            }
        });
    }

}
