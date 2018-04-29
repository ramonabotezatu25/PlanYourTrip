package com.example.ramona.planyourtrip.Util.FragmentList;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.ramona.planyourtrip.CircleProfile;
import com.example.ramona.planyourtrip.Explore;
import com.example.ramona.planyourtrip.R;
import com.example.ramona.planyourtrip.Weather.ChooseCityWeather;

import static com.example.ramona.planyourtrip.Util.CircleMenu.ConstantsCircleMenu.FRAGENT_LOCATII_VIZITATE;
import static com.example.ramona.planyourtrip.Util.CircleMenu.ConstantsCircleMenu.FRAGENT_NEARBY_PLACES;
import static com.example.ramona.planyourtrip.Util.CircleMenu.ConstantsCircleMenu.FRAGMENT_LIST;
import static com.example.ramona.planyourtrip.Util.CircleMenu.ConstantsCircleMenu.FRAGMENT_PROFIL;
import static com.example.ramona.planyourtrip.Util.FragmentList.FragmentConstant.FRAGMENT_NAME;

public class FragmentChanger extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feagment_changer);
        Bundle bundle = getIntent().getExtras();
        changeFragment(bundle.getString(FRAGMENT_NAME));
    }

    private void changeFragment(String indexOfMenu){
        if(indexOfMenu !=null){
            if(indexOfMenu.equals(FRAGENT_NEARBY_PLACES)){
                FragmentLanguage fragmentList = (FragmentLanguage)getSupportFragmentManager().findFragmentByTag(FRAGMENT_LIST);
                if(fragmentList==null){
                    fragmentList = new FragmentLanguage();
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.add(android.R.id.content,fragmentList,FRAGMENT_LIST);
                    fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right, android.R.anim.slide_out_right, android.R.anim.slide_in_left);
                    // fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.animator.fade_out);
                    fragmentTransaction.commit();
                    //

                }
            }else if(indexOfMenu.equals(FRAGENT_LOCATII_VIZITATE)){
                startNewActivity(ChooseCityWeather.class);
            }
            else if(indexOfMenu.equals(FRAGMENT_PROFIL)){
                Toast.makeText(getApplicationContext()," Profil momentan indisponibil", Toast.LENGTH_SHORT);
            }
            else if(indexOfMenu.equals((FRAGMENT_LIST))){
                Toast.makeText(getApplicationContext(),"Lista momentan indisponibila", Toast.LENGTH_SHORT);

            }
        }else{
            startNewActivity(CircleProfile.class);
        }
    }

    private void startNewActivity(Class intent) {
        Intent a = new Intent(this,intent);
        startActivity(a);
    }
}
