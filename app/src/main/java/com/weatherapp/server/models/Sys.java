package com.weatherapp.server.models;

import java.io.Serializable;

/**
 * Created by shahbaz on 2/16/2016.
 */
class Sys implements Serializable {

    private int type;
    private long id;
    private double message;
    private String country;
    private long sunrise;
    private long sunset;

    public Sys()
    {

    }

    @Override
    public String toString()
    {
        StringBuilder data = new StringBuilder();
        data.append("\n\nSys Info");
        data.append("\n\t\tType: " + type);
        data.append("\n\t\tMessage: " + message);
        data.append("\n\t\tCountry: " + country);
        data.append("\n\t\tSunrise: " + sunrise);
        data.append("\n\t\tSunset: " + sunset);
        return data.toString();
    }
}
