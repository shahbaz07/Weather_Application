package com.weatherapp.server.models;

import java.io.Serializable;

/**
 * Created by shahbaz on 2/15/2016.
 */
public class WeatherInfo implements Serializable {

    private Coord coord;
    private Weather[] weather;
    private String base;
    private Main main;
    private long visibility;
    private Wind wind;
    private Clouds clouds;
    private long dt;
    private Sys sys;
    private long id;
    private String name;
    private int cod;

    public WeatherInfo()
    {

    }

    @Override
    public String toString()
    {
        StringBuilder data = new StringBuilder();
        data.append("Weather Info");
        data.append("\n\t\tName: " + name);
        data.append("\n\t\tBase: " + base);
        data.append("\n\t\tVisibility: " + visibility);
        data.append("\n\t\tCod: " + cod);
        if(coord != null)
        {
            data.append(coord.toString());
        }
        if(weather != null && weather.length > 0)
        {
            data.append(weather[0].toString());
        }
        if(main != null)
        {
            data.append(main.toString());
        }
        if(wind != null)
        {
            data.append(wind.toString());
        }
        if(clouds != null)
        {
            data.append(clouds.toString());
        }
        if(sys != null)
        {
            data.append(sys.toString());
        }
        return data.toString();
    }
}
