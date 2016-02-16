package com.weatherapp.server.models;

import java.io.Serializable;

/**
 * Created by shahbaz on 2/16/2016.
 */
class Clouds implements Serializable {

    private int all;

    public Clouds()
    {

    }

    @Override
    public String toString()
    {
        StringBuilder data = new StringBuilder();
        data.append("\n\nClouds Info");
        data.append("\n\t\tAll: " + all);
        return data.toString();
    }
}
