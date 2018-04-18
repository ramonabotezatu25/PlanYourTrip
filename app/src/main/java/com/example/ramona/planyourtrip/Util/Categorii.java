package com.example.ramona.planyourtrip.Util;

/**
 * Created by Ramona on 4/18/2018.
 */

public class Categorii {
    Integer id;
    String numeCategorie;

    public Categorii() {
    }

    public Categorii(Integer id, String numeCategorie) {
        this.id = id;
        this.numeCategorie = numeCategorie;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumeCategorie() {
        return numeCategorie;
    }

    public void setNumeCategorie(String numeCategorie) {
        this.numeCategorie = numeCategorie;
    }
}


