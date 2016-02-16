package com.weatherapp.ui.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.weatherapp.R;
import com.weatherapp.localstorage.interfaces.LocalStorageType;
import com.weatherapp.server.implementations.WeatherImplFactory;
import com.weatherapp.server.interfaces.WeatherAPIResponseCallBack;
import com.weatherapp.server.models.WeatherInfo;
import com.weatherapp.utils.Util;

import java.util.Locale;

/**
 * Created by shahbaz on 2/15/2016.
 */
public class WeatherAcitivity extends Activity implements View.OnClickListener {

    private EditText txtCityName = null;
    private Button btnGetWeatherInfo = null;
    private TextView lblWeatherInfo = null;
    private ProgressDialog loading = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        initViews();
        initListener();
    }

    @Override
    protected void onDestroy() {
        if(loading != null)
        {
            loading.cancel();
        }
        super.onDestroy();
    }

    private void initViews()
    {
        txtCityName = (EditText)findViewById(R.id.txtCityName);
        btnGetWeatherInfo = (Button)findViewById(R.id.btnGetWeatherInfo);
        lblWeatherInfo = (TextView)findViewById(R.id.lblWeatherInfo);

        loading = new ProgressDialog(this);
        loading.setMessage(getString(R.string.please_wait));
        loading.setCancelable(false);
    }

    private void initListener()
    {
        btnGetWeatherInfo.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btnGetWeatherInfo:
                getWeatherInfo();
                break;
        }
    }

    private void getWeatherInfo()
    {
        String cityName = txtCityName.getText().toString().trim();
        if(TextUtils.isEmpty(cityName))
        {
            showMessage(getString(R.string.city_name_required));
        }
        else {
            loading.show();
            Util.hideKeyBoard(WeatherAcitivity.this, txtCityName);
            WeatherImplFactory.getWeatherManager(WeatherAcitivity.this).getWeatherInfoByCityName(cityName.toLowerCase(Locale.ENGLISH)
                    , LocalStorageType.DATABAE, callBack);
        }
    }

    private WeatherAPIResponseCallBack callBack = new WeatherAPIResponseCallBack() {
        @Override
        public void onRespone(final WeatherInfo weatherInfo) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(weatherInfo != null)
                    {
                        lblWeatherInfo.setText(weatherInfo.toString());
                    }
                    else
                    {
                        lblWeatherInfo.setText(getString(R.string.weather_info_error));
                    }
                    if(loading != null)
                    {
                        loading.cancel();
                    }
                }
            });
        }
    };

    private void showMessage(String message)
    {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
