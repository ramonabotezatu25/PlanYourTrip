package com.example.ramona.planyourtrip.Util;

/**
 * Created by Ramona on 4/3/2018.
 */

public class User {
    private Integer id;
    private String nume;
    private String email;
    private String parola;
    private String codConfirmare;

    public User() {
    }

    public User(Integer id, String nume, String email, String parola, String codConfirmare) {
        this.id = id;
        this.nume = nume;
        this.email = email;
        this.parola = parola;
        this.codConfirmare = codConfirmare;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getParola() {
        return parola;
    }

    public void setParola(String parola) {
        this.parola = parola;
    }

    public String getCodConfirmare() {
        return codConfirmare;
    }

    public void setCodConfirmare(String codConfirmare) {
        this.codConfirmare = codConfirmare;
    }
}
