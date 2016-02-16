package com.weatherapp.localstorage.implementations;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.weatherapp.Constants;
import com.weatherapp.localstorage.interfaces.LocalStorageInterface;

import java.sql.SQLException;

/**
 * Created by shahbaz on 2/16/2016.
 */
class DatabaseStorage implements LocalStorageInterface {

    private Context context;
    private DatabaseHelper dbHelper;


    public DatabaseStorage(Context context)
    {
        this.context = context;
        dbHelper = new DatabaseHelper(context);
    }

    @Override
    public String getWeatherInfo(String cityName) {
        return dbHelper.getWeatherInfo(cityName);
    }

    @Override
    public void saveWeatherInfo(String cityName, String jsonResponse) {
        try {
            dbHelper.insertOrUpdateWeatherInfo(cityName, jsonResponse);
        }
        catch (Exception ex)
        {

        }
    }

    public class DatabaseHelper extends SQLiteOpenHelper {

        public static final String TABLE_WEATHER_INFO = "weather_info";
        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_CITY_NAME = "city_name";
        public static final String COLUMN_INFO = "info";
        public static final String COLUMN_UPDATE_ON = "update_on";

        private static final String DATABASE_NAME = "weatherinfo.db";
        private static final int DATABASE_VERSION = 1;

        private SQLiteDatabase database;

        // Database creation sql statement
        private static final String DATABASE_CREATE = "create table "
            + TABLE_WEATHER_INFO + "(" + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_CITY_NAME + " text not null, "
            + COLUMN_INFO + " text not null, "
            + COLUMN_UPDATE_ON + " integer not null);";

        private String[] FIELDS = new String[]
            {
                COLUMN_INFO,
                COLUMN_UPDATE_ON
            };

        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase database) {
            database.execSQL(DATABASE_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        }

        public void open() throws SQLException {
            database = getWritableDatabase();
        }

        public long insertOrUpdateWeatherInfo(String cityName, String info)
        {
            if(isWeatherInfoExist(cityName))
            {
              return updateWeatherInfo(cityName, info);
            }
            else
            {
                return insertWeatherInfo(cityName, info);
            }
        }

        private long insertWeatherInfo(String cityName, String info)
        {
            long returnVal = -1;
            try
            {
                open();
                ContentValues values = new ContentValues();
                values.put(COLUMN_CITY_NAME, cityName);
                values.put(COLUMN_INFO, info);
                values.put(COLUMN_UPDATE_ON, System.currentTimeMillis());
                returnVal = database.insert(TABLE_WEATHER_INFO, null, values);
                close();
            }
            catch (Exception ex)
            {

            }
            return returnVal;
        }

        private long updateWeatherInfo(String cityName, String info)
        {
            long returnVal = -1;
            try
            {
                open();
                ContentValues values = new ContentValues();
                values.put(COLUMN_CITY_NAME, cityName);
                values.put(COLUMN_INFO, info);
                values.put(COLUMN_UPDATE_ON, System.currentTimeMillis());
                returnVal = database.update(TABLE_WEATHER_INFO, values, COLUMN_CITY_NAME + "=?", new String[]{cityName});
                close();
            }
            catch (Exception ex)
            {

            }
            return returnVal;
        }

        public String getWeatherInfo(String cityName)
        {
            String info = "";
            try {
                open();
                Cursor cursor = database.query(TABLE_WEATHER_INFO,
                        FIELDS, COLUMN_CITY_NAME + "=?", new String[]{cityName},
                        null, null, null);
                if (cursor != null && cursor.moveToFirst()) {
                    long time = cursor.getLong(1);
                    if (System.currentTimeMillis() - Constants.WEATHER_INFO_CACHE_TIME < time) {
                        info = cursor.getString(0);
                    }
                }
                close();
            }
            catch (Exception ex)
            {

            }
            return info;
        }

        private boolean isWeatherInfoExist(String cityName)
        {
            boolean isExist = false;
            try {
                open();
                Cursor cursor = database.query(TABLE_WEATHER_INFO,
                        FIELDS, COLUMN_CITY_NAME + "=?", new String[]{cityName},
                        null, null, null);
                if (cursor != null) {
                    isExist = cursor.moveToFirst();
                }
                close();
            }
            catch (Exception ex)
            {

            }
            return isExist;
        }
    }
}
