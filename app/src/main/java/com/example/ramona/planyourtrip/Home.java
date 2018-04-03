package com.example.ramona.planyourtrip;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ramona.planyourtrip.MultiLanguage.MultiLanguageHelper;
import com.example.ramona.planyourtrip.Util.Database.DatabaseOperation;
import com.example.ramona.planyourtrip.Util.Locatii;
import com.example.ramona.planyourtrip.Weather.WeatherMainActivity;

import java.util.ArrayList;
import java.util.List;

import io.paperdb.Paper;

import static com.example.ramona.planyourtrip.MultiLanguage.Language.setDefaultLanguage;
import static com.example.ramona.planyourtrip.Weather.GetCityLongLat.cityList;
import static com.example.ramona.planyourtrip.Weather.GetCityLongLat.getCityLatLong;
import static com.example.ramona.planyourtrip.Weather.GetCityLongLat.loadJSONFromAsset;

public class Home extends AppCompatActivity {

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
    List<String> orase = new ArrayList<>();
    List<Locatii> locatiiList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //preia din baza orase
        getDB();


        //json pentru weather
        loadJSONFromAsset(this);


        //setare imagine cardviw1 cu poza luata din resurse - merge
        imageView1 = (ImageView) findViewById(R.id.home_imageView1);
        String lowerCountryCode = "rome2";
        int id = getResources().getIdentifier(lowerCountryCode, "drawable", getPackageName());
        imageView1.setImageResource(id);


        //setari de limba
        setDefaultLanguage(this);
        allYouNeed();

        metode();
    }

    public void getDB(){

        locatiiList=db.getLocation();
        for(Locatii l : locatiiList){
            if(orase.size()<6)
                orase.add(l.getNume());
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
    private void setAllTextOnActivity() {
        //setari de limba
        context = getApplicationContext();
        context = MultiLanguageHelper.setLocale(context,(String) Paper.book().read("language"));
        resources = context.getResources();
        int oras1 = resources.getIdentifier(orase.get(0), "string", context.getPackageName());
        int oras2 = resources.getIdentifier(orase.get(1), "string", context.getPackageName());
        int oras3 = resources.getIdentifier(orase.get(2), "string", context.getPackageName());
        int oras4 = resources.getIdentifier(orase.get(3), "string", context.getPackageName());
        int oras5 = resources.getIdentifier(orase.get(4), "string", context.getPackageName());
        int oras6 = resources.getIdentifier(orase.get(5), "string", context.getPackageName());
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
                String latLong = getCityLatLong(orase.get(0));
                if(!latLong.equals("")) {
                    String[] lL = latLong.split(";");
                    deschideExploreCity(lL[0],lL[1]);
                }
            }
        });

        buttonExplore2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String latLong = getCityLatLong(orase.get(1));
                if(!latLong.equals("")) {
                    String[] lL = latLong.split(";");
                    deschideExploreCity(lL[0],lL[1]);
                }
            }
        });

        buttonExplore3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String latLong = getCityLatLong(orase.get(2));
                if(!latLong.equals("")) {
                    String[] lL = latLong.split(";");
                    deschideExploreCity(lL[0],lL[1]);
                }
            }
        });

        buttonExplore4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String latLong = getCityLatLong(orase.get(3));
                if(!latLong.equals("")) {
                    String[] lL = latLong.split(";");
                    deschideExploreCity(lL[0],lL[1]);
                }
            }
        });

        buttonExplore5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String latLong = getCityLatLong(orase.get(4));
                if(!latLong.equals("")) {
                    String[] lL = latLong.split(";");
                    deschideExploreCity(lL[0],lL[1]);
                }
            }
        });

        buttonExplore6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String latLong = getCityLatLong(orase.get(5));
                if(!latLong.equals("")) {
                    String[] lL = latLong.split(";");
                    deschideExploreCity(lL[0],lL[1]);
                }
            }
        });
    }

    public void deschideExploreCity(String lat,String lon){
        //cam asta e.Pentru test. Apeleaza cu New York
        Intent exploreCity = new Intent(Home.this, ExploreCity.class);
        exploreCity.putExtra("lat",lat);
        exploreCity.putExtra("long",lon);
        startActivity(exploreCity);

    }
}
