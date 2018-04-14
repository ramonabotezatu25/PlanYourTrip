package com.example.ramona.planyourtrip;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import com.example.ramona.planyourtrip.Weather.Weather;

public class ExploreCity extends AppCompatActivity {

    TextView cityField, detailsField, currentTemperatureField, humidity_field, pressure_field, weatherIcon, updatedField;
    //de unde apelezi clasa asta?
    Typeface weatherFont;
    String latitudine;
    String longitudine ;
    Bundle bu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore_city);
        bu = getIntent().getExtras();
        final String numeOras = bu.getString("name");
        weatherFont = Typeface.createFromAsset(getAssets(), "font/font/weathericons-regular-webfont.ttf");

        cityField = (TextView)findViewById(R.id.explore_city_field);
        updatedField = (TextView)findViewById(R.id.explore_updated_field);
        detailsField = (TextView)findViewById(R.id.explore_details_field);
        currentTemperatureField = (TextView)findViewById(R.id.explore_current_temperature_field);
        humidity_field = (TextView)findViewById(R.id.explore_humidity_field);
        pressure_field = (TextView)findViewById(R.id.explore_pressure_field);
        weatherIcon = (TextView)findViewById(R.id.explore_weather_icon);
        weatherIcon.setTypeface(weatherFont);

        Weather.placeIdTask asyncTask =new Weather.placeIdTask(new Weather.AsyncResponse() {
            public void processFinish(String weather_city, String weather_description, String weather_temperature, String weather_humidity, String weather_pressure, String weather_updatedOn, String weather_iconText, String sun_rise) {

                cityField.setText(numeOras);
                updatedField.setText(weather_updatedOn);
                detailsField.setText(weather_description);
                currentTemperatureField.setText(weather_temperature);
                humidity_field.setText("Humidity: "+weather_humidity);
                pressure_field.setText("Pressure: "+weather_pressure);
                weatherIcon.setText(Html.fromHtml(weather_iconText));

            }
        });

        if(bu != null){
            latitudine = bu.getString("lat");
            longitudine = bu.getString("long");
            asyncTask.execute(latitudine,longitudine);
        }
    }
}
