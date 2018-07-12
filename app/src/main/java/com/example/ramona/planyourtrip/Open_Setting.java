package com.example.ramona.planyourtrip;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Open_Setting extends AppCompatActivity {
    LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open__setting);
        linearLayout=(LinearLayout)findViewById(R.id.linearLaout_SettingsParola);
    }

    public void settingsPreferences(View view){
        Intent settings = new Intent(this, Formular_interese.class);
        settings.putExtra("operatie","update");
        startActivity(settings);
        // in caz ca a fost apasat update pass
        linearLayout.setVisibility(View.INVISIBLE);
    }

    public void schimbaParolaTV(View view){
        linearLayout.setVisibility(View.VISIBLE);

    }

    public void updatePassword(View view){
        Toast.makeText(getApplicationContext(),R.string.updateSucces, Toast.LENGTH_SHORT).show();
    }
}
