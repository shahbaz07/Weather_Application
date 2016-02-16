package com.weatherapp.server.models;

import java.io.Serializable;

/**
 * Created by shahbaz on 2/15/2016.
 */
class Main implements Serializable {

    private double temp;
    private double pressure;
    private double humidity;
    private double temp_min;
    private double temp_max;

    public Main()
    {

    }

    @Override
    public String toString()
    {
        StringBuilder data = new StringBuilder();
        data.append("\n\nMain Info");
        data.append("\n\t\tTemprature: " + temp);
        data.append("\n\t\tPressure: " + pressure);
        data.append("\n\t\tHumidity: " + humidity);
        data.append("\n\t\tMinimum Temprature: " + temp_min);
        data.append("\n\t\tMaximum Temprature: " + temp_max);
        return data.toString();
    }
}
