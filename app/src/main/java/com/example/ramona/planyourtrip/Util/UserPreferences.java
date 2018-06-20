package com.example.ramona.planyourtrip.Util;

/**
 * Created by Ramona on 4/1/2018.
 */

public class UserPreferences {
    private Integer id;
    private String statusRelatie;
    private String areCopii;
    private String catDeDesPleci;
    private String categoria1;
    private String categoria2;
    private String buget;
    private String oraseVizitate;

    public UserPreferences() {
    }

    public UserPreferences(Integer id,String statusRelatie, String areCopii, String catDeDesPleci, String categoria1, String categoria2, String buget, String oraseVizitate) {
        this.id= id;
        this.statusRelatie = statusRelatie;
        this.areCopii = areCopii;
        this.catDeDesPleci = catDeDesPleci;
        this.categoria1 = categoria1;
        this.categoria2 = categoria2;
        this.buget = buget;
        this.oraseVizitate = oraseVizitate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatusRelatie() {
        return statusRelatie;
    }

    public void setStatusRelatie(String statusRelatie) {
        this.statusRelatie = statusRelatie;
    }

    public String getAreCopii() {
        return areCopii;
    }

    public void setAreCopii(String areCopii) {
        this.areCopii = areCopii;
    }

    public String getCatDeDesPleci() {
        return catDeDesPleci;
    }

    public void setCatDeDesPleci(String catDeDesPleci) {
        this.catDeDesPleci = catDeDesPleci;
    }

    public String getCategoria1() {
        return categoria1;
    }

    public void setCategoria1(String categoria1) {
        this.categoria1 = categoria1;
    }

    public String getCategoria2() {
        return categoria2;
    }

    public void setCategoria2(String categoria2) {
        this.categoria2 = categoria2;
    }

    public String getBuget() {
        return buget;
    }

    public void setBuget(String buget) {
        this.buget = buget;
    }

    public String getOraseVizitate() {
        return oraseVizitate;
    }

    public void setOraseVizitate(String oraseVizitate) {
        this.oraseVizitate = oraseVizitate;
    }

    @Override
    public String toString() {
        return "UserPreferences{" +
                "statusRelatie='" + statusRelatie + '\'' +
                ", areCopii='" + areCopii + '\'' +
                ", catDeDesPleci='" + catDeDesPleci + '\'' +
                ", categoria1='" + categoria1 + '\'' +
                ", categoria2='" + categoria2 + '\'' +
                ", buget='" + buget + '\'' +
                ", oraseVizitate='" + oraseVizitate + '\'' +
                '}';
    }
}
