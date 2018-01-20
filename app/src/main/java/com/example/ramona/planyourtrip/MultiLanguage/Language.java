package com.example.ramona.planyourtrip.MultiLanguage;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.ramona.planyourtrip.R;

import io.paperdb.Paper;

/**
 * Created by claudiu.cercel on 1/19/2018.
 */

public class Language extends Application {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

   /* public void setDefaultLanguage(Context context){
        //default lang ro
        Paper.init(context);
        String limba = Paper.book().read("language");
        if(limba == null){
            Paper.book().write("language","ro");
        }
        Paper.book().write("language","ro");
        updateView(context,(String) Paper.book().read("language"));
    }

    private void updateView(Context context, String limba) {
        Context contextApp = MultiLanguageHelper.setLocale(context,limba);
    }
    public void schimbaLimba(Context context,String limba){
        if(limba.equals("RO")){
            Paper.book().write("language","ro");
            updateView(context,(String) Paper.book().read("language"));
        }else{
            Paper.book().write("language","en");
            updateView(context,(String) Paper.book().read("language"));
        }
    }*/
}
