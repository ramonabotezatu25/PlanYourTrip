package com.example.ramona.planyourtrip;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import static android.widget.AdapterView.*;

public class Formular_interese extends AppCompatActivity {

    Spinner spinnerCategorii1;
    Spinner spinnerCategorii2;
    Spinner spinnerBuget;
    Spinner spinnerOrase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formular_interese);
        spinnerCategorii1=(Spinner)findViewById(R.id.formular_spinnerCategorii_1);
        spinnerCategorii2=(Spinner)findViewById(R.id.formular_spinnerCategorii_2);
        spinnerBuget=(Spinner)findViewById(R.id.formular_spinerBuget);
        spinnerOrase=(Spinner)findViewById(R.id.formular_spinnerOrase);

        //populez spinnerCategorii 1
        ArrayAdapter<CharSequence> adapter =ArrayAdapter.createFromResource(this, R.array.spinnerCategorii, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategorii1.setAdapter(adapter);

        spinnerCategorii1.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here
                String[] list;
                list= getResources().getStringArray(R.array.spinnerCategorii);
                List<String>listaOrase=new ArrayList<>();
                for(int i=0;i<list.length;i++){
                        listaOrase.add(list[i]);
                }
                listaOrase.remove(position);
                ArrayAdapter<String> adapter2 =
                        new ArrayAdapter<String>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, listaOrase);
                adapter2.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

                spinnerCategorii2.setAdapter(adapter2);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        //populez spinner Buget
        ArrayAdapter<CharSequence> adapter3 =ArrayAdapter.createFromResource(this, R.array.spinnerBuget, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerBuget.setAdapter(adapter3);

    }
}
