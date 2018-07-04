package com.example.ramona.planyourtrip;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import com.example.ramona.planyourtrip.Util.Database.DatabaseOperation;
import com.example.ramona.planyourtrip.Util.Locatii;
import com.example.ramona.planyourtrip.Util.StoryObj;
import com.example.ramona.planyourtrip.stories.Story;

import java.util.ArrayList;
import java.util.List;

import static com.example.ramona.planyourtrip.GmailSender.Constante.idUtilizator;

public class AddStory extends AppCompatActivity {

    DatabaseOperation db;
    List<Locatii> listaLocatii = new ArrayList<>();
    EditText descriere;

    //

    AutoCompleteTextView locatie;
    EditText titlu;
    EditText poveste;
    EditText facebook;
    EditText instagram;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_story);
        //   getLocatii();
        locatie = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView_add_story_locatie);
        List<String> lista = new ArrayList<>();
        lista.add("New York");
        lista.add("Paris");
        lista.add("Londra");
        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, lista);
        locatie.setAdapter(adapter);

        //
        titlu = (EditText)findViewById(R.id.editText_add_story_titlu);
        poveste = (EditText)findViewById(R.id.editText_add_story_descriere);
        facebook = (EditText)findViewById(R.id.editText_add_story_facebook);
        instagram = (EditText)findViewById(R.id.editText_add_story_instagram);

    }

    public void saveStory(View view){
        StoryObj storyObj = new StoryObj();
        storyObj.setIdUser(idUtilizator);
        storyObj.setIdLocatie(1);
        storyObj.setTitlu(titlu.getText().toString());
        storyObj.setPoveste(poveste.getText().toString());
        storyObj.setFacebook(facebook.getText().toString());
        storyObj.setInstagram(instagram.getText().toString());
        db = new DatabaseOperation();

        db.addDBstory(storyObj);
    }

}