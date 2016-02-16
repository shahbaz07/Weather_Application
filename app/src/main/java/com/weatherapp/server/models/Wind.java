package com.weatherapp.server.models;

import java.io.Serializable;

/**
 * Created by shahbaz on 2/16/2016.
 */
class Wind implements Serializable {

    private double speed;
    private double deg;

    public Wind()
    {

    }

    @Override
    public String toString()
    {
        StringBuilder data = new StringBuilder();
        data.append("\n\nWind Info");
        data.append("\n\t\tSpeed: " + speed);
        data.append("\n\t\tdeg: " + deg);
        return data.toString();
    }
}
