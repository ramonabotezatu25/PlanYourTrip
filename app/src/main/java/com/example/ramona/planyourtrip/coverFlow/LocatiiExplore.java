package com.example.ramona.planyourtrip.coverFlow;

import android.widget.ImageView;

/**
 * Created by Ramona on 4/4/2018.
 */

public class LocatiiExplore {

    private String numeLocatie;
    private String imageURL;


    public LocatiiExplore(String numeLocatie, String imageURL) {
        this.numeLocatie = numeLocatie;
        this.imageURL = imageURL;
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
}
