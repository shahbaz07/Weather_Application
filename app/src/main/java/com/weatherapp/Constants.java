package com.weatherapp;

/**
 * Created by shahbaz on 2/15/2016.
 */
public interface Constants {

    String WEATER_API_URL = "http://api.openweathermap.org/data/2.5/weather?q=%s&appid=44db6a862fba0b067b1930da0d769e98";

    int WEATHER_INFO_CACHE_TIME = 60000; //In millisecond
}
