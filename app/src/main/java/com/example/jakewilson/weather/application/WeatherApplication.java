package com.example.jakewilson.weather.application;

import android.app.Application;

import com.example.jakewilson.weather.clients.WeatherApi;
import com.example.jakewilson.weather.events.BusProvider;
import com.example.jakewilson.weather.services.WeatherService;
import com.squareup.otto.Bus;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by jakewilson on 9/5/15.
 */
public class WeatherApplication extends Application {
    private WeatherService mWeatherService;
    private Bus mBus = BusProvider.getInstance();

    @Override
    public void onCreate() {
        super.onCreate();
        mWeatherService = new WeatherService(buildApi(), mBus);
        mBus.register(mWeatherService);
        mBus.register(this);
    }

    private WeatherApi buildApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(WeatherApi.class);
    }

}
