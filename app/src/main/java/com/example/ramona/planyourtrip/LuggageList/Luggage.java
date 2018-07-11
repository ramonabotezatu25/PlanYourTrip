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

import java.util.ArrayList;

import io.paperdb.Paper;

import static com.example.ramona.planyourtrip.GmailSender.Constante.luggageList;
import static com.example.ramona.planyourtrip.GmailSender.Constante.idUtilizator;

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
        chl=(ListView) findViewById(R.id.checkable_list);
        //set multiple selection mode
        chl.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        //supply data itmes to ListView
        adapter=new ArrayAdapter<String>(this,R.layout.checkable_list_layout,R.id.txt_title,luggageList);
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
        new TestAsyncUserProfile().execute(editText.getText().toString());
        Intent intent = getIntent();
        finish();
        startActivity(intent);
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
        Integer id=databaseOperation.addLuggage(arg0[0].toString(),idUtilizator);
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