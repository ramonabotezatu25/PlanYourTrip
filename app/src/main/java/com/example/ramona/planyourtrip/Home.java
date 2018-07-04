package com.example.ramona.planyourtrip;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ramona.planyourtrip.MultiLanguage.MultiLanguageHelper;
import com.example.ramona.planyourtrip.Profile.UserProfile;
import com.example.ramona.planyourtrip.Util.Database.DatabaseOperation;
import com.example.ramona.planyourtrip.Util.Locatii;
import com.example.ramona.planyourtrip.exploreCity.ExploreMyCity;

import static com.example.ramona.planyourtrip.GmailSender.Constante.locatiiList;
import static com.example.ramona.planyourtrip.GmailSender.Constante.orasDestinatieFlight;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import io.paperdb.Paper;

import static com.example.ramona.planyourtrip.GmailSender.Constante.userPreferencesForHome;
import static com.example.ramona.planyourtrip.MultiLanguage.Language.setDefaultLanguage;
import static com.example.ramona.planyourtrip.Weather.GetCityLongLat.getCityLatLong;
import static com.example.ramona.planyourtrip.Weather.GetCityLongLat.loadJSONFromAsset;

public class Home extends AppCompatActivity {

    private Random randomGenerator=new Random();

    //setari de limba
    Context context;
    Resources resources;
    //
    TextView homeTv1;
    TextView homeTv2;
    TextView homeTv3;
    TextView homeTv4;
    TextView homeTv5;
    TextView homeTv6;
    Button buttonExplore1;
    Button buttonExplore2;
    Button buttonExplore3;
    Button buttonExplore4;
    Button buttonExplore5;
    Button buttonExplore6;
    //
    //database
    DatabaseOperation db =new DatabaseOperation();

    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;
    //nume pe care il trimit in activitatea explore_city
    String orasSelectat;
    //locatii list
    List<Locatii> locatiiListHome = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //preia din baza orase
        getDB();

        //json pentru weather
        loadJSONFromAsset(this);
        //id urile imaginilor pentru setarea pozelor
        imageView1 = (ImageView) findViewById(R.id.home_imageView1);
        imageView2 = (ImageView) findViewById(R.id.home_imageView2);
        imageView3 = (ImageView) findViewById(R.id.home_imageView3);
        imageView4 = (ImageView) findViewById(R.id.home_imageView4);
        imageView5 = (ImageView) findViewById(R.id.home_imageView5);
        imageView6 = (ImageView) findViewById(R.id.home_imageView6);
        setareImagini();
        //setari de limba
        setDefaultLanguage(this);
        allYouNeed();
        metode();

