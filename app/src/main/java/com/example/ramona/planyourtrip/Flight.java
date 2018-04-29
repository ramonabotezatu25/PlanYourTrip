package com.example.ramona.planyourtrip;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ramona.planyourtrip.Util.Database.DatabaseOperation;
import com.example.ramona.planyourtrip.Util.Locatii;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Flight extends AppCompatActivity {

    Button startTime;
    Button endTime;
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
    //
    String linkCars = "https://www.momondo.com/cars/";
    String linkCarsArrCity = "los-angeles-c16078/";
    String linkCarsStartTime= "";

    //database
    DatabaseOperation db = new DatabaseOperation();
    List<Locatii> listaocatii = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight);


        Spinner dropdown = findViewById(R.id.spinner1);
        Spinner dropdown2 = findViewById(R.id.spinner2);

        listaocatii = db.getLocation();
        String[] items = new String[listaocatii.size()];
        for(int i =0;i<listaocatii.size();i++){
            items[i] = listaocatii.get(i).getNume();
        }


        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);

        dropdown.setAdapter(adapter);
        dropdown2.setAdapter(adapter);

        ////
        startTime = (Button)findViewById(R.id.startTime);
        endTime = (Button)findViewById(R.id.endTime);
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        mounth = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        showCalendar();

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
            mounth = mounthX;
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
                linkCarsStartTime =String.valueOf(year)+ "-" + monthS + "-"+dayS+"-1h/";

            }
            else
                linkFlightendTime=String.valueOf(year)+ "-" + monthS + "-"+dayS;

            getTime="";
        }
    };

    public void searchFlight(View view){
        linkFlight = linkFlight + linkFlightDepCity+linkFlightArrCity+linkFlightStartTime+linkFlightendTime;
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(linkFlight));
        startActivity(browserIntent);
    }

    public void searchHotel(View view){
        linkHotel = linkHotel+linkHotelArrCity;
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(linkHotel));
        startActivity(browserIntent);
    }

    public void searchCars(View view){
        linkCars = linkCars + linkCarsArrCity +linkCarsStartTime+linkFlightendTime;
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(linkCars));
        startActivity(browserIntent);
    }

}
