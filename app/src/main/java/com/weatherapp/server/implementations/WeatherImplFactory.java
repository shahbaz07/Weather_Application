package com.weatherapp.server.implementations;

import android.content.Context;

import com.weatherapp.server.interfaces.WeatherAPIInterface;

/**
 * Created by shahbaz on 2/16/2016.
 */
public class WeatherImplFactory {

    private static WeatherAPIInterface weatherAPI = null;

    public static WeatherAPIInterface getWeatherManager(Context context)
    {
        if (weatherAPI == null)
        {
            weatherAPI = new WeatherAPIImpl(context);
        }
        return weatherAPI;
    }

    public static void destroy()
    {
        weatherAPI = null;
    }
}
