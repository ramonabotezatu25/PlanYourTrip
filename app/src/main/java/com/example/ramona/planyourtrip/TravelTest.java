package com.example.ramona.planyourtrip;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ramona.planyourtrip.GmailSender.SendEmail;
import com.example.ramona.planyourtrip.Intro.IntroActivity;
import com.example.ramona.planyourtrip.MultiLanguage.MultiLanguageHelper;
import com.example.ramona.planyourtrip.Profile.UserProfile;
import com.example.ramona.planyourtrip.Util.Database.Background;
import com.example.ramona.planyourtrip.Util.Database.DatabaseOperation;
import com.example.ramona.planyourtrip.Util.Locatii;
import com.example.ramona.planyourtrip.Util.VerificaEmpty;
import com.example.ramona.planyourtrip.exploreCity.ExploreMyCity;
import com.example.ramona.planyourtrip.maps.YourPlace;
import com.example.ramona.planyourtrip.stories.Story;

import java.util.List;
import java.util.Map;

import io.paperdb.Paper;
import static com.example.ramona.planyourtrip.GmailSender.Constante.locatiiList;

import static com.example.ramona.planyourtrip.MultiLanguage.Language.setDefaultLanguage;

public class TravelTest extends AppCompatActivity {
    //setari de limba
    Context context;
    Resources resources;
    //
    TextView textView ;
    EditText editText;
    TextInputLayout hintName;
    TextInputLayout hintPass;
    //databse
    DatabaseOperation db = new DatabaseOperation();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel);

        setDefaultLanguage(this);
        allYouNeed();

    }

    private void allYouNeed() {

        //preiau toate view-urile care trebuie traduce din clasa
        textView = (TextView) findViewById(R.id.textView);
        hintName = (TextInputLayout)findViewById(R.id.textInput1);
        hintPass = (TextInputLayout)findViewById(R.id.textInput2);
        //pun toate id-urile stringurilor de care am nevoie
        setAllTextOnActivity();
        //navigation view
        navView();
    }

    private void navView(){

        //navigation view
        final BottomNavigationView bottomNavigationView;
        bottomNavigationView = (BottomNavigationView)findViewById(R.id.nav_view);
        Menu menu = bottomNavigationView.getMenu();
        for (int i = 0; i < bottomNavigationView.getMenu().size(); i++) {
            MenuItem menuItem = menu.getItem(i);
            if (i == 0)
                menuItem.setTitle(resources.getString(R.string.nav_home));
            if (i == 1)
                menuItem.setTitle(resources.getString(R.string.nav_explore));
            if (i == 2)
                menuItem.setTitle(resources.getString(R.string.nav_profile));
        }
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @RequiresApi(api = Build.VERSION_CODES.ECLAIR)
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_home:
                        startNewActivity(Home.class);
                        overridePendingTransition(0, 0);
                        break;
                    case R.id.nav_explore:
                        startNewActivity(Explore.class);
                        overridePendingTransition(0, 0);
                        break;
                    case R.id.nav_profile:
                        startNewActivity(UserProfile.class);
                        overridePendingTransition(0, 0);
                        break;
                    default:
                        Toast.makeText(getApplicationContext(),R.string.nav_exception,Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });
    }

    private void startNewActivity(Class intent) {
        Intent a = new Intent(this,intent);
        startActivity(a);
    }

    private void setAllTextOnActivity() {
        //setari de limba
        context = getApplicationContext();
        context = MultiLanguageHelper.setLocale(context,(String) Paper.book().read("language"));
        resources = context.getResources();
        hintPass.setHint(resources.getString(R.string.name));
        hintName.setHint(resources.getString(R.string.password));
    }

    public void faCeva(View view){
        VerificaEmpty verificaEmpty = new VerificaEmpty();
        editText = (EditText)findViewById(R.id.editText);
        verificaEmpty.vericaEmplty(getApplicationContext(),editText);
    }
    public void deschideLogIn(View view)
    {
        Intent intent= new Intent(TravelTest.this, LogIn.class);
        startActivity(intent);
    }
    public void deschideEmail(View view){
        Intent intent= new Intent(TravelTest.this, SendEmail.class);
        startActivity(intent);
    }
    public  void deschideFormular(View view)
    {
        Intent intent= new Intent(TravelTest.this, AddStory.class);
        startActivity(intent);
    }
    public void deschideHome(View view){
        Intent intent=new Intent(TravelTest.this, Home.class);
        startActivity(intent);
    }
    public void bazadedate(View view){
        List<Map<String,String>> MyData = null;
        Background mydata =new Background();
        MyData= mydata.doInBackground();
    }

    public void yourPlace(View view){
        locatiiList = db.getUserLocation(1);
        Intent yourPlace = new Intent(this, YourPlace.class);
        startActivity(yourPlace);
    }


}

class TestAsync2 extends AsyncTask<Void, Integer, String>
{
    String TAG = getClass().getSimpleName();

    protected void onPreExecute (){
        super.onPreExecute();
        Log.d(TAG + " PreExceute","On pre Exceute......");
    }

    protected String doInBackground(Void...arg0) {
        Log.d(TAG + " DoINBackGround","On doInBackground...");

        for(int i=0; i<10; i++){
           DatabaseOperation databaseOperation = new DatabaseOperation();
           locatiiList= databaseOperation.getLocation();
        }
        return "You are at PostExecute";
    }

    protected void onProgressUpdate(Integer...a){
        super.onProgressUpdate(a);
        Log.d(TAG + " onProgressUpdate", "You are in progress update ... " + a[0]);
    }

    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        Log.d(TAG + " onPostExecute", "" + result);
    }
}
