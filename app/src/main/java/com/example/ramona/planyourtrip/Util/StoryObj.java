package com.example.ramona.planyourtrip.Util;

/**
 * Created by Ramona on 7/4/2018.
 */

public class StoryObj {

    private Integer id;
    private Integer idUser;
    private Integer idLocatie;
    private String titlu;
    private String poveste;
    private String facebook;
    private String instagram;
    private String link;


    public String getTitlu() {
        return titlu;
    }

    public void setTitlu(String titlu) {
        this.titlu = titlu;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public Integer getIdLocatie() {
        return idLocatie;
    }

    public void setIdLocatie(Integer idLocatie) {
        this.idLocatie = idLocatie;
    }

    public String getPoveste() {
        return poveste;
    }

    public void setPoveste(String poveste) {
        this.poveste = poveste;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
