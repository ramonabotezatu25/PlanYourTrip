package com.example.ramona.planyourtrip;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ramona.planyourtrip.MultiLanguage.MultiLanguageHelper;
import com.example.ramona.planyourtrip.Util.FragmentList.FragmentConstant;
import com.example.ramona.planyourtrip.Util.FragmentList.FragmentLanguage;
import com.example.ramona.planyourtrip.Util.FragmentList.FragmentList;
import com.example.ramona.planyourtrip.Util.Popup.Popup;

import io.paperdb.Paper;

public class Profile extends AppCompatActivity {
    //setari de limba
    Context context;
    Resources resources;
    //
    Bundle bu;
    String limba ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        //vezi metoda in TRAVEL TEST SI IA O DE ACOLO.-imediat dupa setContentView trebuie pusa aceasta metoda pentru a prelua contextul actual
        allYouNeed();
        //fragment
        changeFragment();


    }
    private void allYouNeed() {
        //setari de limba
        context = getApplicationContext();
        context = MultiLanguageHelper.setLocale(context,(String) Paper.book().read("language"));
        resources = context.getResources();
        //preiau toate view-urile care trebuie traduce din clasa
        limba = resources.getString(R.string.profil_meniu_language);
        //navigation view
        navView();
    }
    private void navView(){

        //navigation view
        final BottomNavigationView bottomNavigationView;
        bottomNavigationView = (BottomNavigationView)findViewById(R.id.nav_view);
        Menu menu = bottomNavigationView.getMenu();
        for (int i = 0; i < bottomNavigationView.getMenu().size(); i++) {
            MenuItem menuItem = menu.getItem(i);
            if (i == 0)
                menuItem.setTitle(resources.getString(R.string.nav_home));
            if (i == 1)
                menuItem.setTitle(resources.getString(R.string.nav_explore));
            if (i == 2)
                menuItem.setTitle(resources.getString(R.string.nav_profile));
        }
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
    private void changeFragment(){
        bu = getIntent().getExtras();
        if(bu !=null){
            if(bu.getString(FragmentConstant.fragmentName).equals(limba)){
                FragmentLanguage fragmentList = (FragmentLanguage)getSupportFragmentManager().findFragmentByTag("fragmentList");
                if(fragmentList==null){
                    fragmentList = new FragmentLanguage();
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.add(android.R.id.content,fragmentList,"fragmentList");
                    fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right, android.R.anim.slide_out_right, android.R.anim.slide_in_left);
                    // fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.animator.fade_out);
                    fragmentTransaction.commit();
                }
            }
        }else{
            FragmentList fragmentList = (FragmentList)getSupportFragmentManager().findFragmentByTag("fragmentList");
            if(fragmentList==null){
                fragmentList = new FragmentList();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.add(android.R.id.content,fragmentList,"fragmentList");
                fragmentTransaction.commit();
            }
        }
    }

    public void viewProfile(View view){
        Intent viewProfile = new Intent(this, Popup.class);
        startActivity(viewProfile);
    }

}
