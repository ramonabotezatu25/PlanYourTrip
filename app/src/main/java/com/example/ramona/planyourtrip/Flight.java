package com.example.ramona.planyourtrip;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.ramona.planyourtrip.MultiLanguage.MultiLanguageHelper;
import com.example.ramona.planyourtrip.Util.Database.DatabaseOperation;
import com.example.ramona.planyourtrip.Util.Locatii;
import static com.example.ramona.planyourtrip.GmailSender.CodUnicIdentificare.orasDestinatieFlight;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import io.paperdb.Paper;

public class Flight extends AppCompatActivity {

    //setari de limba
    Context context;
    Resources resources;
    ///
    Button startTime,endTime;
    TextView tvStartTime,tvEndTime;
    Spinner dropdown,dropdown2;
    int year,mounth,day;
    int DIALOG_ID=0;
    Calendar calendar;
    DatePickerDialog dpd;
    //variables
    String getTime="";
    //
    String linkFlightDepCity = "BUH-";
    String linkFlightArrCity = "CDG/";
    String linkFlightStartTime= "";
    String linkFlightendTime= "";
    String linkFlight = "https://www.momondo.com/flight-search/";
    //hotel
    String linkHotel = "https://www.momondo.com/hotels/search/";
    String linkHotelArrCity = "paris";

    //database
    DatabaseOperation db = new DatabaseOperation();
    List<Locatii> listaocatii = new ArrayList<>();
    String[] items;
    Integer indexOrasDestinatie=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight);


         dropdown = findViewById(R.id.spinner1);
         dropdown2 = findViewById(R.id.spinner2);

        listaocatii = db.getLocation();
        items = new String[listaocatii.size()];

        allYouNeed();



        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);

        dropdown.setAdapter(adapter);
        dropdown2.setAdapter(adapter);
        dropdown2.setSelection(indexOrasDestinatie);

        ////
        startTime = (Button)findViewById(R.id.startTime);
        endTime = (Button)findViewById(R.id.endTime);
        tvStartTime = (TextView)findViewById(R.id.startTimeTV);
        tvEndTime = (TextView)findViewById(R.id.endTimeTv);
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        mounth = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        showCalendar();

    }

    private void allYouNeed() {

        //preiau toate view-urile care trebuie traduce din clasa
        setAllTextOnActivity();

    }
    private void setAllTextOnActivity() {
        //setari de limba
        context = getApplicationContext();
        context = MultiLanguageHelper.setLocale(context,(String) Paper.book().read("language"));
        resources = context.getResources();

        for(int i =0;i<listaocatii.size();i++){
            int oras = resources.getIdentifier(listaocatii.get(i).getNume()+"AER", "string", context.getPackageName());
            items[i] = resources.getText(oras).toString();
            if(items[i].contains(orasDestinatieFlight))
                indexOrasDestinatie=i;
        }

    }

    private void showCalendar(){
        startTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getTime = "startTime";
                showDialog(DIALOG_ID);
            }
        });

        endTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getTime="endTime";
                showDialog(DIALOG_ID);
            }
        });
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        if(id == DIALOG_ID){
            dpd = new DatePickerDialog(this,dpickerListener,year,mounth,day);
            calendar.set(Calendar.YEAR,year);
            calendar.set(Calendar.MONTH,mounth);
            calendar.set(Calendar.DAY_OF_MONTH,day);
            dpd.getDatePicker().setMinDate(calendar.getTimeInMillis());
            return dpd;
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener dpickerListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int yearX, int mounthX, int dayX) {
           String monthS = "";
           String dayS = "";
            year = yearX;
            mounth = mounthX+1;
            day = dayX;


            if(mounthX < 10)
                monthS = "0"+String.valueOf(mounth);
            else
                monthS = String.valueOf(mounth);

            if(dayX < 10)
                dayS ="0"+String.valueOf(dayX);
            else
                dayS=String.valueOf(dayX);

            if(getTime.equals("startTime")){
                linkFlightStartTime =String.valueOf(year)+ "-" + monthS + "-"+dayS+"/";
                tvStartTime.setText(String.valueOf(year)+ "-" + monthS + "-"+dayS);
            }
            else {
                linkFlightendTime = String.valueOf(year) + "-" + monthS + "-" + dayS;
                tvEndTime.setText(String.valueOf(year)+ "-" + monthS + "-"+dayS);
            }
            getTime="";
        }
    };

    public void searchFlight(View view){
        String orasPlecare = dropdown.getSelectedItem().toString();
        String orasDestinatie = dropdown2.getSelectedItem().toString();

        linkFlightDepCity = orasPlecare.substring(orasPlecare.indexOf("(")+1,orasPlecare.indexOf(")"))+"-";
        linkFlightArrCity = orasDestinatie.substring(orasDestinatie.indexOf("(")+1,orasDestinatie.indexOf(")"))+"/";

        linkFlight = linkFlight + linkFlightDepCity+linkFlightArrCity+linkFlightStartTime+linkFlightendTime;
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(linkFlight));
        startActivity(browserIntent);
    }

    public void searchHotel(View view){
        linkHotelArrCity = dropdown2.getSelectedItem().toString();
        linkHotel = linkHotel+linkHotelArrCity;
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(linkHotel));
        startActivity(browserIntent);
    }

}
