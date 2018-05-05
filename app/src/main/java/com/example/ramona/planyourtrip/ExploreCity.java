package com.example.ramona.planyourtrip;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ramona.planyourtrip.MultiLanguage.MultiLanguageHelper;
import com.example.ramona.planyourtrip.Util.Database.DatabaseOperation;
import com.example.ramona.planyourtrip.Util.Locatii;
import com.example.ramona.planyourtrip.Weather.Weather;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import io.paperdb.Paper;

public class ExploreCity extends AppCompatActivity {

    TextView cityField, detailsField, currentTemperatureField, humidity_field, pressure_field, weatherIcon, updatedField;
    Typeface weatherFont;
    String latitudine;
    String longitudine ;
    Bundle bu;
    String numeOrasPrimit;

    //setari de limba
    Context context;
    Resources resources;
    //
    TextView descrieretitlu;
    TextView obiectiveTitlu;
    TextView restauranteTitlu;
    TextView activitatiTilu;
    Button buttonVeziBilete;
    ImageView imageView;

    //database
    DatabaseOperation db =new DatabaseOperation();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore_city);
        bu = getIntent().getExtras();
        final String numeOras = bu.getString("name");
        weatherFont = Typeface.createFromAsset(getAssets(), "font/font/weathericons-regular-webfont.ttf");

        cityField = (TextView) findViewById(R.id.explore_city_field);
        updatedField = (TextView) findViewById(R.id.explore_updated_field);
        detailsField = (TextView) findViewById(R.id.explore_details_field);
        currentTemperatureField = (TextView) findViewById(R.id.explore_current_temperature_field);
        humidity_field = (TextView) findViewById(R.id.explore_humidity_field);
        pressure_field = (TextView) findViewById(R.id.explore_pressure_field);
        weatherIcon = (TextView) findViewById(R.id.explore_weather_icon);
        weatherIcon.setTypeface(weatherFont);

        Weather.placeIdTask asyncTask = new Weather.placeIdTask(new Weather.AsyncResponse() {
            public void processFinish(String weather_city, String weather_description, String weather_temperature, String weather_humidity, String weather_pressure, String weather_updatedOn, String weather_iconText, String sun_rise) {

                cityField.setText(numeOras);
                updatedField.setText(weather_updatedOn);
                detailsField.setText(weather_description);
                currentTemperatureField.setText(weather_temperature);
                humidity_field.setText("Humidity: " + weather_humidity);
                pressure_field.setText("Pressure: " + weather_pressure);
                weatherIcon.setText(Html.fromHtml(weather_iconText));

            }
        });

        if (bu != null) {
            latitudine = bu.getString("lat");
            longitudine = bu.getString("long");
            numeOrasPrimit = bu.getString("orasSelectat");
            asyncTask.execute(latitudine, longitudine);
        }
        //setari de multi lang
        allYouNeed();

        //setare imageView
        setImageViewExplore();


        //
        final Button seeFlight = (Button)findViewById(R.id.explore_button_vezi_bilete);
        seeFlight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                seeFlights();
            }
        });
    }

    private void allYouNeed() {

        //preiau toate view-urile care trebuie traduce din clasa
        descrieretitlu = (TextView) findViewById(R.id.explore_tv_descriere_titlu);
        obiectiveTitlu = (TextView) findViewById(R.id.explore_tv_obiective_titlu);
        restauranteTitlu = (TextView) findViewById(R.id.explore_tv_restaurante_titlu);
        activitatiTilu = (TextView) findViewById(R.id.explore_tv_activitati_titlu);
        buttonVeziBilete=(Button)findViewById(R.id.explore_button_vezi_bilete);
        //pun toate id-urile stringurilor de care am nevoie
        setAllTextOnActivity();
        //navigation view

    }
    private void setAllTextOnActivity() {
        //setari de limba
        context = getApplicationContext();
        context = MultiLanguageHelper.setLocale(context,(String) Paper.book().read("language"));
        resources = context.getResources();
        descrieretitlu.setText(resources.getString(R.string.descrierereOras));
        obiectiveTitlu.setText(resources.getString(R.string.obiective));
        restauranteTitlu.setText(resources.getString(R.string.restaurante));
        activitatiTilu.setText(resources.getString(R.string.activitati));
        buttonVeziBilete.setText(resources.getString(R.string.biletZbor));

    }

    private void setImageViewExplore(){
        //preia din baza orase
        String nume=numeOrasPrimit.replace(" ","");
        String link=db.getLinkLocatie(nume);

        if(link.isEmpty()){
            link="https://scontent.fotp3-3.fna.fbcdn.net/v/t1.0-9/30712461_587953308239947_3492033068502351872_n.jpg?_nc_cat=0&oh=b38e5409b120619bbd7bfde656c02080&oe=5B27EE83";
        }

        ImageView imageView=(ImageView)findViewById(R.id.explore_imageView1);
        URL newurl = null;
        try {
            newurl = new URL(link);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Bitmap mIcon_val = null;
        try {
            mIcon_val = BitmapFactory.decodeStream(newurl.openConnection() .getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        imageView.setImageBitmap(mIcon_val);
    }


    private void seeFlights(){
        Intent flight = new Intent(this,Flight.class);
        startActivity(flight);
    }
}
