package com.example.ramona.planyourtrip.MultiLanguage;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.widget.TextView;

import io.paperdb.Paper;

/**
 * Created by claudiu.cercel on 1/19/2018.
 */

public class Language extends Application {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    public static void setDefaultLanguage(Context context){
        //default lang ro
        Paper.init(context);
        if((String) Paper.book().read("language") == null){
            Paper.book().write("language","ro");
        }
    }

    public static void schimbaLimba(String limba){
        if(limba.equals("RO")){
            Paper.book().write("language","ro");
        }else{
            Paper.book().write("language","en");
        }
    }
}
