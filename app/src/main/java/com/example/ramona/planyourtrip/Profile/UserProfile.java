package com.example.ramona.planyourtrip.Profile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.ramona.planyourtrip.Formular_interese;
import com.example.ramona.planyourtrip.R;
import com.example.ramona.planyourtrip.Util.Database.DatabaseOperation;
import com.example.ramona.planyourtrip.maps.YourPlace;

import static com.example.ramona.planyourtrip.GmailSender.Constante.idUtilizator;
import static com.example.ramona.planyourtrip.GmailSender.Constante.locatiiList;

public class UserProfile extends AppCompatActivity {

    //databse
    DatabaseOperation db = new DatabaseOperation();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
    }


    public void changeLanguage(View view){
        Intent i = new Intent(this,ChangeLanguage.class);
        startActivity(i);
    }

    public void yourPlace(View view){
        locatiiList = db.getUserLocation(idUtilizator);
        Intent yourPlace = new Intent(this, YourPlace.class);
        startActivity(yourPlace);
    }
    public void settingsPreferences(View view){
        Intent settings = new Intent(this, Formular_interese.class);
        settings.putExtra("operatie","update");
        startActivity(settings);
    }
}
