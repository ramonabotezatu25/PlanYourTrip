package com.example.ramona.planyourtrip.LuggageList;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ramona.planyourtrip.R;

import java.util.ArrayList;

import static com.example.ramona.planyourtrip.GmailSender.Constante.luggageList;

public class Luggage extends AppCompatActivity {
    ArrayList<String> selectedItems;
    EditText editText;
    ArrayAdapter<String> adapter;
    ListView chl;
    String[] items;

    protected void onCreate(Bundle savedInstanceState) {
        //TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_luggage);
        //create an ArrayList object to store selected items
        selectedItems=new ArrayList<String>();
        editText = (EditText)findViewById(R.id.plain_text_input);
    }



    public void onStart(){
        super.onStart();
        //create an instance of ListView
        chl=(ListView) findViewById(R.id.checkable_list);
        //set multiple selection mode
        chl.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        items = new String[50];
        luggageList.add("English");
        luggageList.add("USA");
        luggageList.add("Romania");
        for(int i=0;i<luggageList.size();i++){
            items[i]=luggageList.get(i);
        }
        //supply data itmes to ListView
        adapter=new ArrayAdapter<String>(this,R.layout.checkable_list_layout,R.id.txt_title,items);
        chl.setAdapter(adapter);

        //set OnItemClickListener
        chl.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // selected item
                String selectedItem = ((TextView) view).getText().toString();
                if(selectedItems.contains(selectedItem))
                    selectedItems.remove(selectedItem); //remove deselected item from the list of selected items
                else
                    selectedItems.add(selectedItem); //add selected item to the list of selected items

            }

        });
    }

    public void showSelectedItems(View view){
        String selItems="";
        for(String item:selectedItems){
            if(selItems=="")
                selItems=item;
            else
                selItems+="/"+item;
        }
        Toast.makeText(this, selItems, Toast.LENGTH_LONG).show();
    }

    public void addItems(View view){

        luggageList.add(editText.getText().toString());

        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }

}
