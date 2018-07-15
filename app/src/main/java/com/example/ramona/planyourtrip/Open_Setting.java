package com.example.ramona.planyourtrip;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ramona.planyourtrip.GmailSender.Constante;
import com.example.ramona.planyourtrip.MultiLanguage.MultiLanguageHelper;
import com.example.ramona.planyourtrip.Util.Constants;
import com.example.ramona.planyourtrip.Util.Database.DatabaseOperation;
import com.example.ramona.planyourtrip.Util.Encrypt.Encrypt;

import io.paperdb.Paper;

import static com.example.ramona.planyourtrip.GmailSender.Constante.initVector;
import static com.example.ramona.planyourtrip.GmailSender.Constante.key;
import static com.example.ramona.planyourtrip.MultiLanguage.Language.setDefaultLanguage;
import static com.example.ramona.planyourtrip.GmailSender.Constante.idUtilizator;

public class Open_Setting extends AppCompatActivity {
    LinearLayout linearLayout;
    //setari de limba
    Context context;
    Resources resources;
    //elemente
    TextView titlu;
    TextView btnParola;
    TextView btnForm;
    EditText parolaCurenta;
    EditText parolaNoua;
    Button updateParola;
    //db
    DatabaseOperation db=new DatabaseOperation();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open__setting);
        linearLayout=(LinearLayout)findViewById(R.id.linearLaout_SettingsParola);
        allYouNeed();
    }

    public void settingsPreferences(View view){
        Intent settings = new Intent(this, Formular_interese.class);
        settings.putExtra("operatie","update");
        startActivity(settings);
        // in caz ca a fost apasat update pass
        linearLayout.setVisibility(View.INVISIBLE);
        //setari de limba
        setDefaultLanguage(this);
        allYouNeed();
    }

    public void schimbaParolaTV(View view){
        linearLayout.setVisibility(View.VISIBLE);

    }
    //update in bd
    public void updatePassword(View view){

        String parola= parolaNoua.getText().toString();
        String parolaActuala= parolaCurenta.getText().toString();
        Encrypt encryptor = new Encrypt();
        String parolaNoua = encryptor.encrypt(key,initVector,parola);
        String parolaVeche = encryptor.encrypt(key,initVector,parolaActuala);
        db.updateUserPassword(idUtilizator, parolaNoua,parolaVeche);
        Toast.makeText(getApplicationContext(),R.string.updateSucces, Toast.LENGTH_SHORT).show();
        linearLayout.setVisibility(View.INVISIBLE);
    }

    private void allYouNeed() {

        //preiau toate view-urile care trebuie traduce din clasa
        titlu=(TextView)findViewById(R.id.textView_Settings_Title);
        btnParola=(TextView)findViewById(R.id.textView_Settings_update_pass);
        btnForm=(TextView)findViewById(R.id.textView_update_form);
        parolaCurenta=(EditText)findViewById(R.id.editText_Settings_currentPassword);
        parolaNoua=(EditText)findViewById(R.id.editText_Settings_newPassword);
        updateParola=(Button)findViewById(R.id.btn_Settings_updatePasswword);
        //pun toate id-urile stringurilor de care am nevoie
        setAllTextOnActivity();

    }

    private void setAllTextOnActivity() {
        //setari de limba
        context = getApplicationContext();
        context = MultiLanguageHelper.setLocale(context,(String) Paper.book().read("language"));
        resources = context.getResources();

        titlu.setText(resources.getString(R.string.settingsTitle));
        btnParola.setText(resources.getString(R.string.changePassword));
        btnForm.setText(resources.getString(R.string.changeForm));
        parolaNoua.setHint(resources.getString(R.string.newPassword));
        parolaCurenta.setHint(resources.getString(R.string.currentPassword));
        updateParola.setText(resources.getString(R.string.updatePass));


    }
}
