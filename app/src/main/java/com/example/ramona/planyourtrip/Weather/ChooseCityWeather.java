package com.example.ramona.planyourtrip.Weather;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.ramona.planyourtrip.R;

import static com.example.ramona.planyourtrip.Weather.GetCityLongLat.cityList;
import static com.example.ramona.planyourtrip.Weather.GetCityLongLat.cityName;
import static com.example.ramona.planyourtrip.Weather.GetCityLongLat.loadJSONFromAsset;

public class ChooseCityWeather extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_city_weather);
        //preia din jsonul cu orase.
        loadJSONFromAsset(this);
        //pune in adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, cityName);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner citySpinner = (Spinner) findViewById(R.id.spinner);
        citySpinner.setAdapter(adapter);

        citySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                Intent weather = new Intent(getApplicationContext(),WeatherMainActivity.class);
                weather.putExtra("lat",String.valueOf(cityList.get(position).getLat()));
                weather.putExtra("long",String.valueOf(cityList.get(position).getLongitudine()));
                startActivity(weather);
            } // to close the onItemSelected
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });


        //search your city
        Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
