package com.example.ramona.planyourtrip;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ramona.planyourtrip.MultiLanguage.MultiLanguageHelper;
import com.example.ramona.planyourtrip.Util.Constants;
import com.example.ramona.planyourtrip.Util.Database.DatabaseOperation;
import com.example.ramona.planyourtrip.Util.Encrypt.Encrypt;
import com.example.ramona.planyourtrip.Util.User;
import com.example.ramona.planyourtrip.Util.UserPreferences;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import static com.example.ramona.planyourtrip.GmailSender.Constante.idUtilizator;
import static com.example.ramona.planyourtrip.GmailSender.Constante.initVector;
import static com.example.ramona.planyourtrip.GmailSender.Constante.key;
import static com.example.ramona.planyourtrip.GmailSender.Constante.locatiiList;
import static com.example.ramona.planyourtrip.GmailSender.Constante.userPreferencesForHome;
import org.json.JSONException;
import org.json.JSONObject;


import java.util.Arrays;

import io.paperdb.Paper;

public class LogIn extends AppCompatActivity{
    //databse
    DatabaseOperation db = new DatabaseOperation();
    //facebook
    CallbackManager callbackManager;
    //retine user name si parola
    SharedPreferences preferences;

    EditText email;
    EditText parola;
    TextView tvSignUpIntrebare;
    TextView tvSignUpHere;
    Button buttonLogin;
    //setari limba
    Context context;
    Resources resources;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        //
        new TestAsync().execute();
        //
        email = (EditText) findViewById(R.id.editText_name_logIn);
        parola = (EditText) findViewById(R.id.editText_password_logIn);

        //facebook Auth
        //callback
        callbackManager = CallbackManager.Factory.create();
        final LoginButton loginButton = (LoginButton) findViewById(R.id.login_button);
        if(AccessToken.getCurrentAccessToken() == null){

        LoginManager.getInstance().registerCallback(callbackManager,
                    new FacebookCallback<LoginResult>() {
                        @Override
                        public void onSuccess(LoginResult loginResult) {
                            // App code
                            loginButton.setReadPermissions(Arrays.asList("public_profile", "email"));

                            String accesData = loginResult.getAccessToken().getToken();
                            GraphRequest request = GraphRequest.newMeRequest(
                                    loginResult.getAccessToken(),
                                    new GraphRequest.GraphJSONObjectCallback() {
                                        @Override
                                        public void onCompleted(
                                                JSONObject object,
                                                GraphResponse response) {
                                            // Application code
                                            getFacebookData(object);
                                        }
                                    });
                            Bundle parameters = new Bundle();
                            parameters.putString("fields", "name,email");
                            request.setParameters(parameters);
                            request.executeAsync();
                        }

                        @Override
                        public void onCancel() {
                            // App code
                            Toast.makeText(getApplicationContext(), "V-ati conectat cu FACEBOOK", Toast.LENGTH_SHORT).show();

                        }

                        @Override
                        public void onError(FacebookException exception) {
                            // App code
                            Toast.makeText(getApplicationContext(), "V-ati conectat cu FACEBOOK", Toast.LENGTH_SHORT).show();

                        }
                    });

        }else{
            Intent activity  = new Intent(this,Home.class);
            startActivity(activity);
        }

        //mulilng
        allYouNeed();
        //shared pref
        preferences=getSharedPreferences(Constants.SHAREDPREFERENCES2_NAME, MODE_PRIVATE);

        final String u=preferences.getString(Constants.LOGIN_NAME, Constants.FARA_NUME_LOGIN);
        final String p=preferences.getString(Constants.PAROLA_NAME, Constants.FARA_NUME_LOGIN);

        if(!u.equals(null))
        {
            email.setText(u.toString());
        }
        if(!p.equals(null))
        {
            parola.setText(p.toString());
        }

    }



    private void allYouNeed() {

        //preiau toate view-urile care trebuie traduce din clasa
        tvSignUpIntrebare = (TextView) findViewById(R.id.login_tv_signup_intrebare);
        tvSignUpHere = (TextView) findViewById(R.id.login_tv_signUpHere);
        email = (EditText) findViewById(R.id.editText_name_logIn);
        parola = (EditText) findViewById(R.id.editText_password_logIn);
        buttonLogin = (Button) findViewById(R.id.login_btn_autentificare);
        //pun toate id-urile stringurilor de care am nevoie
        setAllTextOnActivity();
        //navigation view
    }
    private void setAllTextOnActivity() {
        //setari de limba
        context = getApplicationContext();
        context = MultiLanguageHelper.setLocale(context,(String) Paper.book().read("language"));
        resources = context.getResources();
        email.setHint(resources.getString(R.string.name));
        parola.setHint(resources.getString(R.string.password));
        tvSignUpIntrebare.setText(resources.getString(R.string.questionSignUp));
        tvSignUpHere.setText(resources.getString(R.string.signUpHere));
        buttonLogin.setText(resources.getString(R.string.login));
    }



    private Bundle getFacebookData(JSONObject object) {
        Bundle nume =null;
        try {
            String email = object.getString("email");
            String name = object.getString("name");
            User facebookUser = new User();
            facebookUser.setEmail(email);
            facebookUser.setParola("FACEBOOK" + name);
            facebookUser.setCodConfirmare("FACEBOOK");
            facebookUser.setNume(name);
            db.insertUtilizator(facebookUser);
            Intent explore= new Intent(this, Home.class);
            startActivity(explore);


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return nume;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void login(View view){
        final ProgressDialog dialog = ProgressDialog.show(this, "Loading data...", "Loading data...",
                true);
        dialog.show();
        //preia din baza orase
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                dialog.dismiss();
            }
        }, 10000);
        String emailU = email.getText().toString();
        String parolaU = parola.getText().toString();
        Encrypt encryptor = new Encrypt();
        String parola = encryptor.encrypt(key,initVector,parolaU);

        User utilizatorActiv = db.logInUtilizator(emailU,parola);
        if(utilizatorActiv!=null && utilizatorActiv.getActiv()==1){
            idUtilizator = utilizatorActiv.getId();
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString(Constants.LOGIN_NAME, emailU);
            editor.putString(Constants.PAROLA_NAME, parolaU);
            editor.commit();
            UserPreferences userPref = db.getUserPref(idUtilizator);
            if(userPref!=null){
                Intent explore= new Intent(this, Home.class);
                userPreferencesForHome = userPref;
                startActivity(explore);
            }else{
                Intent userPreferences= new Intent(this, Formular_interese.class);
                userPreferences.putExtra("operatie","insert");
                startActivity(userPreferences);
            }


        }else{
            Toast.makeText(this,"Parola gresita sau utilizator inactiv!",Toast.LENGTH_SHORT).show();
        }
    }
    //deschide SignUp Form
    public void deschideSignUp(View View){
        Intent intent= new Intent(LogIn.this, SignUpActivity.class);
        startActivity(intent);
    }
}

class TestAsync extends AsyncTask<Void, Integer, String> {
    String TAG = getClass().getSimpleName();

    protected void onPreExecute() {
        super.onPreExecute();
        Log.d(TAG + " PreExceute", "On pre Exceute......");
    }

    protected String doInBackground(Void... arg0) {
        Log.d(TAG + " DoINBackGround", "On doInBackground...");

         DatabaseOperation databaseOperation = new DatabaseOperation();
         locatiiList = databaseOperation.getLocation();

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