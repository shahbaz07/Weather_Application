package com.weatherapp.server.interfaces;

import com.weatherapp.localstorage.interfaces.LocalStorageType;

/**
 * Created by shahbaz on 2/15/2016.
 */
public interface WeatherAPIInterface {

    void getWeatherInfoByCityName(String cityName, LocalStorageType type, WeatherAPIResponseCallBack callBack);
}
