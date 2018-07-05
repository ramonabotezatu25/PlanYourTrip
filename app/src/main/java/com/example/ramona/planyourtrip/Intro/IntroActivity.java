package com.example.ramona.planyourtrip.Intro;

import android.Manifest;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.example.ramona.planyourtrip.R;
import com.example.ramona.planyourtrip.TravelTest;
import com.github.paolorotolo.appintro.AppIntro2;
import com.github.paolorotolo.appintro.AppIntroFragment;

/**
 * Created by Ramona on 7/4/2018.
 */

public class IntroActivity extends AppIntro2{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addSlide(AppIntroFragment.newInstance("TItlu", "DESCRIERE", R.drawable.abudhabi,R.color.colorAccent2));
        addSlide(AppIntroFragment.newInstance("TItlu", "DESCRIERE", R.drawable.paris2,R.color.colorAccent2));
        addSlide(AppIntroFragment.newInstance("TItlu", "DESCRIERE", R.drawable.new_york2,R.color.colorAccent2));
        addSlide(AppIntroFragment.newInstance("Done", "Done", R.drawable.new_york2,R.color.colorAccent2));
        addSlide(AppIntroFragment.newInstance("Done", "Done", R.drawable.new_york2,R.color.colorAccent2));


        // OPTIONAL METHODS
        // Override bar/separator color.
        setBarColor(Color.parseColor("#3F51B5"));
        //setSeparatorColor(Color.parseColor("#2196F3"));

        // Hide Skip/Done button.
        showSkipButton(false);
        setProgressButtonEnabled(false);

        // Turn vibration on and set intensity.
        // NOTE: you will probably need to ask VIBRATE permission in Manifest.
        setVibrate(true);
        setVibrateIntensity(30);



    }

    @Override
    public void onSkipPressed(@Nullable Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
    }

    @Override
    public void onSlideChanged(@Nullable Fragment oldFragment, @Nullable Fragment newFragment) {
        super.onSlideChanged(oldFragment, newFragment);
        // Do something when the slide changes.
        Integer i=2;

        if(i==0){
            Intent ia = new Intent(getApplicationContext(), TravelTest.class);
            startActivity(ia);
        }
    }
}
