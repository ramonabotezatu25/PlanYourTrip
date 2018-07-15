package com.example.ramona.planyourtrip.GmailSender;

import android.content.res.AssetManager;

import com.example.ramona.planyourtrip.Util.Locatii;
import com.example.ramona.planyourtrip.Util.LuggageList;
import com.example.ramona.planyourtrip.Util.StoryObj;
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


    public static AssetManager assetManager;
    public static String numeOras;
    public static String latitudine;
    public static String longitudine ;


    //story
    public static List<StoryObj> storyList = new ArrayList<>();
    public static Integer idLocatie;
    //luggage
    public static List<String> luggageList = new ArrayList<>();
    public static Integer luggageListSize = 0;
    public static List<LuggageList> luggageListUser = new ArrayList<>();
    public static List<String> luggageListChecked = new ArrayList<>();
    public static Integer lastLuggageIDUser =0;

     public static final double[] currentLocation = new double[2];


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
