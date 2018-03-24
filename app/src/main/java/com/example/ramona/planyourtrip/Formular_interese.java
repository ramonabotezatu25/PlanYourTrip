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
import android.widget.Toast;

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
    Spinner spinnerOrase;
    ArrayList<Integer> listOraseSelectate = new ArrayList<>();
    WaveView waveView;
    RadioButton rb_interesatOraseVizitate;
    Set<String> waveViewProgress = new HashSet<>();

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

        rb_interesatOraseVizitate=(RadioButton)findViewById(R.id.formular_rb_oraseVizitate_da);
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
                }
            }
        });
        // This will get the radiogroup
        RadioGroup rGroupOraseVizitate = (RadioGroup)findViewById(R.id.formular_radioGroup_oraseVizitate);
        rGroupOraseVizitate.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
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
                    waveViewProgress("rGroupOraseVizitate");
                }
            }
        });
    }

    public void waveViewProgress(String chkGroup){
        waveView.setProgress(0);
        waveViewProgress.add(chkGroup);
        waveView.setProgress((int) (12.5*waveViewProgress.size()));
    }

}