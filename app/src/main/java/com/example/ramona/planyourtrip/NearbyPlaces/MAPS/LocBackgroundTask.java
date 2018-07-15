package com.example.ramona.planyourtrip.NearbyPlaces.MAPS;

import android.app.ProgressDialog;
import android.content.Context;
import android.location.Location;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Infrasoft Ciprian on 05-Feb-18.
 */

public class LocBackgroundTask extends AsyncTask<String, Void, String> {
    String Result;
    LinearLayout spinner=null;
    Context context;
    private ProgressDialog pdia;
    StringBuilder sb = new StringBuilder();
    public LocBackgroundTask(LinearLayout spinner) {
        this.spinner =spinner;this.context=context;
    }
    @Override
    protected void onPreExecute()
    {
        this.spinner.setVisibility(View.VISIBLE);
    }
    @Override
    protected String doInBackground(String... arg0)
    {
        String link1 = (String) arg0[0];
        Result = link( link1);
        return Result;
    }

    @Override
    protected void onPostExecute(String result) {
    }
    // This method is used to establish a connection to PHP file.
    // SQL query and link to PHP file is passed as parameter.

    public String link(String url) {
        String result = "";
        try {
            URL urls = new URL(url.replace(" ","%20"));
            HttpURLConnection conn = (HttpURLConnection) urls.openConnection();
            conn.setReadTimeout(150000)   ; //milliseconds
            conn.setConnectTimeout(15000) ; // milliseconds
            conn.setRequestMethod("POST") ;
            conn.connect();
            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(
                        conn.getInputStream(), "iso-8859-1"), 8);
                StringBuilder sb = new StringBuilder();
                String line = null;
                while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");
                }
                result = sb.toString();
            } else {
                return "error";
            }
        } catch (Exception e) {
            // System.out.println("exception in jsonparser class ........");
            e.printStackTrace();
            return "error";
        }
        return result;
    } // method ends

    /*  // get the data in array format for adapter. jsondata is passed with database fields.
      // This method is for offers details adapter and show data in ListView.
      public List<FindPlaces> getplaces(String jsondata, String[] data) {
          List<FindPlaces> allplaces = new LinkedList<FindPlaces>();

          try {
              JSONObject jobject = new JSONObject(jsondata);
              JSONArray jmain = jobject.optJSONArray("results");
              Double lng,lat;
              for (int i = 0; i < jmain.length(); i++)
              {
                  JSONObject jchild = jmain.getJSONObject(i);
                  FindPlaces findPlace = new FindPlaces();
                  Log.v("location",jchild.toString());
                  findPlace.setId(i);
                  findPlace.setName(jchild.optString(data[1]));
                  findPlace.setVicinity(jchild.optString(data[2]));
                  lat = jchild.getJSONObject("geometry").getJSONObject("location").getDouble("lat");
                  lng = jchild .getJSONObject("geometry").getJSONObject("location").getDouble("lng");
                  findPlace.setLat(lat);
                  findPlace.setLng(lng);
                  findPlace.setTypes(jchild.optJSONObject(data[5]));
                  allplaces.add(findPlace);
              }
          } catch (JSONException e) {
              // TODO Auto-generated catch block
              e.printStackTrace();
          }
          return allplaces;
      }
*/
    public Location getlocation(String jsondata) {
        //   List<Address> allplaces = new LinkedList<Address>();
        Location loc= new Location("network");
        try {
            JSONObject jobject = new JSONObject(jsondata);
            JSONArray jmain = jobject.optJSONArray("results");
            Double lng,lat = null;
            for (int i = 0; i < jmain.length(); i++)
            {
                JSONObject jchild = jmain.getJSONObject(i);
                //   Address address = new Address(Locale.getDefault());
                Log.v("location",jchild.toString());
                try {
                    lat = jchild.getJSONObject("geometry").getJSONObject("location").getDouble("lat");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                lng = jchild.getJSONObject("geometry").getJSONObject("location").getDouble("lng");
                loc.setLongitude(lng);
                loc.setLatitude(lat);
            }

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return loc;
    }
}