package com.example.ramona.planyourtrip;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.provider.ContactsContract;
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
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ramona.planyourtrip.MultiLanguage.MultiLanguageHelper;
import com.example.ramona.planyourtrip.Util.Categorii;
import com.example.ramona.planyourtrip.Util.Database.DatabaseOperation;
import com.example.ramona.planyourtrip.Util.Locatii;
import static com.example.ramona.planyourtrip.GmailSender.Constante.idUtilizator;
import com.example.ramona.planyourtrip.Util.UserPreferences;
import com.john.waveview.WaveView;
import com.taishi.flipprogressdialog.FlipProgressDialog;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import io.paperdb.Paper;

import static android.widget.AdapterView.*;

public class Formular_interese extends AppCompatActivity{


    //data bse operation
    DatabaseOperation db = new DatabaseOperation();
    //
    TextView titlu;
    TextView statusRelatie;
    RadioButton rbSingur;
    RadioButton rbInRelatie;
    RadioButton rbCasatorit;
    TextView aveticopii;
    RadioButton rbDA;
    RadioButton rbNU;
    TextView catDeDesCalatoriti;
    RadioButton rbDes;
    RadioButton rbFoartedes;
    RadioButton rbRar;
    TextView tvSpinner1;
    TextView tvSpinner2;
    TextView tvSpinner3;

    Spinner spinnerCategorii1;
    Spinner spinnerCategorii2;
    Spinner spinnerBuget;

    TextView oraseVizitate;
    RadioButton rbDa2;
    RadioButton rbNu2;

    TextView tvAlegeOrtas;
    Button btnAlegeOras;

    Button btnFinalizare;
    ArrayList<Integer> listOraseSelectate = new ArrayList<>();
    List<Categorii> listaCategorii = new ArrayList<>();
    final List<String> listaOrase = new ArrayList<>();
    final List<String> listaOrase3 = new ArrayList<>();
    List<Locatii> locatiiList  = new ArrayList<>();

