package com.weatherapp.server.models;

import java.io.Serializable;

/**
 * Created by shahbaz on 2/15/2016.
 */
class Weather implements Serializable {

    private long id;
    private String main;
    private String description;
    private String icon;
    public Weather()
    {

    }

    @Override
    public String toString()
    {
        StringBuilder data = new StringBuilder();
        data.append("\n\nInfo");
        data.append("\n\t\tMain: " + main);
        data.append("\n\t\tDescription: " + description);
        return data.toString();
    }
}
