package com.weatherapp.localstorage.interfaces;

import com.weatherapp.server.models.WeatherInfo;

/**
 * Created by shahbaz on 2/15/2016.
 */
public interface LocalStorageResponseCallBack {

    void onRespone(WeatherInfo weatherInfo);
}
