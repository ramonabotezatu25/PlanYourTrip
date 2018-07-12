package com.example.ramona.planyourtrip;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import com.example.ramona.planyourtrip.MultiLanguage.Language;
import com.example.ramona.planyourtrip.MultiLanguage.MultiLanguageHelper;
import com.example.ramona.planyourtrip.Profile.UserProfile;
import com.example.ramona.planyourtrip.Util.Database.DatabaseOperation;
import com.example.ramona.planyourtrip.Util.Locatii;
import com.example.ramona.planyourtrip.coverFlow.LocatiiAdapter;
import com.example.ramona.planyourtrip.exploreCity.ExploreMyCity;

import java.util.ArrayList;
import java.util.List;

import io.paperdb.Paper;
import it.moondroid.coverflow.components.ui.containers.FeatureCoverFlow;

import static com.example.ramona.planyourtrip.GmailSender.Constante.locatiiList;
import static com.example.ramona.planyourtrip.GmailSender.Constante.orasDestinatieFlight;

public class Explore extends AppCompatActivity {

    Language language = new Language();
    //setari de limba
    Context context;
    Resources resources;
    String limba ;
    //database
    DatabaseOperation db =new DatabaseOperation();
    //pentru Cover Flow
    private FeatureCoverFlow coverFlow;
    private LocatiiAdapter locatiiAdapter;
    private TextSwitcher mTitle;
    //lista orase

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore);
        allYouNeed();

        //initializare lista locatii
        getLocatii();
        mTitle=(TextSwitcher)findViewById(R.id.textSwitcher_numeOras);
        mTitle.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                LayoutInflater inflater= LayoutInflater.from(Explore.this);
                TextView txt= (TextView) inflater.inflate(R.layout.layout_title_cover_flow, null);
                return  txt;
            }
        });

        //animatii
        Animation in= AnimationUtils.loadAnimation(this, R.anim.slide_in_top_cover_flow);
        Animation out= AnimationUtils.loadAnimation(this, R.anim.slide_out_bottom_cover_flow);
        mTitle.setInAnimation(in);
        mTitle.setOutAnimation(out);

        //
        locatiiAdapter= new LocatiiAdapter(locatiiList, this);
        coverFlow= (FeatureCoverFlow)findViewById(R.id.coverFlow);
        coverFlow.setAdapter(locatiiAdapter);

        //

        coverFlow.setOnScrollPositionListener(new FeatureCoverFlow.OnScrollPositionListener() {
            @Override
            public void onScrolledToPosition(int position) {
                mTitle.setText(locatiiList.get(position).getNume());

            }
            @Override
            public void onScrolling() {

            }
        });

        coverFlow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent= new Intent(getApplicationContext(), ExploreMyCity.class);
                intent.putExtra("lat",locatiiList.get(i).getLat());
                intent.putExtra("long",locatiiList.get(i).getLon());
                intent.putExtra("name",locatiiList.get(i).getNume());
                intent.putExtra("link", locatiiList.get(i).getLink());
                orasDestinatieFlight = locatiiList.get(i).getNume();
                startActivity(intent);
            }
        });
    }

    private void allYouNeed() {
        //setari de limba
        context = getApplicationContext();
        context = MultiLanguageHelper.setLocale(context,(String) Paper.book().read("language"));
        resources = context.getResources();
        //navigation view
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
        Menu menu = bottomNavigationView.getMenu();
        for (int i = 0; i < bottomNavigationView.getMenu().size(); i++) {
            bottomNavigationView.getMenu().getItem(1).setChecked(true);
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
                        startNewActivity(UserProfile.class);
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

    //setare galerie
    private void getLocatii(){
        //preia din baza orase
        if(locatiiList.size()==0){
            locatiiList= db.getLocation();
        }


        for(int i=0;i<30;i++){
            String locatie=locatiiList.get(i).getNume();
            int id=resources.getIdentifier(locatie, "string", context.getPackageName());
            if(id!=0){
                locatie=resources.getString(id);
                locatiiList.get(i).setNume(locatie);
            }

        }
    }
}
