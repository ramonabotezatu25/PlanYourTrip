package com.example.ramona.planyourtrip.MultiLanguage;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.preference.PreferenceManager;

import java.util.Locale;
import java.util.prefs.PreferenceChangeEvent;

import io.paperdb.Paper;

public class MultiLanguageHelper {

    private static final String LIMBA_SELECTATA = "Locale.Helper.Selected.Language";

    public static Context onAttach(Context context){
        String limba = getPersistedData(context, Locale.getDefault().getLanguage());
        return setLocale(context,limba);
    }

    public static Context onAttach(Context context,String defaultLanguage){
        String limba = getPersistedData(context,defaultLanguage);
        return setLocale(context,limba);
    }
    public static Context setLocale(Context context, String limba) {
        persist(context,limba);
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.N){
            return updateResources(context,limba);
        }
        return updateResourcesLegacy(context,limba);
    }

    @TargetApi(Build.VERSION_CODES.N)
    private static Context updateResources(Context context, String limba) {
        Locale locale = new Locale(limba);
        locale.setDefault(locale);

        Configuration configuration = context.getResources().getConfiguration();
        configuration.setLocale(locale);
        configuration.setLayoutDirection(locale);
        return context.createConfigurationContext(configuration);
    }

    @SuppressWarnings("deprecation")
    private static Context updateResourcesLegacy(Context context, String limba) {
        Locale locale = new Locale(limba);
        locale.setDefault(locale);

        Resources resources = context.getResources();
        Configuration configuration = resources.getConfiguration();
        configuration.locale=locale;
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.JELLY_BEAN_MR1){
            configuration.setLayoutDirection(locale);
        }
        resources.updateConfiguration(configuration,resources.getDisplayMetrics());
        return context;
    }

    private static void persist(Context context, String limba) {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(LIMBA_SELECTATA,limba);
        editor.apply();
    }

    private static String getPersistedData(Context context, String limba) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(LIMBA_SELECTATA,limba);

    }
}
