package com.example.ramona.planyourtrip.NearbyPlaces.MAPS;

import org.json.JSONObject;

/**
 * Created by Infrasoft Ciprian on 05-Feb-18.
 */

public class FindPlaces {
    public int id;
    public String name;
    public String vicinity;
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    public String token;
    public JSONObject getTypes() {
        return types;
    }

    public void setTypes(JSONObject types) {
        this.types = types;
    }

    public JSONObject getLoc() {
        return loc;
    }
    public void setLoc(JSONObject loc) {
        this.loc = loc;
    }
    public JSONObject types;
    private JSONObject loc;
    public Double getLat() {
        return lat;
    }
    public void setLat(Double lat) {
        this.lat = lat;
    }
    private Double lat;
    public Double getLng() {
        return lng;
    }
    public void setLng(Double lng) {
        this.lng = lng;
    }

    private Double lng;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getVicinity() {
        return vicinity;
    }
    public void setVicinity(String vicinity) {
        this.vicinity = vicinity;
    }
}
