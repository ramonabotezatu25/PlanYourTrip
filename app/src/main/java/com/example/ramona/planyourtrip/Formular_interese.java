package com.example.ramona.planyourtrip;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.john.waveview.WaveView;
import com.taishi.flipprogressdialog.FlipProgressDialog;

import java.util.ArrayList;
import java.util.List;

import static android.widget.AdapterView.*;

public class Formular_interese extends AppCompatActivity{

    Spinner spinnerCategorii1;
    Spinner spinnerCategorii2;
    Spinner spinnerBuget;
    Spinner spinnerOrase;
    ArrayList<Integer> listOraseSelectate = new ArrayList<>();
    FlipProgressDialog fpd;
    WaveView waveView;
    RadioButton rb_single;
    RadioButton rb_inRelatie;
    RadioButton rb_castorit;
    RadioButton rb_avetiCopii;
    RadioButton rb_catDeDesPleci_rar;
    RadioButton rb_catDeDesPleci_des;
    RadioButton rb_catDeDesPleci_foarteDes;
    RadioButton rb_interesatOraseVizitate;
    RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formular_interese);

        waveView = (WaveView) findViewById(R.id.waveView2);
        waveView.setProgress(0);

        spinnerCategorii1 = (Spinner) findViewById(R.id.formular_spinnerCategorii_1);
        spinnerCategorii2 = (Spinner) findViewById(R.id.formular_spinnerCategorii_2);
        spinnerBuget = (Spinner) findViewById(R.id.formular_spinerBuget);
        spinnerOrase = (Spinner) findViewById(R.id.formular_spinnerOrase);
        spinnerOrase.setVisibility(INVISIBLE);

        rb_single=(RadioButton)findViewById(R.id.formular_rb_singur);
        rb_inRelatie=(RadioButton)findViewById(R.id.formular_rb_inRelatie);
        rb_castorit=(RadioButton)findViewById(R.id.formular_rb_casatorit);
        rb_avetiCopii=(RadioButton)findViewById(R.id.formular_rb_copii_da);
        rb_catDeDesPleci_rar=(RadioButton)findViewById(R.id.formular_rb_calatoresc_rar);
        rb_catDeDesPleci_des=(RadioButton)findViewById(R.id.formular_rb_calatoresc_des);
        rb_catDeDesPleci_foarteDes=(RadioButton)findViewById(R.id.formular_rb_calatoresc_foarte_des);
        rb_interesatOraseVizitate=(RadioButton)findViewById(R.id.formular_rb_oraseVizitate_da);

        rb_single.addTextChangedListener(fieldValidatorTextWatcher);
        rb_castorit.addTextChangedListener(fieldValidatorTextWatcher);
        rb_inRelatie.addTextChangedListener(fieldValidatorTextWatcher);

        //populez spinnerCategorii 1
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.spinnerCategorii, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategorii1.setAdapter(adapter);

        spinnerCategorii1.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here
                String[] list;
                list = getResources().getStringArray(R.array.spinnerCategorii);
                List<String> listaOrase = new ArrayList<>();
                for (int i = 0; i < list.length; i++) {
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
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this, R.array.spinnerBuget, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerBuget.setAdapter(adapter3);

        rb_interesatOraseVizitate.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                //populez spinner Orase
                spinnerOrase.setVisibility(VISIBLE);
                ArrayAdapter<CharSequence> adapter4= ArrayAdapter.createFromResource(getApplicationContext(), R.array.spinnerOrase, android.R.layout.simple_list_item_multiple_choice);
                adapter4.setDropDownViewResource(android.R.layout.simple_list_item_multiple_choice);
                spinnerOrase.setAdapter(adapter4);
            }
        });


    }

    public void verificaDateFormular(){
        waveView.setProgress(0);
          List<String> lista = new ArrayList<String>();

        if (rb_single.isChecked() || rb_castorit.isChecked() || rb_inRelatie.isChecked())
            lista.add("element");

        if (rb_catDeDesPleci_rar.isChecked() || rb_catDeDesPleci_des.isChecked() || rb_catDeDesPleci_foarteDes.isChecked())
            lista.add("element");


        if (rb_interesatOraseVizitate.isChecked())
            lista.add("element");

        if(!spinnerCategorii1.getSelectedItem().toString().equals("")){
            lista.add("element");
        }

        if(!spinnerCategorii2.getSelectedItem().toString().equals("")){
            lista.add("element");
        }
        if(!spinnerBuget.getSelectedItem().toString().equals("")){
            lista.add("element");
        }
        waveView.setProgress((int) (16.6 * lista.size()));

}

    TextWatcher fieldValidatorTextWatcher = new TextWatcher() {
        @Override
        public void afterTextChanged(Editable s) {
            verificaDateFormular();
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }
    };
}