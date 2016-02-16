package com.weatherapp.localstorage.implementations;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import com.weatherapp.Constants;
import com.weatherapp.localstorage.interfaces.LocalStorageInterface;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Date;

/**
 * Created by shahbaz on 2/16/2016.
 */
class FileStorage implements LocalStorageInterface {

    private Context context;

    public FileStorage(Context context)
    {
        this.context = context;
    }

    @Override
    public String getWeatherInfo(String cityName) {
        StringBuilder info = new StringBuilder();
        File file = new File(context.getFilesDir().getAbsolutePath() + "/" + cityName);
        if(file.exists() && System.currentTimeMillis() - Constants.WEATHER_INFO_CACHE_TIME < file.lastModified()) {
            try {
                FileInputStream fin = context.openFileInput(cityName);
                int c;

                while ((c = fin.read()) != -1) {
                    info.append(Character.toString((char) c));
                }
                fin.close();
            } catch (Exception e) {
            }
        }
        return info.toString();
    }

    @Override
    public void saveWeatherInfo(String cityName, String jsonResponse) {
        try {
            FileOutputStream fOut = context.openFileOutput(cityName, Context.MODE_PRIVATE);
            fOut.write(jsonResponse.getBytes());
            fOut.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
