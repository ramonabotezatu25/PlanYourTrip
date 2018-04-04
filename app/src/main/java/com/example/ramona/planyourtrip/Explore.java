package com.example.ramona.planyourtrip;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import com.example.ramona.planyourtrip.MultiLanguage.Language;
import com.example.ramona.planyourtrip.coverFlow.LocatiiAdapter;
import com.example.ramona.planyourtrip.coverFlow.LocatiiExplore;

import java.util.ArrayList;
import java.util.List;

import it.moondroid.coverflow.components.ui.containers.FeatureCoverFlow;

public class Explore extends AppCompatActivity {

    Language language = new Language();

    //pentru Cover Flow
    private FeatureCoverFlow coverFlow;
    private LocatiiAdapter locatiiAdapter;
    private List<LocatiiExplore> locatiiExploreList= new ArrayList<>();
    private TextSwitcher mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore);
        allYouNeed();

        //initializare cover Flow

        initData();
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
        locatiiAdapter= new LocatiiAdapter(locatiiExploreList, this);
        coverFlow= (FeatureCoverFlow)findViewById(R.id.coverFlow);
        coverFlow.setAdapter(locatiiAdapter);

        //

        coverFlow.setOnScrollPositionListener(new FeatureCoverFlow.OnScrollPositionListener() {
            @Override
            public void onScrolledToPosition(int position) {
                mTitle.setText(locatiiExploreList.get(position).getNumeLocatie());

            }

            @Override
            public void onScrolling() {

            }
        });
    }

    private void initData() {

        locatiiExploreList.add(new LocatiiExplore("Los Anegeles", "https://www.lacity.org/sites/g/files/wph781/f/styles/tiled_homepage_blog/public/bigstock-Los-Angeles-5909078.jpg?itok=Pu2dewLz"));
        locatiiExploreList.add(new LocatiiExplore("Barcelona", "http://cdn.hotellacasadelsol.com/wp-content/uploads/2018/01/barcelona-cultura-historia.jpg"));
        locatiiExploreList.add(new LocatiiExplore("Dubai", "https://gdb.alhurra.eu/B1C207A2-7469-4534-83CD-BF225D3E34A2_cx0_cy10_cw0_w1023_r1_s.jpg"));
        locatiiExploreList.add(new LocatiiExplore("San Francisco", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS-rP5URd2M-Xre2cWlUSHdeUS7GLWr-dSFrv990OdFWdfr6nTo"));



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
    private void getAllIdTextView() {
        //ex:Integer idTextView = R.string.hello;
        //adauga in lista de traduceri
        //ex: listaIDTextViews.add(idTextView);
    }
}
