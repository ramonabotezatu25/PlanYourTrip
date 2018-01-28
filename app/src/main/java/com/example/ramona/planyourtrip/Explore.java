package com.example.ramona.planyourtrip;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.ramona.planyourtrip.MultiLanguage.Language;

public class Explore extends AppCompatActivity {

    Language language = new Language();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore);
        allYouNeed();
    }
    private void allYouNeed() {
        //preiau toate textViewurile din clasa
       // ex: textView = (TextView) findViewById(R.id.textView);
        //pun toate id-urile stringurilor de care am nevoie
        getAllIdTextView();
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
                        startNewActivity(TravelTest.class);
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
        //ex:Integer idTextView = R.string.hello;
        //adauga in lista de traduceri
        //ex: listaIDTextViews.add(idTextView);
    }
}
