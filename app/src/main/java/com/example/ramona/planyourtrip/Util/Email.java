package com.example.ramona.planyourtrip.Util;

/**
 * Created by Ramona on 4/14/2018.
 */

public class Email {
    private Integer id;
    private String nume_utilizator;
    private String emailFrom;
    private String emailTo;
    private String emailSubject;
    private String emailBody;

    public Email() {
    }

    public Email(Integer id, String nume_utilizator, String emailFrom, String emailTo, String emailSubject, String emailBody) {
        this.id = id;
        this.nume_utilizator = nume_utilizator;
        this.emailFrom = emailFrom;
        this.emailTo = emailTo;
        this.emailSubject = emailSubject;
        this.emailBody = emailBody;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNume_utilizator() {
        return nume_utilizator;
    }

    public void setNume_utilizator(String nume_utilizator) {
        this.nume_utilizator = nume_utilizator;
    }

    public String getEmailFrom() {
        return emailFrom;
    }

    public void setEmailFrom(String emailFrom) {
        this.emailFrom = emailFrom;
    }

    public String getEmailTo() {
        return emailTo;
    }

    public void setEmailTo(String emailTo) {
        this.emailTo = emailTo;
    }

    public String getEmailSubject() {
        return emailSubject;
    }

    public void setEmailSubject(String emailSubject) {
        this.emailSubject = emailSubject;
    }

    public String getEmailBody() {
        return emailBody;
    }

    public void setEmailBody(String emailBody) {
        this.emailBody = emailBody;
    }

}
