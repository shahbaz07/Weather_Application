package com.weatherapp.localstorage.interfaces;

import com.weatherapp.server.models.WeatherInfo;

/**
 * Created by shahbaz on 2/16/2016.
 */
public interface LocalStorageInterface {

    String getWeatherInfo(String cityName);
    void saveWeatherInfo(String cityName, String jsonResponse);
}
