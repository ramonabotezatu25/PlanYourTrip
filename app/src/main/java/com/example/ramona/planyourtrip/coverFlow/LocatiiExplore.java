package com.example.ramona.planyourtrip.coverFlow;

import android.widget.ImageView;

/**
 * Created by Ramona on 4/4/2018.
 */

public class LocatiiExplore {

    private String numeLocatie;
    private String imageURL;
    private String lat;
    private String lon;


    public LocatiiExplore(){};
    public LocatiiExplore(String numeLocatie, String imageURL, String lat, String lon) {
        this.numeLocatie = numeLocatie;
        this.imageURL = imageURL;
        this.lat=lat;
        this.lon=lon;
    }

    public String getNumeLocatie() {
        return numeLocatie;
    }

    public void setNumeLocatie(String numeLocatie) {
        this.numeLocatie = numeLocatie;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }
}
