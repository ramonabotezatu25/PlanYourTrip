package com.example.ramona.planyourtrip;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ramona.planyourtrip.Weather.WeatherMainActivity;

import java.util.ArrayList;
import java.util.List;

import static com.example.ramona.planyourtrip.Weather.GetCityLongLat.cityList;
import static com.example.ramona.planyourtrip.Weather.GetCityLongLat.getCityLatLong;
import static com.example.ramona.planyourtrip.Weather.GetCityLongLat.loadJSONFromAsset;

public class Home extends AppCompatActivity {
    ImageView imageView1;
    List<String> orase = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        orase.add("Madrid");
        loadJSONFromAsset(this);
        //setare imagine cardviw1 cu poza luata din resurse - merge
        imageView1 = (ImageView) findViewById(R.id.home_imageView1);
        String lowerCountryCode = "rome2";
        int id = getResources().getIdentifier(lowerCountryCode, "drawable", getPackageName());
        imageView1.setImageResource(id);
        metode();
    }
//nu in asta ba da scuze

    public void metode (){
        Button button = (Button)findViewById(R.id.home_btn1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String latLong = getCityLatLong(orase.get(0));
                if(!latLong.equals("")) {
                    String[] lL = latLong.split(";");
                    deschideExploreCity(lL[0],lL[1]);
                }
            }
        });
    }

    public void deschideExploreCity(String lat,String lon){
        //cam asta e.Pentru test. Apeleaza cu New York
        Intent exploreCity = new Intent(Home.this, ExploreCity.class);
        exploreCity.putExtra("lat",lat);
        exploreCity.putExtra("long",lon);
        startActivity(exploreCity);

    }
}
