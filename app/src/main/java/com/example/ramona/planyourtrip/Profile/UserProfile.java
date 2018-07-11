package com.example.ramona.planyourtrip.Profile;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ramona.planyourtrip.AddStory;
import com.example.ramona.planyourtrip.Explore;
import com.example.ramona.planyourtrip.Formular_interese;
import com.example.ramona.planyourtrip.Home;
import com.example.ramona.planyourtrip.LuggageList.Luggage;
import com.example.ramona.planyourtrip.MultiLanguage.MultiLanguageHelper;
import com.example.ramona.planyourtrip.R;
import com.example.ramona.planyourtrip.Util.Database.DatabaseOperation;
import com.example.ramona.planyourtrip.maps.YourPlace;
import com.example.ramona.planyourtrip.voiceControl.VoiceControl;

import io.paperdb.Paper;

import static com.example.ramona.planyourtrip.GmailSender.Constante.idUtilizator;
import static com.example.ramona.planyourtrip.GmailSender.Constante.locatiiList;
import static com.example.ramona.planyourtrip.GmailSender.Constante.luggageList;

public class UserProfile extends AppCompatActivity {
    //Context
    Context context;
    Resources resources;
    //databse
    DatabaseOperation db = new DatabaseOperation();
    //bottom nav
    private TextView mTextMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        //
        new TestAsyncUserProfile().execute();
        //
        setAllTextOnActivity();
        //navigation view
        navView();

    }

    private void setAllTextOnActivity() {
        //setari de limba
        context = getApplicationContext();
        context = MultiLanguageHelper.setLocale(context,(String) Paper.book().read("language"));
        resources = context.getResources();
    }
    public void changeLanguage(View view){
        Intent i = new Intent(this,ChangeLanguage.class);
        startActivity(i);
    }

    public void yourPlace(View view){
        locatiiList = db.getUserLocation(idUtilizator);
        Intent yourPlace = new Intent(this, YourPlace.class);
        startActivity(yourPlace);
    }
    public void settingsPreferences(View view){
        Intent settings = new Intent(this, Formular_interese.class);
        settings.putExtra("operatie","update");
        startActivity(settings);
    }
    public void voiceControl(View view){
        Intent settings = new Intent(this, VoiceControl.class);
        startActivity(settings);
    }

    private void navView(){

        //navigation view
        final BottomNavigationView bottomNavigationView;
        bottomNavigationView = (BottomNavigationView)findViewById(R.id.nav_view_profile);
        Menu menu = bottomNavigationView.getMenu();
        for (int i = 0; i < bottomNavigationView.getMenu().size(); i++) {
            bottomNavigationView.getMenu().getItem(2).setChecked(true);
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
                return false;
            }
        });

    }

    private void startNewActivity(Class intent) {
        Intent a = new Intent(this,intent);
        startActivity(a);
    }


    public void addStory(View view){
        Intent a = new Intent(this, Luggage.class);
        startActivity(a);
    }
}
class TestAsyncUserProfile extends AsyncTask<Void, Integer, String> {
    String TAG = getClass().getSimpleName();

    protected void onPreExecute() {
        super.onPreExecute();
        Log.d(TAG + " PreExceute", "On pre Exceute......");
    }

    protected String doInBackground(Void... arg0) {
        Log.d(TAG + " DoINBackGround", "On doInBackground...");

        String limba =(String) Paper.book().read("language");
        DatabaseOperation databaseOperation = new DatabaseOperation();
        luggageList = databaseOperation.getStandardLuggage(limba);

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
