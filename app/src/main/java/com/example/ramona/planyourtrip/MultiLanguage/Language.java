package com.example.ramona.planyourtrip.MultiLanguage;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.widget.TextView;

import io.paperdb.Paper;

import static com.example.ramona.planyourtrip.TravelTest.listaIDTextViews;

/**
 * Created by claudiu.cercel on 1/19/2018.
 */

public class Language extends Application {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    public void setDefaultLanguage(Context context, TextView... textViews){
        //default lang ro
        Paper.init(context);
        String limba = Paper.book().read("language");
        Paper.book().write("language","ro");
        updateView(context,(String) Paper.book().read("language"),textViews);
    }

    private void updateView(Context context, String limba,TextView...textViews) {
        context = MultiLanguageHelper.setLocale(context,limba);
        Resources resources = context.getResources();
        for(int i= 0;i<textViews.length;i++){
            textViews[i].setText(resources.getString(listaIDTextViews.get(i)));
        }
    }
    public void schimbaLimba(Context context,String limba,TextView... textViews){
        if(limba.equals("RO")){
            Paper.book().write("language","ro");
            updateView(context,(String) Paper.book().read("language"),textViews);
        }else{
            Paper.book().write("language","en");
            updateView(context,(String) Paper.book().read("language"),textViews);
        }
    }
}
