package com.example.ramona.planyourtrip;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ramona.planyourtrip.Util.Database.DatabaseOperation;
import com.example.ramona.planyourtrip.Util.Locatii;
import com.example.ramona.planyourtrip.Util.StoryObj;
import com.example.ramona.planyourtrip.stories.Story;

import static com.example.ramona.planyourtrip.GmailSender.Constante.assetManager;
import static com.example.ramona.planyourtrip.GmailSender.Constante.latitudine;
import static com.example.ramona.planyourtrip.GmailSender.Constante.locatiiList;

import java.util.ArrayList;
import java.util.List;

import static com.example.ramona.planyourtrip.GmailSender.Constante.idUtilizator;
import static com.example.ramona.planyourtrip.GmailSender.Constante.numeOras;
public class AddStory extends AppCompatActivity {

    DatabaseOperation db;
    //

    AutoCompleteTextView locatie;
    EditText titlu;
    EditText poveste;
    EditText facebook;
    EditText instagram;
    Integer idLocatieSelectata;
    Integer indexLocatieSelectata;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_story);
        //   getLocatii();

        List<String> lista = new ArrayList<>();
        locatie = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView_add_story_locatie);
        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, lista);
        locatie.setAdapter(adapter);

        if(numeOras!=null && !numeOras.equals("")){
            for(Locatii l:locatiiList){
                lista.add(l.getNume());
                if(l.getNume().equals(numeOras))
                    locatie.setText(numeOras);
            }
        } else{
            for(Locatii l:locatiiList){
                lista.add(l.getNume());
            }
        }
        //
        titlu = (EditText)findViewById(R.id.editText_add_story_titlu);
        poveste = (EditText)findViewById(R.id.editText_add_story_descriere);
        facebook = (EditText)findViewById(R.id.editText_add_story_facebook);
        instagram = (EditText)findViewById(R.id.editText_add_story_instagram);

    }

    public void saveStory(View view){
        String numeSelectat = locatie.getText().toString();
        for(Locatii l:locatiiList){
            if(l.getNume().equals(numeSelectat))
                idLocatieSelectata = l.getId();
        }
        StoryObj storyObj = new StoryObj();
        storyObj.setIdUser(idUtilizator);
        storyObj.setIdLocatie(idLocatieSelectata);
        storyObj.setTitlu(titlu.getText().toString());
        storyObj.setPoveste(poveste.getText().toString());
        storyObj.setFacebook(facebook.getText().toString());
        storyObj.setInstagram(instagram.getText().toString());
        db = new DatabaseOperation();
        try {
            db.addDBstory(storyObj);
            Toast.makeText(getApplicationContext(),"GOOD",Toast.LENGTH_SHORT).show();
        } catch (Exception ex){
            Toast.makeText(getApplicationContext(),"ERROR ADD STORY",Toast.LENGTH_SHORT).show();
        }
    }

}