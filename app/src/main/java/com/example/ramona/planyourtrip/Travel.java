package com.example.ramona.planyourtrip;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ramona.planyourtrip.MultiLanguage.Language;
import com.example.ramona.planyourtrip.MultiLanguage.MultiLanguageHelper;

import java.util.ArrayList;
import java.util.List;

import io.paperdb.Paper;

public class Travel extends AppCompatActivity {

    Language language = new Language();
    public static List<Integer> listaIDTextViews = new ArrayList<>();
    TextView textView ;
    TextView textViewNavHome ;
    TextView textViewNavProfile ;
    TextView textViewNavExplore ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel);
        allYouNeed();
    }

    private void allYouNeed() {
        //preiau toate textViewurile din clasa
        textView = (TextView) findViewById(R.id.textView);
        //pun toate id-urile stringurilor de care am nevoie
        getAllIdTextView();
        //seteaza limba default
        language.setDefaultLanguage(this,textView);
        //navigation view
        navView();
    }

    private void navView(){
        //navigation view
        final BottomNavigationView bottomNavigationView;
        bottomNavigationView = (BottomNavigationView)findViewById(R.id.nav_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @RequiresApi(api = Build.VERSION_CODES.ECLAIR)
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_home:
                        startNewActivity(Travel.class);
                        overridePendingTransition(0, 0);
                        break;
                    case R.id.nav_explore:
                        startNewActivity(Explore.class);
                        overridePendingTransition(0, 0);
                        break;
                    case R.id.nav_profile:
                        startNewActivity(Profile.class);
                        overridePendingTransition(0, 0);
                        break;
                    default:
                        Toast.makeText(getApplicationContext(),R.string.nav_exception,Toast.LENGTH_SHORT).show();
                        break;
                }
                return false;
            }
        });
    }

    private void startNewActivity(Class intent) {
        Intent a = new Intent(this,intent);
        startActivity(a);
    }

    private void getAllIdTextView() {
        Integer idTextView = R.string.hello;
        //adauga in lista de traduceri
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
