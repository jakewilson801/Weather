package com.example.jakewilson.weather.models;

import java.util.ArrayList;

/**
 * Created by jakewilson on 9/2/15.
 */
public class List {
    private Long dt;
    private Temp temp;
    private Double pressure;
    private Long humidity;
    private java.util.List<Weather> weather = new ArrayList<>();
    private Double speed;
    private Long deg;
    private Long clouds;
    private Double rain;

    /**
     *
     * @return
     * The dt
     */
    public Long getDt() {
        return dt;
    }

    /**
     *
     * @param dt
     * The dt
     */
    public void setDt(Long dt) {
        this.dt = dt;
    }

    /**
     *
     * @return
     * The temp
     */
    public Temp getTemp() {
        return temp;
    }

    /**
     *
     * @param temp
     * The temp
     */
    public void setTemp(Temp temp) {
        this.temp = temp;
    }

    /**
     *
     * @return
     * The pressure
     */
    public Double getPressure() {
        return pressure;
    }

    /**
     *
     * @param pressure
     * The pressure
     */
    public void setPressure(Double pressure) {
        this.pressure = pressure;
    }

    /**
     *
     * @return
     * The humidity
     */
    public Long getHumidity() {
        return humidity;
    }

    /**
     *
     * @param humidity
     * The humidity
     */
    public void setHumidity(Long humidity) {
        this.humidity = humidity;
    }

    /**
     *
     * @return
     * The weather
     */
    public java.util.List<Weather> getWeather() {
        return weather;
    }

    /**
     *
     * @param weather
     * The weather
     */
    public void setWeather(java.util.List<Weather> weather) {
        this.weather = weather;
    }

    /**
     *
     * @return
     * The speed
     */
    public Double getSpeed() {
        return speed;
    }

    /**
     *
     * @param speed
     * The speed
     */
    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    /**
     *
     * @return
     * The deg
     */
    public Long getDeg() {
        return deg;
    }

    /**
     *
     * @param deg
     * The deg
     */
    public void setDeg(Long deg) {
        this.deg = deg;
    }

    /**
     *
     * @return
     * The clouds
     */
    public Long getClouds() {
        return clouds;
    }

    /**
     *
     * @param clouds
     * The clouds
     */
    public void setClouds(Long clouds) {
        this.clouds = clouds;
    }

    /**
     *
     * @return
     * The rain
     */
    public Double getRain() {
        return rain;
    }

    /**
     *
     * @param rain
     * The rain
     */
    public void setRain(Double rain) {
        this.rain = rain;
    }

}
