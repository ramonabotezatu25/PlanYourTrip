package com.example.ramona.planyourtrip;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class ConfirmationCode extends AppCompatActivity {

    EditText cod;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation_code);
        cod=(EditText)findViewById(R.id.editText_confirationCode);

    }
    public  void golesteEditText(View view)
    {
        cod.setText("");
    }
}
