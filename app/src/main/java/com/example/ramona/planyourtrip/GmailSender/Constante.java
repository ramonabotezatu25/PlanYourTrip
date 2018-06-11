package com.example.ramona.planyourtrip.GmailSender;

import com.example.ramona.planyourtrip.Util.Locatii;
import com.example.ramona.planyourtrip.Util.UserPreferences;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Random;

/**
 * Created by Infrasoft Ciprian on 31-Jan-18.
 */

public class Constante {

    public static String codUnicDeIndentificare="";
    public static UserPreferences userPreferencesForHome;
    public static List<Locatii> locatiiList = new ArrayList<>();
    public static String orasDestinatieFlight="";
    public static Integer idUtilizator;


    public static final void getSaltString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 10) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        codUnicDeIndentificare = salt.toString();
    }
}
