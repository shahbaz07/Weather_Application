package com.weatherapp.server.models;

import java.io.Serializable;

/**
 * Created by shahbaz on 2/15/2016.
 */
class Coord implements Serializable {

    private double lon;
    private double lat;

    public Coord()
    {

    }

    @Override
    public String toString()
    {
        StringBuilder data = new StringBuilder();
        data.append("\n\nCoord Info");
        data.append("\n\t\tLongitude: " + lon);
        data.append("\n\t\tLatitude: " + lat);
        return data.toString();
    }
}
