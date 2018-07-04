package com.example.ramona.planyourtrip;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import com.example.ramona.planyourtrip.Util.Database.DatabaseOperation;
import com.example.ramona.planyourtrip.Util.Locatii;

import java.util.ArrayList;
import java.util.List;

public class AddStory extends AppCompatActivity {
    AutoCompleteTextView textView;
    List<Locatii> listaLocatii = new ArrayList<>();
    EditText descriere;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_story);
        //   getLocatii();
        AutoCompleteTextView textView = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView_add_story_locatie);
        List<String> lista = new ArrayList<>();
        lista.add("New York");
        lista.add("Paris");
        lista.add("Londra");
        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, lista);
        textView.setAdapter(adapter);
    }

}