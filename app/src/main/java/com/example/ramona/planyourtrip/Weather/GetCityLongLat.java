package com.example.ramona.planyourtrip.Weather;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Infrasoft Ciprian on 03-Feb-18.
 */

public class GetCityLongLat {

    public static List<City> cityList = new ArrayList<>();
    public static List<String> cityName = new ArrayList<>();
    public static final void loadJSONFromAsset(Context context) {
        String json = null;

        try {

            InputStream is = context.getAssets().open("city/vic.json");

            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            json = new String(buffer, "UTF-8");


        } catch (IOException ex) {
            ex.printStackTrace();
        }
        getCityFromJson(json);

    }

    private static void getCityFromJson (String json){
        try {
            JSONArray cityArray = new JSONArray(json);
            for(int i =0;i<cityArray.length();i++){
                City city = new City();
                JSONObject cityObject = cityArray.getJSONObject(i);
                JSONObject cityCoor = cityObject.getJSONObject("coord");

                city.setId(cityObject.getInt("id"));
                city.setName(cityObject.getString("name"));
                city.setLat(cityCoor.getDouble("lat"));
                city.setLongitudine(cityCoor.getDouble("lon"));
                cityName.add(cityObject.getString("name"));
                cityList.add(city);
            }
        } catch (JSONException e) {
            System.out.println(e.toString());
        }
    }

    public static String getCityLatLong(String oras){
        String latLong = null;
        for(City o : cityList){
            if(oras.equals(o.getName())){
                latLong = o.getLat().toString() + ";"+o.getLongitudine().toString();
            }
        }
        return latLong;
    }

}

