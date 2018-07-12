package com.example.ramona.planyourtrip.LuggageList;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ramona.planyourtrip.R;
import com.example.ramona.planyourtrip.Util.Database.DatabaseOperation;
import com.example.ramona.planyourtrip.Util.LuggageList;

import java.util.ArrayList;

import io.paperdb.Paper;

import static com.example.ramona.planyourtrip.GmailSender.Constante.locatiiList;
import static com.example.ramona.planyourtrip.GmailSender.Constante.luggageList;
import static com.example.ramona.planyourtrip.GmailSender.Constante.idUtilizator;
import static com.example.ramona.planyourtrip.GmailSender.Constante.luggageListSize;
import static com.example.ramona.planyourtrip.GmailSender.Constante.luggageListUser;
import static com.example.ramona.planyourtrip.GmailSender.Constante.luggageListChecked;

public class Luggage extends AppCompatActivity {
    EditText editText;
    ArrayAdapter<String> adapter;
    ListView chl;
    String[] items;
    DatabaseOperation databaseOperation = new DatabaseOperation();

    protected void onCreate(Bundle savedInstanceState) {
        //TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_luggage);
        //create an ArrayList object to store selected items
        editText = (EditText)findViewById(R.id.plain_text_input);
        chl=(ListView) findViewById(R.id.checkable_list);
        //set multiple selection mode
        chl.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);


        for(LuggageList luggage : luggageListUser){
            if(!luggageList.contains(luggage.getLuggageName()))
                luggageList.add(luggage.getLuggageName());
            if(luggage.getChecked()==1){
                luggageListChecked.add(luggage.getLuggageName());
            }
        }

        //supply data itmes to ListView
        adapter=new ArrayAdapter<String>(this,R.layout.checkable_list_layout,R.id.txt_title,luggageList);
        chl.setAdapter(adapter);

        //set OnItemClickListener
        chl.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // selected item
                String selectedItem = ((TextView) view).getText().toString();
                if(luggageListChecked.contains(selectedItem)){
                    luggageListChecked.remove(selectedItem);
                    new TestAsyncUserProfile().execute(selectedItem,"UNCHECK");
                } else{
                    luggageListChecked.add(selectedItem);
                    new TestAsyncUserProfile().execute(selectedItem,"CHECK");
                }

            }

        });

        for(String s : luggageListChecked ){
            Integer indexOfItem = luggageList.indexOf(s);
            chl.setItemChecked(indexOfItem,true);
        }
    }


    public void showSelectedItems(View view){
        String selItems="";
        for(String item:luggageListChecked){
            if(selItems=="")
                selItems=item;
            else
                selItems+="/"+item;
        }
        Toast.makeText(this, selItems, Toast.LENGTH_LONG).show();
    }

    public void addItems(View view){

        try{
            new TestAsyncUserProfile().execute(editText.getText().toString(),"ADD");
            if(!luggageList.contains(editText.getText().toString())){
                luggageList.add(editText.getText().toString());
            }

        }
        catch(Exception ex){

        }finally {
            Intent intent = getIntent();
            finish();
            startActivity(intent);
        }

    }

    public void removeItems(View view){


        if(luggageListChecked.size()==0){
            Toast.makeText(this, "Please select an item", Toast.LENGTH_LONG).show();
            Intent intent = getIntent();
            finish();
            startActivity(intent);
        }else{
            if(luggageListChecked.size()<2){
            for(String item:luggageListChecked){
                Integer indexOfObject = luggageList.indexOf(item)+1;
                if(indexOfObject>luggageListSize){
                    Integer idLuggage =luggageListUser.get(indexOfObject-1-luggageListSize).getId();
                    luggageList.remove(item);
                    luggageListChecked.remove(item);
                    luggageListUser.remove(indexOfObject-1-luggageListSize);
                    new TestAsyncUserProfile().execute(idLuggage.toString(),"REMOVE");
                    Intent intent = getIntent();
                    finish();
                    startActivity(intent);
                }else{
                    Toast.makeText(this, "This item can't be deleted!Is our standard list", Toast.LENGTH_LONG).show();
                }
            }
            }else{
                Toast.makeText(this, "Select only one item.", Toast.LENGTH_LONG).show();
            }
        }
    }

}


class TestAsyncUserProfile extends AsyncTask<String, Integer, String> {
    String TAG = getClass().getSimpleName();

    protected void onPreExecute() {
        super.onPreExecute();
        Log.d(TAG + " PreExceute", "On pre Exceute......");
    }

    protected String doInBackground(String... arg0) {
        Log.d(TAG + " DoINBackGround", "On doInBackground...");
        DatabaseOperation databaseOperation = new DatabaseOperation();
        if(arg0[1].toString().equals("REMOVE")){
            databaseOperation.removeItem(Integer.parseInt(arg0[0].toString()));
            luggageListUser =  databaseOperation.getStandardLuggageUser(idUtilizator);
        }else if(arg0[1].toString().equals("CHECK")){
            databaseOperation.updateLuggageItem(luggageList.indexOf(arg0[0].toString()),1);
        }
        else if(arg0[1].toString().equals("UNCHECK")){
            databaseOperation.updateLuggageItem(luggageList.indexOf(arg0[0].toString()),0);
        } else{
            databaseOperation.addLuggage(arg0[0].toString(), idUtilizator);
            luggageListUser =  databaseOperation.getStandardLuggageUser(idUtilizator);
        }

        return "You are at PostExecute";
    }

    protected void onProgressUpdate(Integer... a) {
        super.onProgressUpdate(a);
        Log.d(TAG + " onProgressUpdate", "You are in progress update ... " + a[0]);
    }

    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        Log.d(TAG + " onPostExecute", "" + result);
    }
}