        //navigation view
        navView();

    }

    public void getDB(){
        locatiiListHome = locatiiList;
        if(locatiiListHome.size()==0){
            locatiiListHome=db.getLocationByCateg(userPreferencesForHome);
        }else{
            List<Locatii> locatiiListHomeCategoria1 = new ArrayList<>();
            List<Locatii> locatiiListHomeCategoria2 = new ArrayList<>();
            List<Locatii> locatiiListHomeRandom =new ArrayList<>();
            for(int i = 0;i<locatiiListHome.size();i++){
                if(locatiiListHome.get(i).getCategorie() == Integer.parseInt(userPreferencesForHome.getCategoria1())){
                    locatiiListHomeCategoria1.add(locatiiListHome.get(i));
                }else if(locatiiListHome.get(i).getCategorie2() == Integer.parseInt(userPreferencesForHome.getCategoria2())){
                    locatiiListHomeCategoria2.add(locatiiListHome.get(i));
                }else{
                    locatiiListHomeRandom.add(locatiiListHome.get(i));
                }
            }
            locatiiListHome = new ArrayList<>();
            for(int j =0;j<2;j++){
            int index = randomGenerator.nextInt(locatiiListHomeCategoria1.size());
                locatiiListHome.add(locatiiListHomeCategoria1.get(index));
            }

            for(int j =0;j<2;j++){
                int index = randomGenerator.nextInt(locatiiListHomeCategoria2.size());
                locatiiListHome.add(locatiiListHomeCategoria2.get(index));
            }


            for(int j =0;j<2;j++){
                int index = randomGenerator.nextInt(locatiiListHomeRandom.size());
                locatiiListHome.add(locatiiListHomeRandom.get(index));
            }

            }

    }

    private void allYouNeed() {

        //preiau toate view-urile care trebuie traduce din clasa
        homeTv1=(TextView)findViewById(R.id.home_tv1);
        homeTv2=(TextView)findViewById(R.id.home_tv2);
        homeTv3=(TextView)findViewById(R.id.home_tv3);
        homeTv4=(TextView)findViewById(R.id.home_tv4);
        homeTv5=(TextView)findViewById(R.id.home_tv5);
        homeTv6=(TextView)findViewById(R.id.home_tv6);

        buttonExplore1=(Button)findViewById(R.id.home_btn1);
        buttonExplore2=(Button)findViewById(R.id.home_btn2);
        buttonExplore3=(Button)findViewById(R.id.home_btn3);
        buttonExplore4=(Button)findViewById(R.id.home_btn4);
        buttonExplore5=(Button)findViewById(R.id.home_btn5);
        buttonExplore6=(Button)findViewById(R.id.home_btn6);
        //pun toate id-urile stringurilor de care am nevoie
        setAllTextOnActivity();

    }

    private void navView(){

        //navigation view
        final BottomNavigationView bottomNavigationView;
        bottomNavigationView = (BottomNavigationView)findViewById(R.id.nav_view);
        Menu menu = bottomNavigationView.getMenu();
        for (int i = 0; i < bottomNavigationView.getMenu().size(); i++) {
            bottomNavigationView.getMenu().getItem(0).setChecked(true);
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

    private void setAllTextOnActivity() {
        //setari de limba
        context = getApplicationContext();
        context = MultiLanguageHelper.setLocale(context,(String) Paper.book().read("language"));
        resources = context.getResources();
        int oras1 = resources.getIdentifier(locatiiListHome.get(0).getNume(), "string", context.getPackageName());
        int oras2 = resources.getIdentifier(locatiiListHome.get(1).getNume(), "string", context.getPackageName());
        int oras3 = resources.getIdentifier(locatiiListHome.get(2).getNume(), "string", context.getPackageName());
        int oras4 = resources.getIdentifier(locatiiListHome.get(3).getNume(), "string", context.getPackageName());
        int oras5 = resources.getIdentifier(locatiiListHome.get(4).getNume(), "string", context.getPackageName());
        int oras6 = resources.getIdentifier(locatiiListHome.get(5).getNume(), "string", context.getPackageName());
        homeTv1.setText(resources.getText(oras1));
        homeTv2.setText(resources.getText(oras2));
        homeTv3.setText(resources.getText(oras3));
        homeTv4.setText(resources.getText(oras4));
        homeTv5.setText(resources.getText(oras5));
        homeTv6.setText(resources.getText(oras6));

        buttonExplore1.setText(resources.getString(R.string.btnExplore));
        buttonExplore2.setText(resources.getString(R.string.btnExplore));
        buttonExplore3.setText(resources.getString(R.string.btnExplore));
        buttonExplore4.setText(resources.getString(R.string.btnExplore));
        buttonExplore5.setText(resources.getString(R.string.btnExplore));
        buttonExplore6.setText(resources.getString(R.string.btnExplore));


    }

    public void metode (){
        buttonExplore1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String latLong = getCityLatLong(locatiiListHome.get(0).getNume());
                if(!latLong.equals("")) {
                    String[] lL = latLong.split(";");
                    orasSelectat= homeTv1.getText().toString();
                    deschideExploreCity(view,lL[0],lL[1],homeTv1.getText().toString(),locatiiListHome.get(0).getLink());
                }
            }
        });

        buttonExplore2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String latLong = getCityLatLong(locatiiListHome.get(1).getNume());
                if(!latLong.equals("")) {
                    String[] lL = latLong.split(";");
                    orasSelectat= homeTv2.getText().toString();
                    deschideExploreCity(view,lL[0],lL[1],homeTv2.getText().toString(),locatiiListHome.get(1).getLink());
                }
            }
        });

        buttonExplore3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String latLong = getCityLatLong(locatiiListHome.get(2).getNume());
                if(!latLong.equals("")) {
                    String[] lL = latLong.split(";");
                    orasSelectat= homeTv3.getText().toString();
                    deschideExploreCity(view,lL[0],lL[1],homeTv3.getText().toString(),locatiiListHome.get(2).getLink());
                }
            }
        });

        buttonExplore4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String latLong = getCityLatLong(locatiiListHome.get(3).getNume());
                if(!latLong.equals("")) {
                    String[] lL = latLong.split(";");
                    orasSelectat= homeTv4.getText().toString();
                    deschideExploreCity(view,lL[0],lL[1],homeTv4.getText().toString(),locatiiListHome.get(3).getLink());

                }
            }
        });

        buttonExplore5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String latLong = getCityLatLong(locatiiListHome.get(4).getNume());
                if(!latLong.equals("")) {
                    String[] lL = latLong.split(";");
                    orasSelectat= homeTv5.getText().toString();
                    deschideExploreCity(view,lL[0],lL[1],homeTv5.getText().toString(),locatiiListHome.get(4).getLink());

                }
            }
        });

        buttonExplore6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String latLong = getCityLatLong(locatiiListHome.get(5).getNume());
                if(!latLong.equals("")) {
                    String[] lL = latLong.split(";");
                    orasSelectat= homeTv6.getText().toString();
                    deschideExploreCity(view,lL[0],lL[1],homeTv6.getText().toString(),locatiiListHome.get(5).getLink());
                }
            }
        });
    }

    public void deschideExploreCity(View view,String lat,String lon,String name, String link){
        //cam asta e.Pentru test. Apeleaza cu New York
        Intent exploreCity = new Intent(Home.this, ExploreMyCity.class);
        exploreCity.putExtra("lat",lat);
        exploreCity.putExtra("long",lon);
        exploreCity.putExtra("name",name);
        exploreCity.putExtra("orasSelectat", orasSelectat);
        exploreCity.putExtra("link", link);
        orasDestinatieFlight =orasSelectat;
        startActivity(exploreCity);

    }

    public void setareImagini(){
        String link1=locatiiListHome.get(0).getLink();
        String link2=locatiiListHome.get(1).getLink();
        String link3=locatiiListHome.get(2).getLink();
        String link4=locatiiListHome.get(3).getLink();
        String link5=locatiiListHome.get(4).getLink();
        String link6=locatiiListHome.get(5).getLink();
        URL newurl1 = null;
        URL newurl2 = null;
        URL newurl3 = null;
        URL newurl4 = null;
        URL newurl5 = null;
        URL newurl6 = null;
        try {
            newurl1 = new URL(link1);
            newurl2 = new URL(link2);
            newurl3 = new URL(link3);
            newurl4 = new URL(link4);
            newurl5 = new URL(link5);
            newurl6 = new URL(link6);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Bitmap mIcon_val1 = null;
        Bitmap mIcon_val2 = null;
        Bitmap mIcon_val3 = null;
        Bitmap mIcon_val4= null;
        Bitmap mIcon_val5= null;
        Bitmap mIcon_val6= null;
        try {
            mIcon_val1 = BitmapFactory.decodeStream(newurl1.openConnection() .getInputStream());
            mIcon_val2 = BitmapFactory.decodeStream(newurl2.openConnection() .getInputStream());
            mIcon_val3 = BitmapFactory.decodeStream(newurl3.openConnection() .getInputStream());
            mIcon_val4 = BitmapFactory.decodeStream(newurl4.openConnection() .getInputStream());
            mIcon_val5 = BitmapFactory.decodeStream(newurl5.openConnection() .getInputStream());
            mIcon_val6 = BitmapFactory.decodeStream(newurl6.openConnection() .getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        imageView1.setImageBitmap(mIcon_val1);
        imageView2.setImageBitmap(mIcon_val2);
        imageView3.setImageBitmap(mIcon_val3);
        imageView4.setImageBitmap(mIcon_val4);
        imageView5.setImageBitmap(mIcon_val5);
        imageView6.setImageBitmap(mIcon_val6);

    }
}

