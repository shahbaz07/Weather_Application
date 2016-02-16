package com.weatherapp.localstorage.implementations;

import android.content.Context;
import android.content.SharedPreferences;

import com.weatherapp.Constants;
import com.weatherapp.localstorage.interfaces.LocalStorageInterface;

/**
 * Created by shahbaz on 2/16/2016.
 */
class SharedPreferenceStorage implements LocalStorageInterface {

    private static final String PREFS_NAME = "weather_info";
    private static final String WEATHER_INFO_TIME_POSTFIX = "_time";
    private Context context;
    private SharedPreferences prefs = null;

    public SharedPreferenceStorage(Context context)
    {
        this.context = context;
        prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    @Override
    public String getWeatherInfo(String cityName) {
        String info = "";
        long time = prefs.getLong(cityName + WEATHER_INFO_TIME_POSTFIX, 0);
        if(time > 0)
        {
            if(System.currentTimeMillis() - Constants.WEATHER_INFO_CACHE_TIME <= time)
            {
                info = prefs.getString(cityName, "");
            }
        }
        return info;
    }

    @Override
    public void saveWeatherInfo(String cityName, String jsonResponse) {

        SharedPreferences.Editor edit = prefs.edit();
        edit.putString(cityName, jsonResponse);
        edit.putLong(cityName + WEATHER_INFO_TIME_POSTFIX, System.currentTimeMillis());
        edit.commit();
    }
}
