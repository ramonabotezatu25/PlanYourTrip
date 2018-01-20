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

import java.util.ArrayList;
import java.util.List;

import io.paperdb.Paper;

public class Travel extends AppCompatActivity {

    Language language = new Language();
    public static List<Integer> listaIDTextViews = new ArrayList<>();
    TextView textView ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel);
        //preiau toate textViewurile din clasa
        textView = (TextView) findViewById(R.id.textView);
        getAllIdTextView();
        Integer idTextView = R.string.hello;
        //seteaza limba default
       language.setDefaultLanguage(this,textView);
    }

    private void getAllIdTextView() {
        Integer idTextView = R.string.hello;
        listaIDTextViews.add(idTextView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()== R.id.engleza){
           language.schimbaLimba(this,"EN",textView);
        }else if(item.getItemId()== R.id.romana){
            language.schimbaLimba(this,"RO",textView);
        }
        return super.onOptionsItemSelected(item);
    }
}
