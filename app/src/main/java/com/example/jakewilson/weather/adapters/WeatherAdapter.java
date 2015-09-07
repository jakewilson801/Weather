package com.example.jakewilson.weather.adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.jakewilson.weather.fragments.WeatherFragment;
import com.example.jakewilson.weather.models.WeatherResponse;

/**
 * Created by jakewilson on 9/5/15.
 */
public class WeatherAdapter extends FragmentPagerAdapter{
    private WeatherResponse mForecast;

    public WeatherAdapter(FragmentManager fm, WeatherResponse forecast){
        super(fm);
        this.mForecast = forecast;
    }

    public WeatherAdapter(FragmentManager fm){
        super(fm);
        this.mForecast = new WeatherResponse();
    }


    @Override
    public Fragment getItem(int position) {
        if(mForecast != null && mForecast.getList().size() > 0) {
            Fragment w = new WeatherFragment();
            Bundle args = new Bundle();
            args.putInt(WeatherFragment.ARG_SECTION_NUMBER, position);
            args.putString(WeatherFragment.CITY, mForecast.getCity().getName());
            args.putInt(WeatherFragment.DAY_FORECAST, mForecast.getList().get(position).getTemp().getDay().intValue());
            args.putLong(WeatherFragment.DATE, mForecast.getList().get(position).getDt());
            args.putString(WeatherFragment.ICON, mForecast.getList().get(position).getWeather().get(0).getIcon());
            args.putString(WeatherFragment.DESCRIPTION, mForecast.getList().get(position).getWeather().get(0).getDescription());
            args.putString(WeatherFragment.RAIN, String.valueOf(mForecast.getList().get(position).getClouds())+ "%");
            args.putString(WeatherFragment.WIND, String.valueOf(mForecast.getList().get(position).getSpeed()) + " mph");
            args.putString(WeatherFragment.LOW, String.valueOf(mForecast.getList().get(position).getTemp().getMin().intValue()));
            args.putString(WeatherFragment.HIGH, String.valueOf(mForecast.getList().get(position).getTemp().getMax().intValue()));
            w.setArguments(args);
            return w;
        } else {
            return new WeatherFragment();
        }
    }

    @Override
    public int getCount() {
        return mForecast.getList().size();
    }

    public void setForecast(WeatherResponse forecast){
        this.mForecast = forecast;
        this.notifyDataSetChanged();
    }
}
