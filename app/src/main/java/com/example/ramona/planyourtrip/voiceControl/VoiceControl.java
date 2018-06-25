package com.example.ramona.planyourtrip.voiceControl;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.ramona.planyourtrip.Profile.ChangeLanguage;
import com.example.ramona.planyourtrip.R;

import java.util.ArrayList;
import java.util.Locale;


public class VoiceControl extends AppCompatActivity {


    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voice_control);
    }

    public void speak(View v){
        tv = (TextView)findViewById(R.id.textView7);
        speakToInput();

    }

    private void speakToInput() {
        Intent i = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        i.putExtra(RecognizerIntent.EXTRA_PROMPT,"SAY SOMETHING");
        try{
           startActivityForResult(i,100);
        }catch(ActivityNotFoundException a) {
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case 100:
                if(resultCode == RESULT_OK && data!=null){
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    tv.setText(result.get(0));
                }
                break;
        }
    }


    public void changeActivity(View v){
        if(tv.getText().toString().toUpperCase().equals("CHANGE LANGUAGE"))
        {
            Intent i = new Intent(this, ChangeLanguage.class);
            startActivity(i);
        }
    }
}
