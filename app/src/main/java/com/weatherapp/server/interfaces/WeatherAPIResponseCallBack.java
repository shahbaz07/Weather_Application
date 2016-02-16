package com.weatherapp.server.interfaces;

import com.weatherapp.server.models.WeatherInfo;

/**
 * Created by shahbaz on 2/15/2016.
 */
public interface WeatherAPIResponseCallBack {

    void onRespone(WeatherInfo weatherInfo);
}
