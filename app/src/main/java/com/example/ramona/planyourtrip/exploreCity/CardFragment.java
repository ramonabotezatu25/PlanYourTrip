package com.example.ramona.planyourtrip.exploreCity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.text.Html;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import static com.example.ramona.planyourtrip.GmailSender.Constante.assetManager;
import static com.example.ramona.planyourtrip.GmailSender.Constante.locatiiList;
import static com.example.ramona.planyourtrip.GmailSender.Constante.numeOras;
import static com.example.ramona.planyourtrip.GmailSender.Constante.latitudine;
import static com.example.ramona.planyourtrip.GmailSender.Constante.longitudine;
import static com.example.ramona.planyourtrip.GmailSender.Constante.storyList;

import com.example.ramona.planyourtrip.AddStory;
import com.example.ramona.planyourtrip.Flight;
import com.example.ramona.planyourtrip.R;
import com.example.ramona.planyourtrip.Util.Locatii;
import com.example.ramona.planyourtrip.Weather.Weather;
import com.example.ramona.planyourtrip.stories.Story;


public class CardFragment extends Fragment {
    private CardView cardView;
    private View layoutWheter;
    private View layoutCardView;
    private String descriereOras;
    private String restaurante;
    private String topAtractiiTuristice;
    private String activitati;
    //whether
    TextView cityField, detailsField, currentTemperatureField, humidity_field, pressure_field, weatherIcon, updatedField;
    Typeface weatherFont;


    public static Fragment getInstance(int position) {
        CardFragment f = new CardFragment();
        Bundle args = new Bundle();
        args.putInt("position", position);
        f.setArguments(args);

        return f;
    }

    @SuppressLint("DefaultLocale")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.item_viewpager, container, false);
        getWhether(view);

        final Integer position=getArguments().getInt("position");
        cardView = (CardView) view.findViewById(R.id.cardView);
        cardView.setMaxCardElevation(cardView.getCardElevation() * CardAdapter.MAX_ELEVATION_FACTOR);

        TextView title = (TextView) view.findViewById(R.id.title);
        TextView descieri = (TextView) view.findViewById(R.id.desciere_explore_my_city);
        Button button = (Button)view.findViewById(R.id.button);
        layoutCardView=(View) view.findViewById(R.id.cardView_layout);
        layoutWheter=(View) view.findViewById(R.id.whether_layout);

        for(Locatii l : locatiiList){
            if(l.getNume().equals(numeOras)){
                descriereOras=l.getDescriere();
                restaurante= l.getRestaurante();
                topAtractiiTuristice=l.getAtractii();
                activitati=l.getActivitati();
            }
        }
        if(position==0){
            //vez ofertele
            title.setText(getResources().getString(R.string.zboruri));
            descieri.setText(getResources().getString(R.string.biletZbor));
            button.setVisibility(View.VISIBLE);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent seeFlights= new Intent(getActivity(), Flight.class);
                    startActivity(seeFlights);
                }
            });
        }
        if(position==1){
            title.setText(getResources().getString(R.string.descrierereOras));
            descieri.setText(descriereOras);
        }
        if(position==2){
            title.setText(getResources().getString(R.string.obiective));
            descieri.setText(topAtractiiTuristice);
        }
        if(position==3){
            title.setText(getResources().getString(R.string.restaurante));
            descieri.setText(restaurante);
        }
        if(position==4){
            title.setText(getResources().getString(R.string.activitati));
            descieri.setText(activitati);
        }
        if(position==5){
            ///whether
            layoutWheter.setVisibility(View.VISIBLE);
            layoutCardView.setVisibility(View.INVISIBLE);
        }

        if(position==6) {
            title.setText(getResources().getString(R.string.veziPovesti));
            descieri.setText(getResources().getString(R.string.veziPovesti));
            button.setVisibility(View.VISIBLE);
            button.setBackgroundResource(R.drawable.abudhabi);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (storyList.size() > 0) {
                        Intent seeStory = new Intent(getActivity(), Story.class);
                        startActivity(seeStory);
                    } else {
                        Toast.makeText(getContext(), "Is no story to show at this city!Tell us one!", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }

        if(position==7){
                title.setText(getResources().getString(R.string.scriePovesti));
                descieri.setText(getResources().getString(R.string.scriePovesti));
                button.setVisibility(View.VISIBLE);
                button.setBackgroundResource(R.drawable.add_story_bg1);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent addStory= new Intent(getActivity(), AddStory.class);
                        startActivity(addStory);
                    }
                });
        }



        return view;
    }

    public CardView getCardView() {
        return cardView;
    }

    public void getWhether(View view){
        //iau informatiile din fosta activitate
        weatherFont = Typeface.createFromAsset(assetManager, "font/font/weathericons-regular-webfont.ttf");

        cityField = (TextView) view.findViewById(R.id.explore_city_field);
        updatedField = (TextView) view.findViewById(R.id.explore_updated_field);
        detailsField = (TextView) view.findViewById(R.id.explore_details_field);
        currentTemperatureField =(TextView) view.findViewById(R.id.explore_current_temperature_field);
        humidity_field = (TextView) view.findViewById(R.id.explore_humidity_field);
        pressure_field = (TextView) view.findViewById(R.id.explore_pressure_field);
        weatherIcon = (TextView) view.findViewById(R.id.explore_weather_icon);
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

        if (latitudine != null && longitudine!=null) {
            asyncTask.execute(latitudine, longitudine);
        }

    }
}
