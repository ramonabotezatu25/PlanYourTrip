package com.example.ramona.planyourtrip.NearbyPlaces;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.ramona.planyourtrip.NearbyPlaces.MAPS.BackgroundTask;
import com.example.ramona.planyourtrip.NearbyPlaces.MAPS.FindPlaces;
import com.example.ramona.planyourtrip.NearbyPlaces.MAPS.LocBackgroundTask;
import com.example.ramona.planyourtrip.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutionException;

import static com.example.ramona.planyourtrip.GmailSender.Constante.currentLocation;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, View.OnClickListener {
    private GoogleMap mMap;
    private EditText placename;
    private CheckBox check1;
    private RadioGroup gradio;
    private Button nextbutton;
    private boolean nextpageflag = false;
    List<Marker> markers = new ArrayList<Marker>();
    private String npage = null;
    Marker marker = null;
    private Button gobutton;
    int pzoom = 13;
    private String ptype = "restaurant";
    String plocation = "28.6451,77.2938";
    Location mlocation = null;
    String pkey = "AIzaSyCG1ucJwPFXTAJahYjpDn3ZotsQdI4eBg0";
    String pname = "";
    int pradius = 1500;
    Button goschool, goatm, gotourist, gofood, gobookstore, godepartmentstore, gotaxistand, goplumber, gorestaurant, gobank, gobakery, gogym, gotemple, goshoppingmall, gopark, gohospital, gocarwash, gocarrepair, gopharmacy;
    LinearLayout spinner;

    //current location

    private static final int REQUEST_LOCATION = 1;
    LocationManager locationManager;
    String lattitude, longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_maps);
        setContentView(R.layout.activity_find_places);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        //context=this;
        goatm = (Button) findViewById(R.id.goatm);
        goschool = (Button) findViewById(R.id.goschool);
        gofood = (Button) findViewById(R.id.gofood);
        gotourist = (Button) findViewById(R.id.gotourist);
        gorestaurant = (Button) findViewById(R.id.gorestaurant);
        gobank = (Button) findViewById(R.id.gobank);
        gopark = (Button) findViewById(R.id.gopark);
        gohospital = (Button) findViewById(R.id.gohospital);
        gopharmacy = (Button) findViewById(R.id.gopharmacy);
        gotemple = (Button) findViewById(R.id.gotemple);
        goshoppingmall = (Button) findViewById(R.id.goshoppingmall);
        gogym = (Button) findViewById(R.id.gogym);
        gobakery = (Button) findViewById(R.id.gobakery);
        gobookstore = (Button) findViewById(R.id.gobookstore);
        gocarrepair = (Button) findViewById(R.id.gocarrepair);
        gocarwash = (Button) findViewById(R.id.gocarwash);
       // godepartmentstore = (Button) findViewById(R.id.godepartmentstore);
        gotaxistand = (Button) findViewById(R.id.gotaxistand);
      //  goplumber = (Button) findViewById(R.id.goplumber);
        gradio = (RadioGroup) findViewById(R.id.gradio);
        spinner = (LinearLayout) findViewById(R.id.LProgress);
        check1 = (CheckBox) findViewById(R.id.check1);
        nextbutton = (Button) findViewById(R.id.nextbutton);
        placename = (EditText) findViewById(R.id.placename);
        gradio.check(R.id.goatm);
        goatm.setOnClickListener(this);
        gofood.setOnClickListener(this);
        goschool.setOnClickListener(this);
        gorestaurant.setOnClickListener(this);
        gobank.setOnClickListener(this);
        gotourist.setOnClickListener(this);
        gopark.setOnClickListener(this);
        gohospital.setOnClickListener(this);
        gopharmacy.setOnClickListener(this);
        gotemple.setOnClickListener(this);
        goshoppingmall.setOnClickListener(this);
        gogym.setOnClickListener(this);
        gobakery.setOnClickListener(this);
        gobookstore.setOnClickListener(this);
        gocarrepair.setOnClickListener(this);
        gocarwash.setOnClickListener(this);
        gotaxistand.setOnClickListener(this);
       // goplumber.setOnClickListener(this);
        check1.setOnClickListener(this);
        nextbutton.setOnClickListener(this);
        // placename.setOnClickListener(this);

        getLocation();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // Add a marker in Sydney and move the camera
        // filldata1(this);
        setupMapUI();
    }

    public void filldata(View view) {
        List<FindPlaces> arrlatlng = getdata();
        if (arrlatlng != null) {
            LatLng pname = null;
            mMap.clear();
            for (int i = 0; i < arrlatlng.size(); i++) {
                pname = new LatLng(arrlatlng.get(i).getLat(), arrlatlng.get(i).getLng());
                mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                marker = mMap.addMarker(new MarkerOptions().position(pname).title(arrlatlng.get(i).getName()).snippet(arrlatlng.get(i).getVicinity()));
                marker.showInfoWindow(); //always show information
                CameraPosition cameraPosition = new CameraPosition.Builder()
                        .target(pname)
                        .zoom(15)
                        .bearing(90)
                        .tilt(60)
                        .build();
                mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            }
            showlocationmarker();
            Toast.makeText(this, "Location Type:" + ptype, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Location Not Found", Toast.LENGTH_LONG).show();
        }
    }

    public void showlocationmarker()

    {
        BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(R.drawable.common_full_open_on_phone);
        LatLng pname = new LatLng( mlocation.getLatitude(),mlocation.getLongitude());
        marker = mMap.addMarker(new MarkerOptions().position(pname).icon(icon).title(placename.getText().toString()));
        marker.showInfoWindow(); //always show information
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(pname)
                .zoom(15)
                .bearing(90)
                .tilt(60)
                .build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }

    private void setupMapUI() {
        // mMap.setMyLocationEnabled(true);
        UiSettings mUiSettings = mMap.getUiSettings();
        mUiSettings.setMyLocationButtonEnabled(true);
        mUiSettings.setCompassEnabled(true);
        mUiSettings.setTiltGesturesEnabled(false);
        mUiSettings.setZoomControlsEnabled(true);
    }



    public List<FindPlaces> getdata() {
        BackgroundTask gp = new BackgroundTask(spinner);
        String str = null;
        String link = null;
        List<FindPlaces> arrstr = null;
        //get location 44.4197521,26.123445
        mlocation =  getlatlngnew(placename.getText().toString());
        plocation = String.valueOf(mlocation.getLatitude())  +","+String.valueOf(mlocation.getLongitude());
        Toast.makeText(this, "Location"+plocation, Toast.LENGTH_SHORT).show();
        try {
            link = "https://maps.googleapis.com//maps/api/place/nearbysearch/json?location=" + plocation + "&radius=" + pradius + "&type=" + ptype + "&name=" + pname + "&key=" + pkey;
            str = gp.execute(link).get();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        String[] fields = {"id", "name", "vicinity", "lat", "lng", "types"};
        arrstr = gp.getplaces(str, fields,npage);
        return arrstr;
    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.check1) {
            if (check1.isChecked()) {
                gradio.setVisibility(view.VISIBLE);
            } else {
                gradio.setVisibility(view.GONE);
            }
            return;
        }
        if (view.getId() == R.id.goatm)  ptype = "atm";
        if (view.getId() == R.id.goatm)  ptype = "Temple";
        if (view.getId() == R.id.gobank) ptype = "bank";
        if (view.getId() == R.id.gofood) ptype = "food";
        if (view.getId() == R.id.goschool) ptype = "school";
        if (view.getId() == R.id.gotourist) ptype = "tourist";
        if (view.getId() == R.id.gopark) ptype = "park";
        if (view.getId() == R.id.gohospital) ptype = "hospital";
        if (view.getId() == R.id.gopharmacy) ptype = "pharmacy";
        if (view.getId() == R.id.gotemple) ptype = "temple";
        if (view.getId() == R.id.goshoppingmall) ptype = "shopping_mall";
        if (view.getId() == R.id.gogym) ptype = "gym";
        if (view.getId() == R.id.gobakery) ptype = "bakery";
        if (view.getId() == R.id.gobookstore) ptype = "book_store";
        if (view.getId() == R.id.gocarrepair) ptype = "car_repair";
        if (view.getId() == R.id.gocarwash) ptype = "car_wash";
        if (view.getId() == R.id.godepartmentstore) ptype = "department_store";
        if (view.getId() == R.id.gotaxistand) ptype = "taxi_stand";
        if (view.getId() == R.id.goplumber) ptype = "plumber";
        gradio.setVisibility(view.GONE);
        filldata(view);
    }

    public Location getlatlngnew(String p_placename) {
        LocBackgroundTask gp = new LocBackgroundTask(spinner);
        String link = null;
        String str=null;
        Location loc= null;
        String psearchlocation=placename.getText().toString();
        try {
            //   link = "https://maps.googleapis.com//maps/api/place/nearbysearch/json?location=" + plocation + "&radius=" + pradius + "&type=" + ptype + "&name=" + pname + "&key=" + pkey;
            link = "https://maps.googleapis.com/maps/api/geocode/json?address="+psearchlocation+"&key=" + pkey;
            str = gp.execute(link).get();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        loc = gp.getlocation(str);
        return loc;
    }

    public void getLocation() {
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
         double latti = 40.73;
         double longi = -73.99;
        if (ActivityCompat.checkSelfPermission(MapsActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission
                (MapsActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(MapsActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);

        } else {
            Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

            Location location1 = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

            Location location2 = locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);


            if (location != null) {
                 latti = location.getLatitude();
                 longi = location.getLongitude();
                lattitude = String.valueOf(latti);
                longitude = String.valueOf(longi);


            } else if (location1 != null) {
                 latti = location1.getLatitude();
                 longi = location1.getLongitude();
                lattitude = String.valueOf(latti);
                longitude = String.valueOf(longi);

            } else if (location2 != null) {
                 latti = location2.getLatitude();
                 longi = location2.getLongitude();
                lattitude = String.valueOf(latti);
                longitude = String.valueOf(longi);

            } else if(currentLocation.length >0){
                latti = currentLocation[0];
                longi = currentLocation[1];
                lattitude = String.valueOf(latti);
                longitude = String.valueOf(longi);
            }else{
                Toast.makeText(this,"GPS NOT WORK",Toast.LENGTH_SHORT).show();
            }
            Geocoder geoCoder = new Geocoder(getApplicationContext(), Locale.getDefault()); //it is Geocoder
            StringBuilder builder = new StringBuilder();
            try {
                List<Address> address = geoCoder.getFromLocation(latti, longi, 1);
                String addressStr = address.get(0).getAddressLine(0);
                builder.append(addressStr);
                builder.append(" ");

                String fnialAddress = builder.toString();//This is the complete address.

                placename.setText(fnialAddress);
            } catch (IOException e) {

            }
            catch (NullPointerException e) {}
        }

    }


    public void backButton(View view){
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }
}