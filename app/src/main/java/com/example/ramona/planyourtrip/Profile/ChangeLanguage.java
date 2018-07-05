package com.example.ramona.planyourtrip.Profile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.ramona.planyourtrip.R;
import com.wajahatkarim3.easyflipview.EasyFlipView;

import io.paperdb.Paper;

public class ChangeLanguage extends AppCompatActivity {

    EasyFlipView easyFlipView;
    String limba;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_language);
        easyFlipView = (EasyFlipView) findViewById(R.id.flip);
        limba = (String) Paper.book().read("language");
        if(limba.equals("en"))
            easyFlipView.flipTheView();

    }

    public void setRomanian(View v){
        easyFlipView.flipTheView();
        limba = (String) Paper.book().read("language");
        if(limba.equals("ro")){
            Paper.book().write("language","en");
            Toast.makeText(getApplicationContext(),"English",Toast.LENGTH_LONG).show();
        }else{
            Paper.book().write("language","ro");
            Toast.makeText(getApplicationContext(),"Romana",Toast.LENGTH_LONG).show();
        }
    }

}
