package com.example.jakewilson.weather.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jakewilson.weather.R;

/**
 * Created by jakewilson on 9/5/15.
 */
public class WeatherFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    public static final String ARG_SECTION_NUMBER = "section_number";
    public static final String DAY_FORECAST = "day";

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */

    private int section;
    private int currentTemp;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        section = args.getInt(ARG_SECTION_NUMBER);
        currentTemp = args.getInt(DAY_FORECAST);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_weather, container, false);
        //  http://openweathermap.org/img/w/01d.png
        ((TextView) rootView.findViewById(R.id.section_label)).setText(String.valueOf(section));
        ((TextView) rootView.findViewById(R.id.current_temp)).setText(String.valueOf(String.valueOf(currentTemp) + '\u2109'));
        return rootView;
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("dummy", true);
    }
}
