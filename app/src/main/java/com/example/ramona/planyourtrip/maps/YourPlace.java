package com.example.ramona.planyourtrip.maps;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.example.ramona.planyourtrip.R;
import com.example.ramona.planyourtrip.Util.Locatii;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import static com.example.ramona.planyourtrip.GmailSender.CodUnicIdentificare.locatiiList;

public class YourPlace extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_place);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng locatie = null;
        if(locatiiList.size()>0){
            for(Locatii l:locatiiList){
                locatie = new LatLng(Double.valueOf(l.getLat()), Double.valueOf(l.getLon()));
                mMap.addMarker(new MarkerOptions().position(locatie).title("You were in "+l.getNume()));
            }
            mMap.moveCamera(CameraUpdateFactory.newLatLng(locatie));

        }
        // Add a marker in Sydney and move the camera
       /* LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));*/
    }
}
