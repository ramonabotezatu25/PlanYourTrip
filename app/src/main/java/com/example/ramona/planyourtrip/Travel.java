package com.example.ramona.planyourtrip;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.ramona.planyourtrip.MultiLanguage.Language;
import com.example.ramona.planyourtrip.MultiLanguage.MultiLanguageHelper;

import io.paperdb.Paper;

public class Travel extends AppCompatActivity {

    Language language = new Language();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel);
        //seteaza limba default
       //language.setDefaultLanguage(this);
        //default lang ro
        Paper.init(this);

        //
        String limba = Paper.book().read("language");
        if(limba == null){
            Paper.book().write("language","ro");
        }
        updateView((String) Paper.book().read("language"));
    }

    public void updateView (String limba){
        Context context = MultiLanguageHelper.setLocale(this,limba);
        Resources resources = context.getResources();
        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText(resources.getString(R.string.hello));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()== R.id.engleza){
            Paper.book().write("language","en");
            updateView((String) Paper.book().read("language"));
        }else if(item.getItemId()== R.id.romana){
            Paper.book().write("language","ro");
            updateView((String) Paper.book().read("language"));
        }
        return super.onOptionsItemSelected(item);
    }

}
