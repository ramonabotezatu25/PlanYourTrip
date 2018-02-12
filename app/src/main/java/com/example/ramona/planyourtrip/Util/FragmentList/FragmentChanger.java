package com.example.ramona.planyourtrip.Util.FragmentList;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.ramona.planyourtrip.CircleProfile;
import com.example.ramona.planyourtrip.Explore;
import com.example.ramona.planyourtrip.R;

import static com.example.ramona.planyourtrip.Util.CircleMenu.ConstantsCircleMenu.FRAGENT_LANGUAGE;
import static com.example.ramona.planyourtrip.Util.CircleMenu.ConstantsCircleMenu.FRAGMENT_LIST;
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
            if(indexOfMenu.equals(FRAGENT_LANGUAGE)){
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
