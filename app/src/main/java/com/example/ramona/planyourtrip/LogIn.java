package com.example.ramona.planyourtrip;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaCas;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ramona.planyourtrip.Util.Database.DatabaseOperation;
import com.example.ramona.planyourtrip.Util.User;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.Arrays;

import br.com.bloder.magic.internal.MagicAnimation;
import br.com.bloder.magic.view.MagicButton;

public class LogIn extends AppCompatActivity {
    //databse
    DatabaseOperation db = new DatabaseOperation();
    //facebook
    CallbackManager callbackManager;

    MagicButton magicButton;
    MagicButton magicButton2;
    EditText email;
    EditText parola;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        //
        email = (EditText) findViewById(R.id.editText_name_logIn);
        parola = (EditText) findViewById(R.id.editText_password_logIn);

        //
        magicButton=(MagicButton)findViewById(R.id.magic_button1);
        magicButton.setLeft(1);
        magicButton2=(MagicButton)findViewById(R.id.magic_button2);
        magicButton.setLeft(0);



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

        //magic
        magicButton2.setMagicButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "V-ati conectat cu GOOGLE + ", Toast.LENGTH_SHORT).show();
            }
        });
        magicButton.setMagicButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Toast.makeText(getApplicationContext(), "V-ati conectat cu FACEBOOK", Toast.LENGTH_SHORT).show();
            }
        });
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
        String emailU = email.getText().toString();
        String parolaU = parola.getText().toString();
        Integer utilizatorActiv = db.logInUtilizator(emailU,parolaU);
        if(utilizatorActiv==1){
            Intent explore= new Intent(this, Home.class);
            startActivity(explore);
        }
    }

    //deschide SignUp Form
    public void deschideSignUp(View View){
        Intent intent= new Intent(LogIn.this, SignUpActivity.class);
        startActivity(intent);
    }
}
