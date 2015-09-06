package com.example.jakewilson.weather.adapters;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.jakewilson.weather.fragments.WeatherFragment;
import com.example.jakewilson.weather.models.List;

import java.util.ArrayList;

/**
 * Created by jakewilson on 9/5/15.
 */
public class WeatherAdapter extends FragmentPagerAdapter{
    private java.util.List<List> mForecast;

    public WeatherAdapter(FragmentManager fm, java.util.List<List> forecast){
        super(fm);
        this.mForecast = forecast;
    }

    public WeatherAdapter(FragmentManager fm){
        super(fm);
        this.mForecast = new ArrayList<>();
    }


    @Override
    public Fragment getItem(int position) {
        if(mForecast.size() > 0) {
            Fragment w = new WeatherFragment();
            Bundle args = new Bundle();
            args.putInt(WeatherFragment.ARG_SECTION_NUMBER, position);
            // args.putInt(WeatherFragment.DAY_FORECAST, mForecast.get(position).getTemp().getDay().intValue());
            args.putInt(WeatherFragment.DAY_FORECAST, mForecast.get(position).getTemp().getDay().intValue());
            w.setArguments(args);
            return w;
        } else {
            return new WeatherFragment();
        }
    }

    @Override
    public int getCount() {
        return mForecast.size();
    }

    public void setForecast(java.util.List<List> forecast){
        this.mForecast = forecast;
        this.notifyDataSetChanged();
    }
}
