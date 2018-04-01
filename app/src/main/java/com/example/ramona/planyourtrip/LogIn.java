package com.example.ramona.planyourtrip;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import br.com.bloder.magic.internal.MagicAnimation;
import br.com.bloder.magic.view.MagicButton;

public class LogIn extends AppCompatActivity {
    MagicButton magicButton;
    MagicButton magicButton2;
    MagicAnimation magicAnimation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        magicButton=(MagicButton)findViewById(R.id.magic_button1);
        magicButton.setLeft(1);
        magicButton2=(MagicButton)findViewById(R.id.magic_button2);
        magicButton.setLeft(0);
        magicButton2.setMagicButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "V-ati conectat cu GOOGLE + ", Toast.LENGTH_SHORT).show();
            }
        });
        magicButton.setMagicButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Toast.makeText(getApplicationContext(), "V-ati conectat cu FACEBOOK", Toast.LENGTH_SHORT).show();
            }
        });


    }



    //deschide SignUp Form
    public void deschideSignUp(View View){
        Intent intent= new Intent(LogIn.this, SignUpActivity.class);
        startActivity(intent);


    }
}
