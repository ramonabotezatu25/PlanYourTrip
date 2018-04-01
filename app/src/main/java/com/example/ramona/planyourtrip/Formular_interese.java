package com.example.ramona.planyourtrip;

import android.content.DialogInterface;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ramona.planyourtrip.Util.UserPreferences;
import com.john.waveview.WaveView;
import com.taishi.flipprogressdialog.FlipProgressDialog;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static android.widget.AdapterView.*;

public class Formular_interese extends AppCompatActivity{

    Spinner spinnerCategorii1;
    Spinner spinnerCategorii2;
    Spinner spinnerBuget;
    ArrayList<Integer> listOraseSelectate = new ArrayList<>();
    TextView tvTari;
    WaveView waveView;
    Set<String> waveViewProgress = new HashSet<>();
    UserPreferences userPreferences= new UserPreferences();
    boolean[] checkedItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formular_interese);

        waveView = (WaveView) findViewById(R.id.waveView2);
        waveView.setProgress(0);
        tvTari = (TextView)findViewById(R.id.tv_tari) ;
        spinnerCategorii1 = (Spinner) findViewById(R.id.formular_spinnerCategorii_1);
        spinnerCategorii2 = (Spinner) findViewById(R.id.formular_spinnerCategorii_2);
        spinnerBuget = (Spinner) findViewById(R.id.formular_spinerBuget);


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
                if (position!=0){
                    waveViewProgress("spinnerCategorii1");
                    listaOrase.remove(0);
                    listaOrase.remove(position-1);
                    ArrayAdapter<String> adapter2 =
                            new ArrayAdapter<String>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, listaOrase);
                    adapter2.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                    spinnerCategorii2.setAdapter(adapter2);
                    spinnerCategorii2.setVisibility(VISIBLE);
                   // DE ACTUALIZAT USER PREFERENCES CU ID-UL CZATEGORIEI ADUS DIN BAA DE DATE CAND INCARC SPINNERUL
                }  else{
                    waveViewProgress("0");
                    spinnerCategorii2.setVisibility(INVISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }

        });

        spinnerCategorii2.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
               waveViewProgress("spinnerCategorii2");
               // DE ACTUALIZAT USER PREFERENCES COMPLETAT CU ID-UL CATEGORIEI ADUSA DIN BAZA DE DATE
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        //populez spinner Buget
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this, R.array.spinnerBuget, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerBuget.setAdapter(adapter3);




        spinnerBuget.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                if (position!=0){
                    waveViewProgress("spinnerBuget");
                    userPreferences.setBuget(spinnerBuget.getSelectedItem().toString());}
                else
                    waveViewProgress("0");
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    //radio grup

        // This will get the radiogroup
        RadioGroup rGroupStatusRelatie = (RadioGroup)findViewById(R.id.formular_radioGroup_status_relatie);
        rGroupStatusRelatie.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                // This will get the radiobutton that has changed in its check state
                RadioButton checkedRadioButton = (RadioButton)group.findViewById(checkedId);
                // This puts the value (true/false) into the variable
                boolean isChecked = checkedRadioButton.isChecked();
                // If the radiobutton that has changed in check state is now checked...
                if (isChecked)
                {
                    waveViewProgress("rGroupStatusRelatie");
                    if(checkedRadioButton.getText().toString().equals(getResources().getString(R.string.single))){
                        userPreferences.setStatusRelatie("1");
                    }
                    if(checkedRadioButton.getText().toString().equals(getResources().getString(R.string.inrelatie))){
                        userPreferences.setStatusRelatie("2");
                    }
                    if(checkedRadioButton.getText().toString().equals(getResources().getString(R.string.casatorit))){
                        userPreferences.setStatusRelatie("3");
                    }


                }
            }
        });

        // This will get the radiogroup
        RadioGroup rGroupCopii = (RadioGroup)findViewById(R.id.formular_radioGrup_aveti_copii);
        rGroupCopii.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                // This will get the radiobutton that has changed in its check state
                RadioButton checkedRadioButton = (RadioButton)group.findViewById(checkedId);
                // This puts the value (true/false) into the variable
                boolean isChecked = checkedRadioButton.isChecked();
                // If the radiobutton that has changed in check state is now checked...
                if (isChecked)
                {

                    waveViewProgress("rGroupCopii");
                    if(checkedRadioButton.getText().toString().equals(getResources().getString(R.string.copiiDa))){
                        userPreferences.setAreCopii("1");
                    }
                    if(checkedRadioButton.getText().toString().equals(getResources().getString(R.string.copiiNu))){
                        userPreferences.setAreCopii("0");
                    }

                }
            }
        });

        // This will get the radiogroup
        RadioGroup rGroupPlecari = (RadioGroup)findViewById(R.id.formular_radioGroup_catDeDesPleci);
        rGroupPlecari.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                // This will get the radiobutton that has changed in its check state
                RadioButton checkedRadioButton = (RadioButton)group.findViewById(checkedId);
                // This puts the value (true/false) into the variable
                boolean isChecked = checkedRadioButton.isChecked();
                // If the radiobutton that has changed in check state is now checked...
                if (isChecked)
                {
                    waveViewProgress("rGroupPlecari");
                    if(checkedRadioButton.getText().toString().equals(getResources().getString(R.string.rar))){
                        userPreferences.setCatDeDesPleci("0");
                    }
                    if(checkedRadioButton.getText().toString().equals(getResources().getString(R.string.des))){
                        userPreferences.setCatDeDesPleci("1");
                    }
                    if(checkedRadioButton.getText().toString().equals(getResources().getString(R.string.foarte_des))){
                        userPreferences.setCatDeDesPleci("2");
                    }

                }
            }
        });
        // This will get the radiogroup
        final RadioGroup rGroupOraseVizitate = (RadioGroup)findViewById(R.id.formular_radioGroup_oraseVizitate);
        rGroupOraseVizitate.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                // This will get the radiobutton that has changed in its check state
                RadioButton checkedRadioButton = (RadioButton)group.findViewById(checkedId);
                // This puts the value (true/false) into the variable
                boolean isChecked = checkedRadioButton.isChecked();
                // If the radiobutton that has changed in check state is now checked...
                Button btn= (Button)(findViewById(R.id.formular_btn_alegeorase));
                TextView tv=(TextView)findViewById(R.id.tv_oraseVizitate);

                if(checkedRadioButton.getText().toString().equals(getResources().getString(R.string.copiiDa))){
                    tv.setVisibility(VISIBLE);
                    btn.setVisibility(VISIBLE);
                }else{
                    tv.setVisibility(INVISIBLE);
                    btn.setVisibility(INVISIBLE);
                }
                if (isChecked)
                {
                    waveViewProgress("rGroupOraseVizitate");
                }
            }
        });

    }

    public void waveViewProgress(String chkGroup){
        if(!chkGroup.equals("0")) {
            waveView.setProgress(0);
            waveViewProgress.add(chkGroup);
            waveView.setProgress((int) (14.2 * waveViewProgress.size()));
        }else{
            if(waveViewProgress.size()==0)
                waveView.setProgress(0);
            else
                waveView.setProgress((int) (14.2 * (waveViewProgress.size()-1) ));
        }

    }


    public void deschideAlegeOras(View view){
        final String[] listaOrase = getResources().getStringArray(R.array.spinnerOrase);
        if(checkedItems==null){
            checkedItems=new boolean[listaOrase.length];
        }
        AlertDialog.Builder mBuilder= new AlertDialog.Builder(Formular_interese.this);
        mBuilder.setTitle("Alegeti orasele vizitate");
        mBuilder.setMultiChoiceItems(listaOrase, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int position, boolean isChecked) {
                if(isChecked){
                    if(!listOraseSelectate.contains(position)){
                        listOraseSelectate.add(position);
                    }
                    else {
                        listOraseSelectate.remove(position);
                    }
                }
            }
        });
        mBuilder.setCancelable(false);
        mBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                String item="";
                for(int i=0; i< listOraseSelectate.size();i++){
                    item=item + listaOrase[listOraseSelectate.get(i)];
                    if(i!=listOraseSelectate.size()-1){
                        item=item+ ", ";
                    }
                }
                tvTari.setText(item);
                userPreferences.setOraseVizitate(tvTari.getText().toString());
            }
        });
        mBuilder.setNegativeButton("dismiss", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        mBuilder.setNeutralButton("Clear all", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                for(int i=0;i<checkedItems.length;i++){
                    checkedItems[i]=false;
                    listOraseSelectate.clear();
                    tvTari.setText("");
                }
            }
        });

        AlertDialog mDialog= mBuilder.create();
        mDialog.show();
    }


    //metoda creare User Preferences
    public void creeazaUserPreferences(View view)
    {
        //INSERARE USER PREFERENCES IN TABELA
        System.out.print(userPreferences);
    }
}