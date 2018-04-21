package com.example.ramona.planyourtrip;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.ramona.planyourtrip.MultiLanguage.MultiLanguageHelper;
import com.example.ramona.planyourtrip.Util.FragmentList.FragmentChanger;
import com.example.ramona.planyourtrip.Util.FragmentList.FragmentConstant;
import com.example.ramona.planyourtrip.Util.FragmentList.FragmentLanguage;
import com.example.ramona.planyourtrip.Util.FragmentList.FragmentList;
import com.hitomi.cmlibrary.CircleMenu;
import com.hitomi.cmlibrary.OnMenuSelectedListener;

import io.paperdb.Paper;

import static com.example.ramona.planyourtrip.Util.CircleMenu.ConstantsCircleMenu.FRAGMENT_LIST;
import static com.example.ramona.planyourtrip.Util.FragmentList.FragmentConstant.FRAGMENT_NAME;

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
        int culoareMeniuPrincipal = Color.parseColor("#ff0000");
        int culoareSubmeniuProfil = Color.parseColor("#4ce3af");
        int culoareSubmeniuNearbyPlaces = Color.parseColor("#FF0040");
        int culoareSubMeniuLocatiiVizitate=Color.parseColor("#2C6307");
        int culoareSubMeniuLista=Color.parseColor("#FFFF00");

            circleMenu =(CircleMenu)findViewById(R.id.circleMenu);
            circleMenu.setMainMenu(culoareMeniuPrincipal,
                    R.drawable.addmenucircle,
                    R.drawable.removecirclemenu)
                    .addSubMenu(culoareSubmeniuProfil,R.drawable.languagecirclemenu)
                    .addSubMenu(culoareSubmeniuNearbyPlaces,R.drawable.nav_explore)
                    .addSubMenu(culoareSubMeniuLocatiiVizitate,R.drawable.icon_user2)
                    .addSubMenu(culoareSubMeniuLista,R.drawable.languagecirclemenu);

            circleMenu.setOnMenuSelectedListener(new OnMenuSelectedListener() {
                @Override
                public void onMenuSelected(int i) {
                    String indexOfMenu = String.valueOf(i);
                    changeFragment(indexOfMenu);
                  //  circleMenu.setVisibility(View.INVISIBLE);
                }
            });
        }

    private void changeFragment(String indexOfMenu){
        if(indexOfMenu !=null){
            Intent fragmentChanger = new Intent(this,FragmentChanger.class);
            fragmentChanger.putExtra(FRAGMENT_NAME,indexOfMenu);
            startActivity(fragmentChanger);
        }else{
            startNewActivity(CircleProfile.class);
        }
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
                        startNewActivity(Home.class);
                        overridePendingTransition(0, 0);
                        break;
                    case R.id.nav_explore:
                        startNewActivity(Explore.class);
                        overridePendingTransition(0, 0);
                        break;
                    case R.id.nav_profile:
                        startNewActivity(CircleProfile.class);
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
