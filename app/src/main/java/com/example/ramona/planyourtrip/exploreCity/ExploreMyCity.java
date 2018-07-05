package com.example.ramona.planyourtrip.exploreCity;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import static com.example.ramona.planyourtrip.GmailSender.Constante.assetManager;
import static com.example.ramona.planyourtrip.GmailSender.Constante.numeOras;
import static com.example.ramona.planyourtrip.GmailSender.Constante.latitudine;
import static com.example.ramona.planyourtrip.GmailSender.Constante.longitudine;

import com.example.ramona.planyourtrip.MultiLanguage.MultiLanguageHelper;
import com.example.ramona.planyourtrip.R;
import com.example.ramona.planyourtrip.Util.Constants;
import com.example.ramona.planyourtrip.Util.Database.DatabaseOperation;
import com.example.ramona.planyourtrip.Weather.Weather;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import io.paperdb.Paper;

import static it.moondroid.coverflow.components.general.ToolBox.dpToPixels;

public class ExploreMyCity extends AppCompatActivity {
    TextView cityField, detailsField, currentTemperatureField, humidity_field, pressure_field, weatherIcon, updatedField;
    Typeface weatherFont;
    Bundle bu;
    //setari de limba
    Context context;
    Resources resources;
    // setare Image
    ImageView imageView;
    //database
    DatabaseOperation db =new DatabaseOperation();
    String linkLocatie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore_my_city);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);

        bu = getIntent().getExtras();
        if(bu!=null){
            assetManager=getAssets();
            numeOras=bu.getString("name");
            latitudine = bu.getString("lat");
            longitudine = bu.getString("long");
            linkLocatie=bu.getString("link");

        }
        CardFragmentPagerAdapter pagerAdapter = new CardFragmentPagerAdapter(getSupportFragmentManager(), dpToPixels(2, this));
        ShadowTransformer fragmentCardShadowTransformer = new ShadowTransformer(viewPager, pagerAdapter);
        fragmentCardShadowTransformer.enableScaling(true);

        viewPager.setAdapter(pagerAdapter);
        viewPager.setPageTransformer(false, fragmentCardShadowTransformer);
        viewPager.setOffscreenPageLimit(3);

        //setari de multi lang
        allYouNeed();

        setImageViewExplore();
    }

    public static float dpToPixels(int dp, Context context) {
        return dp * (context.getResources().getDisplayMetrics().density);
    }

    //setare imagine coperta
    private void setImageViewExplore(){
        //preia din baza orase
        String nume=numeOras.replace(" ","");
        String link=linkLocatie;

        if(link.isEmpty()){
            link="https://scontent.fotp3-3.fna.fbcdn.net/v/t1.0-9/30712461_587953308239947_3492033068502351872_n.jpg?_nc_cat=0&oh=b38e5409b120619bbd7bfde656c02080&oe=5B27EE83";
        }

        ImageView imageView=(ImageView)findViewById(R.id.imageView_explore_my_city);
        URL newurl = null;
        try {
            newurl = new URL(link);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Bitmap mIcon_val = null;
        try {
            mIcon_val = BitmapFactory.decodeStream(newurl.openConnection() .getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        imageView.setImageBitmap(mIcon_val);
    }
    private void allYouNeed() {

        //preiau toate view-urile care trebuie traduce din clasa

        //pun toate id-urile stringurilor de care am nevoie
        setAllTextOnActivity();
        //navigation view

    }

    private void setAllTextOnActivity() {
        //setari de limba
        context = getApplicationContext();
        context = MultiLanguageHelper.setLocale(context,(String) Paper.book().read("language"));
        resources = context.getResources();
    }



}
