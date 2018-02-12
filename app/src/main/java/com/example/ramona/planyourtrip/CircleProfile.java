package com.example.ramona.planyourtrip;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.ramona.planyourtrip.MultiLanguage.MultiLanguageHelper;
import com.hitomi.cmlibrary.CircleMenu;
import com.hitomi.cmlibrary.OnMenuSelectedListener;

import io.paperdb.Paper;

public class CircleProfile extends AppCompatActivity {
    //setari de limba
    Context context;
    Resources resources;
    CircleMenu circleMenu;
    //
    Bundle bu;
    String limba ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle_profile);
        allYouNeed();
        //
        loadCircleMenu();

    }

    public void loadCircleMenu(){
        String[] arrayMenu ={"Profil",
                "Language","Nearby","Povestea Mea"};
        int culoareMeniuPrincipal = Color.parseColor("#ff0000");
        int culoareSubmeniuProfil = Color.parseColor("#4ce3af");
        int culoareSubmeniuLimba = Color.parseColor("#ff0000");

            circleMenu =(CircleMenu)findViewById(R.id.circleMenu);
            circleMenu.setMainMenu(culoareMeniuPrincipal,
                    R.drawable.addmenucircle,
                    R.drawable.removecirclemenu)
                    .addSubMenu(culoareSubmeniuProfil,R.drawable.languagecirclemenu);
            circleMenu.setOnMenuSelectedListener(new OnMenuSelectedListener() {
                @Override
                public void onMenuSelected(int i) {
                    String var = String.valueOf(i);
                    Toast.makeText(getApplicationContext(),var, Toast.LENGTH_SHORT).show();
                }
            });
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

}