    TextView tvTari;
    WaveView waveView;
    Set<String> waveViewProgress = new HashSet<>();
    UserPreferences userPreferences= new UserPreferences();
    boolean[] checkedItems;
    //setari limba
    Context context;
    Resources resources;
    String operatie;
    //
    RadioGroup rGroupStatusRelatie;
    RadioGroup rGroupCopii;
    RadioGroup rGroupPlecari;
    RadioGroup rGroupOraseVizitate;
    List<String> listaBuget;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formular_interese);

        Bundle bu = getIntent().getExtras();
        operatie = bu.getString("operatie");


        waveView = (WaveView) findViewById(R.id.waveView2);
        waveView.setProgress(0);
        tvTari = (TextView)findViewById(R.id.tv_tari) ;
        spinnerCategorii1 = (Spinner) findViewById(R.id.formular_spinnerCategorii_1);
        spinnerCategorii2 = (Spinner) findViewById(R.id.formular_spinnerCategorii_2);
        spinnerBuget = (Spinner) findViewById(R.id.formular_spinerBuget);

        //setam contextl pentru multi lang pentru spinner
        context = getApplicationContext();
        context = MultiLanguageHelper.setLocale(context,(String) Paper.book().read("language"));

        //populez spinnerCategorii 1
        listaCategorii = db.selectCategorii();
        String[] list = new String[listaCategorii.size()];

        //list = context.getResources().getStringArray(R.array.spinnerCategorii);
        for(int i = 0;i<listaCategorii.size();i++){
            list[i] = listaCategorii.get(i).getNumeCategorie();
        }
        listaOrase.add("Selectati o categorie");
        for (int i = 0; i < list.length; i++) {
            listaOrase.add(list[i]);
        }
        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, listaOrase);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategorii1.setAdapter(adapter);
        //populez spinner 2 in functie de spinner 1
        spinnerCategorii1.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if (position!=0){
                    List<String> listaOrase2 = new ArrayList<>();
                    for(String lo :listaOrase){
                        listaOrase2.add(lo);
                    }
                    for(String lo :listaOrase2){
                        listaOrase3.add(lo);
                    }
                    waveViewProgress("spinnerCategorii1");
                    userPreferences.setCategoria1(listaOrase.get(position));
                    listaOrase2.remove(0);
                    listaOrase2.remove(position-1);
                    ArrayAdapter<String> adapter2 =
                            new ArrayAdapter<String>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, listaOrase2);
                    adapter2.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                    spinnerCategorii2.setAdapter(adapter2);
                    spinnerCategorii2.setVisibility(VISIBLE);
                   // DE ACTUALIZAT USER PREFERENCES CU ID-UL CZATEGORIEI ADUS DIN BAA DE DATE CAND INCARC SPINNERUL
                }  else{
                    waveViewProgress("0");
                    userPreferences.setCategoria1(listaOrase.get(position));
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
                userPreferences.setCategoria2(listaOrase3.get(position+1));
               // DE ACTUALIZAT USER PREFERENCES COMPLETAT CU ID-UL CATEGORIEI ADUSA DIN BAZA DE DATE
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        //populez spinner Buget
        String[] buget;
        buget = context.getResources().getStringArray(R.array.spinnerBuget);
        listaBuget = new ArrayList<>();
        for (int i = 0; i < buget.length; i++) {
            listaBuget.add(buget[i]);
        }
        ArrayAdapter<String> adapter3 =
                new ArrayAdapter<String>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, listaBuget);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
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
        rGroupStatusRelatie = (RadioGroup)findViewById(R.id.formular_radioGroup_status_relatie);
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
                    if(checkedRadioButton.getText().toString().equals(resources.getString(R.string.single))){
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
        rGroupCopii = (RadioGroup)findViewById(R.id.formular_radioGrup_aveti_copii);
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
                    if(checkedRadioButton.getText().toString().equals(resources.getString(R.string.copiiDa))){
                        userPreferences.setAreCopii("1");
                    }
                    if(checkedRadioButton.getText().toString().equals(resources.getString(R.string.copiiNu))){
                        userPreferences.setAreCopii("0");
                    }

                }
            }
        });

        // This will get the radiogroup
        rGroupPlecari = (RadioGroup)findViewById(R.id.formular_radioGroup_catDeDesPleci);
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
                    if(checkedRadioButton.getText().toString().equals(resources.getString(R.string.rar))){
                        userPreferences.setCatDeDesPleci("0");
                    }
                    if(checkedRadioButton.getText().toString().equals(resources.getString(R.string.des))){
                        userPreferences.setCatDeDesPleci("1");
                    }
                    if(checkedRadioButton.getText().toString().equals(resources.getString(R.string.foarte_des))){
                        userPreferences.setCatDeDesPleci("2");
                    }

                }
            }
        });
        // orse vizitate
        rGroupOraseVizitate = (RadioGroup)findViewById(R.id.formular_radioGroup_oraseVizitate);
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

                if(checkedRadioButton.getText().toString().equals(context.getResources().getString(R.string.copiiDa))){
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

        //mulilng
        allYouNeed();

        locatiiList = db.getLocation();

        if(operatie.equals("update")) {
            userPreferences = db.getUserPref(idUtilizator);
            checkedItems = new boolean[locatiiList.size()];
            for(int i =0;i<locatiiList.size();i++){
                String[] oraseUser = userPreferences.getOraseVizitate().split(",");
                for(int j=0;j<oraseUser.length;j++){
                    if(Integer.parseInt(oraseUser[j]) == locatiiList.get(i).getId())
                        checkedItems[i] = true;
                }
            }
            setUserPreferences();
        }
    }

    private void setUserPreferences(){
        rGroupStatusRelatie.check(rGroupStatusRelatie.getChildAt(Integer.parseInt(userPreferences.getStatusRelatie())).getId());
        rGroupCopii.check(rGroupCopii.getChildAt(Integer.parseInt(userPreferences.getAreCopii())).getId());
        rGroupPlecari.check(rGroupPlecari.getChildAt(Integer.parseInt(userPreferences.getCatDeDesPleci())).getId());
       // rGroupOraseVizitate.check(rGroupOraseVizitate.getChildAt(Integer.parseInt(userPreferences.getOraseVizitate())).getId());
        String[] selectedCategoria1 = userPreferences.getCategoria1().split(",");
        String[] selectedCategoria2 = userPreferences.getCategoria2().split(",");
        for(int i=0;i<selectedCategoria1.length;i++){
            spinnerCategorii1.setSelection(Integer.parseInt(selectedCategoria1[i]));
        }

        for(int i=0;i<selectedCategoria2.length;i++){
            spinnerCategorii2.setSelection(Integer.parseInt(selectedCategoria1[i]));
        }

        if(userPreferences.getBuget().contains("100"))
            spinnerBuget.setSelection(2);
        else if(userPreferences.getBuget().contains("301"))
            spinnerBuget.setSelection(3);
        else if(userPreferences.getBuget().contains("501"))
            spinnerBuget.setSelection(4);
        else if(userPreferences.getBuget().contains("Over") || userPreferences.getBuget().contains("Peste"))
            spinnerBuget.setSelection(5);
        else
            spinnerBuget.setSelection(1);
    }

    private void allYouNeed() {

        //preiau toate view-urile care trebuie traduce din clasa
        titlu = (TextView) findViewById(R.id.tv_titlu);
        statusRelatie = (TextView) findViewById(R.id.tv_status);
        rbSingur = (RadioButton) findViewById(R.id.formular_rb_singur);
        rbInRelatie = (RadioButton) findViewById(R.id.formular_rb_inRelatie);
        rbCasatorit = (RadioButton) findViewById(R.id.formular_rb_casatorit);
        aveticopii = (TextView) findViewById(R.id.tv_copii);
        rbDA = (RadioButton) findViewById(R.id.formular_rb_copii_da);
        rbNU = (RadioButton) findViewById(R.id.formular_rb_copii_nu);
        rbDa2=(RadioButton)findViewById(R.id.formular_rb_oraseVizitate_da);
        rbNu2=(RadioButton)findViewById(R.id.formular_rb_oraseVizitate_nu);
        tvSpinner1 = (TextView) findViewById(R.id.tvSpinner1);
        tvSpinner2 = (TextView) findViewById(R.id.tvSpinner2);
        tvSpinner3 = (TextView) findViewById(R.id.tvSpinner3);
        rbDes=(RadioButton)findViewById((R.id.formular_rb_calatoresc_des));
        rbRar=(RadioButton)findViewById((R.id.formular_rb_calatoresc_rar));
        rbFoartedes=(RadioButton)findViewById((R.id.formular_rb_calatoresc_foarte_des));

        //setari S[pinner --- ?

        oraseVizitate = (TextView) findViewById(R.id.tv_oraseVizitate);
        btnAlegeOras = (Button) findViewById(R.id.formular_btn_alegeorase);
        btnFinalizare = (Button) findViewById(R.id.formular_btn_finalizare);

        //pun toate id-urile stringurilor de care am nevoie
        setAllTextOnActivity();
        //navigation view
    }
    private void setAllTextOnActivity() {
        //setari de limba
        context = getApplicationContext();
        context = MultiLanguageHelper.setLocale(context,(String) Paper.book().read("language"));
        resources = context.getResources();

        titlu.setText(resources.getString(R.string.titlu));
        statusRelatie.setText(resources.getString(R.string.statusRelatie));
        rbSingur.setText(resources.getString(R.string.single));
        rbInRelatie.setText(resources.getString(R.string.inrelatie));
        rbCasatorit.setText(resources.getString(R.string.casatorit));
        aveticopii.setText(resources.getString(R.string.aveticopii));
        rbDA.setText(resources.getString(R.string.copiiDa));
        rbDa2.setText(resources.getString(R.string.copiiDa));
        rbNU.setText(resources.getString(R.string.copiiNu));
        rbNu2.setText(resources.getString(R.string.copiiNu));
        tvSpinner1.setText(resources.getString(R.string.sunteti_interesat));
        tvSpinner2.setText(resources.getString(R.string.daca_ai_putea_alege));
        tvSpinner3.setText(resources.getString(R.string.buget));
        rbDes.setText(resources.getString(R.string.des));
        rbRar.setText(resources.getString(R.string.rar));
        rbFoartedes.setText(resources.getString(R.string.foarte_des));
        oraseVizitate.setText(resources.getString(R.string.orase_vizitate));
        btnAlegeOras.setText(resources.getString(R.string.alegeOrase));
        btnFinalizare.setText(resources.getString(R.string.finalizare));
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
        final String[] listaOrase = new String[locatiiList.size()];
        for(int i =0;i<locatiiList.size();i++){
            listaOrase[i] = locatiiList.get(i).getNume();
        }

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
        String oraseVizitate = "";
        for(int i=0;i<checkedItems.length;i++){
            if(checkedItems[i]==true)
            {
                if(oraseVizitate.equals("")){
                    oraseVizitate+=String.valueOf(locatiiList.get(i).getId());
                }else{
                    oraseVizitate+= ",";
                    oraseVizitate+= String.valueOf(locatiiList.get(i).getId());
                }
            }

        }
        userPreferences.setOraseVizitate(oraseVizitate);

        for(int i = 0;i<listaCategorii.size();i++) {
            if(userPreferences.getCategoria1().equals(listaCategorii.get(i).getNumeCategorie())){
                userPreferences.setCategoria1(String.valueOf(listaCategorii.get(i).getId()));
            }
            if(userPreferences.getCategoria2().equals(listaCategorii.get(i).getNumeCategorie())){
                userPreferences.setCategoria2(String.valueOf(listaCategorii.get(i).getId()));
            }
        }

        if(operatie.equals("insert"))
            db.insertUserPref(userPreferences,idUtilizator);
        else
            db.updateUserPref(userPreferences);

        Intent explore= new Intent(this, Home.class);
        startActivity(explore);
    }
}