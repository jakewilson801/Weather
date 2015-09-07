package com.example.jakewilson.weather.fragments;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jakewilson.weather.R;

import org.joda.time.DateTime;

import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by jakewilson on 9/5/15.
 */
public class WeatherFragment extends Fragment {

    public static final String ARG_SECTION_NUMBER = "section_number";
    public static final String DAY_FORECAST = "day";
    public static final String CITY = "city";
    public static final String DATE = "date";
    public static final String ICON = "icon";
    public static final String DESCRIPTION = "description";
    public static final String RAIN = "rain";
    public static final String HIGH = "high";
    public static final String LOW = "low";
    public static final String WIND = "wind";

    private int section;
    private int currentTemp;
    private String city;
    private long date;
    private String icon;
    private DateTime dateTime;
    private String description;
    private String rain;
    private String high;
    private String low;
    private String wind;

    @Bind(R.id.city_label) TextView cityView;
    @Bind(R.id.current_temp) TextView currentTempView;
    @Bind(R.id.date) TextView dateView;
    @Bind(R.id.weather_icon) ImageView weatherIcon;
    @Bind(R.id.description) TextView descriptionView;
    @Bind(R.id.chanceOfRain) TextView rainView;
    @Bind(R.id.high) TextView highView;
    @Bind(R.id.low) TextView lowView;
    @Bind(R.id.wind) TextView windView;

    public Drawable getDrawable(int id){
        return getActivity().getResources().getDrawable(id);
    }

    public Drawable mapIcon(String key) {
        HashMap<String, Drawable> iconMap = new HashMap<>();
        iconMap.put("01d", getDrawable(R.drawable.cleard));
        iconMap.put("01n", getDrawable(R.drawable.clearn));
        iconMap.put("02n", getDrawable(R.drawable.clouds));
        iconMap.put("02d", getDrawable(R.drawable.clouds));
        iconMap.put("03d", getDrawable(R.drawable.clouds));
        iconMap.put("03n", getDrawable(R.drawable.clouds));
        iconMap.put("04d", getDrawable(R.drawable.clouds));
        iconMap.put("04n", getDrawable(R.drawable.clouds));
        iconMap.put("09d", getDrawable(R.drawable.showers));
        iconMap.put("09n", getDrawable(R.drawable.showers));
        iconMap.put("10d", getDrawable(R.drawable.raind));
        iconMap.put("10n", getDrawable(R.drawable.rainn));
        iconMap.put("11d", getDrawable(R.drawable.thunderd));
        iconMap.put("11n", getDrawable(R.drawable.thundern));
        iconMap.put("13d", getDrawable(R.drawable.snow));
        iconMap.put("13n", getDrawable(R.drawable.snow));
        iconMap.put("50d", getDrawable(R.drawable.mist));
        iconMap.put("50n", getDrawable(R.drawable.mist));
        try {
           return iconMap.get(key);
        } catch (Exception e){
            return getDrawable(R.drawable.cleard);
        }
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        section = args.getInt(ARG_SECTION_NUMBER);
        currentTemp = args.getInt(DAY_FORECAST);
        city = args.getString(CITY);
        date = args.getLong(DATE);
        icon = args.getString(ICON);
        dateTime = new DateTime(date * 1000);
        description = args.getString(DESCRIPTION);
        rain = args.getString(RAIN);
        wind = args.getString(WIND);
        low = args.getString(LOW);
        high = args.getString(HIGH);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_weather, container, false);
        ButterKnife.bind(this, rootView);
        cityView.setText(city);
        currentTempView.setText(String.valueOf(String.valueOf(currentTemp) + '\u2109'));
        dateView.setText(String.valueOf(String.format("%s %s/%s", dateTime.dayOfWeek().getAsText(), dateTime.monthOfYear().get(), dateTime.dayOfMonth().getAsText())));
        weatherIcon.setImageDrawable(mapIcon(icon));
        descriptionView.setText(description);
        rainView.setText(String.format("%s %s", getResources().getString(R.string.chance_of_rain), rain));
        highView.setText(String.format("%s %s", getResources().getString(R.string.high), high));
        lowView.setText(String.format("%s %s", getResources().getString(R.string.low),low));
        windView.setText(String.format("%s %s" , getResources().getString(R.string.wind),wind));
        return rootView;
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("dummy", true);
    }
}
