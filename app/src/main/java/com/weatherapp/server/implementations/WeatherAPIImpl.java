package com.weatherapp.server.implementations;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.weatherapp.Constants;
import com.weatherapp.localstorage.implementations.LocalStorageFactory;
import com.weatherapp.localstorage.interfaces.LocalStorageType;
import com.weatherapp.server.interfaces.WeatherAPIInterface;
import com.weatherapp.server.interfaces.WeatherAPIResponseCallBack;
import com.weatherapp.server.models.WeatherInfo;

import org.json.JSONObject;

/**
 * Created by shahbaz on 2/15/2016.
 */
 class WeatherAPIImpl implements WeatherAPIInterface {

    private Context context = null;
    private LocalStorageType type = null;
    private WeatherAPIResponseCallBack responseCallBack = null;

    public WeatherAPIImpl(Context context)
    {
        this.context = context;
    }

    @Override
    public void getWeatherInfoByCityName(final String cityName, LocalStorageType type, WeatherAPIResponseCallBack callBack) {
        responseCallBack = callBack;
        this.type = type;
        String info = LocalStorageFactory.getLocalStorageManager(context, type).getWeatherInfo(cityName);
        if(TextUtils.isEmpty(info)) {
            String url = String.format(Constants.WEATER_API_URL, cityName);

            JsonObjectRequest jsonRequest = new JsonObjectRequest
                    (Request.Method.GET, url, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                                new ProcessResponseTask(cityName, response.toString()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                            } else {
                                new ProcessResponseTask(cityName, response.toString()).execute();
                            }
                        }
                    }, new Response.ErrorListener() {

                        @Override
                        public void onErrorResponse(VolleyError error) {
                            if (responseCallBack != null) {
                                responseCallBack.onRespone(null);
                            }
                        }
                    });

            Volley.newRequestQueue(context).add(jsonRequest);
        }
        else
        {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                new ProcessResponseTask(cityName, info).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            } else {
                new ProcessResponseTask(cityName, info).execute();
            }
        }
    }

    private class ProcessResponseTask extends AsyncTask<Void, Void, WeatherInfo>
    {
        private String response;
        private String cityName;

        public ProcessResponseTask(String cityName, String response)
        {
            this.cityName = cityName;
            this.response = response;
        }

        @Override
        protected WeatherInfo doInBackground(Void... params) {
            WeatherInfo weatherInfo = null;
            try {
                LocalStorageFactory.getLocalStorageManager(context, type).saveWeatherInfo(cityName, response);
                weatherInfo = new Gson().fromJson(response, WeatherInfo.class);
            }
            catch (Exception ex)
            {
                weatherInfo = null;
            }
            return weatherInfo;
        }

        @Override
        protected void onPostExecute(WeatherInfo weatherInfo) {
            if (responseCallBack != null)
            {
                responseCallBack.onRespone(weatherInfo);
            }
        }
    }
}
