package com.example.jakewilson.weather.util;

import java.util.Locale;

/**
 * Created by jakewilson on 9/6/15.
 */
public class UnitLocale {
    public static UnitLocale Imperial = new UnitLocale();
    public static UnitLocale Metric = new UnitLocale();

    public static UnitLocale getDefault() {
        return getFrom(Locale.getDefault());
    }
    public static UnitLocale getFrom(Locale locale) {
        String countryCode = locale.getCountry();
        if ("US".equals(countryCode)) return Imperial; // USA
        if ("LR".equals(countryCode)) return Imperial; // liberia
        if ("MM".equals(countryCode)) return Imperial; // burma
        return Metric;
    }

    public static String imperialOrMetric(Locale locale) {
        String countryCode = locale.getCountry();
        if ("US".equals(countryCode)) return "imperial"; // USA
        if ("LR".equals(countryCode)) return "imperial"; // liberia
        if ("MM".equals(countryCode)) return "imperial"; // burma
        return "metric";
    }
}