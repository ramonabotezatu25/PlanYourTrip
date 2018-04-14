package com.example.ramona.planyourtrip.Util;

/**
 * Created by Ramona on 4/3/2018.
 */

public class Locatii {
    private Integer id;
    private String nume;
    private Integer categorie;
    private Integer categorie2;
    private String lat;
    private String lon;

    public Locatii() {
    }

    public Locatii(Integer id, String nume, Integer cateorie1, Integer categorie2, String lat, String lon) {
        this.id = id;
        this.nume = nume;
        this.categorie = cateorie1;
        this.categorie2 = categorie2;
        this.lat = lat;
        this.lon = lon;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public Integer getCategorie() {
        return categorie;
    }

    public void setCategorie(Integer categorie) {
        this.categorie = categorie;
    }

    public Integer getCategorie2() {
        return categorie2;
    }

    public void setCategorie2(Integer categorie2) {
        this.categorie2 = categorie2;
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
