package com.example.ramona.planyourtrip;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;

import com.john.waveview.WaveView;

import java.util.ArrayList;
import java.util.List;

public class SignUpActivity extends AppCompatActivity {

    TextView nume;
    TextView email;
    TextView parola;
    WaveView waveView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        waveView = (WaveView)findViewById(R.id.waveView);
        waveView.setProgress(0);

        nume=(TextView)findViewById(R.id.editText_name_SignUp);
        email=(TextView)findViewById(R.id.editText_email_SignUp);
        parola=(TextView)findViewById(R.id.editText_password_SignUp);
        nume.addTextChangedListener(fieldValidatorTextWatcher);
        email.addTextChangedListener(fieldValidatorTextWatcher);
        parola.addTextChangedListener(fieldValidatorTextWatcher);
    }

    public void upateProgressBar()
    {
        waveView.setProgress(0);
        List<String> lista = new ArrayList<String>();

        if(!nume.getText().toString().equals(""))
            lista.add("element");

        if(!email.getText().toString().equals(""))
            lista.add("element");


        if (!parola.getText().toString().equals(""))
            lista.add("element");

            waveView.setProgress((int) (33.3*lista.size()));

    }
    TextWatcher fieldValidatorTextWatcher =new TextWatcher(){
        @Override
        public void afterTextChanged(Editable s) {
            upateProgressBar();
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }
    };
}
