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
    private String descriere;
    private String atractii;
    private String restaurante;
    private String activitati;
    private String link;

    public Locatii() {
    }

    public Locatii(Integer id, String nume, Integer categorie, Integer categorie2, String lat, String lon, String descriere, String atractii, String restaurante, String activitati, String link) {
        this.id = id;
        this.nume = nume;
        this.categorie = categorie;
        this.categorie2 = categorie2;
        this.lat = lat;
        this.lon = lon;
        this.descriere = descriere;
        this.atractii = atractii;
        this.restaurante = restaurante;
        this.activitati = activitati;
        this.link = link;
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

    public String getDescriere() {
        return descriere;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }

    public String getAtractii() {
        return atractii;
    }

    public void setAtractii(String atractii) {
        this.atractii = atractii;
    }

    public String getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(String restaurante) {
        this.restaurante = restaurante;
    }

    public String getActivitati() {
        return activitati;
    }

    public void setActivitati(String activitati) {
        this.activitati = activitati;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
