package com.example.ramona.planyourtrip.NearbyPlaces.MAPS;


import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLEncoder;
import java.util.LinkedList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by Infrasoft Ciprian on 05-Feb-18.
 */

public class BackgroundTask  extends AsyncTask<String, Void, String> {
    String Result;
    String npage;
    LinearLayout spinner=null;
    public BackgroundTask(LinearLayout spinner) {
        this.spinner =spinner;
    }
    StringBuilder sb = new StringBuilder();
    @Override
    protected void onPreExecute()
    {
        super.onPreExecute();
    }
    @Override
    protected void onPostExecute(String result) {
        spinner.setVisibility(View.GONE);
    }
    @Override
    protected String doInBackground(String... arg0)
    {
        String link1 = (String) arg0[0];
        Result = link( link1);
        return Result;
    }
    // This method is used to establish a connection to PHP file.
    // SQL query and link to PHP file is passed as parameter.
    public String link(String link1) {
        try {
            String data = URLEncoder.encode("query", "UTF-8") + "="    + URLEncoder.encode("", "UTF-8");

            URL url = new URL(link1);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            // URLConnection conn = url.openConnection();

            conn.setDoOutput(true);

            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write("");
            wr.flush();
            wr.close();

            String line = null;
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    conn.getInputStream()));
            while ((line = reader.readLine()) != null)
            {
                sb.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
    // get the data in array format for adapter. jsondata is passed with database fields.
    // This method is for offers details adapter and show data in ListView.
    public List<FindPlaces> getplaces(String jsondata, String[] data, String token) {
        List<FindPlaces> allplaces = new LinkedList<FindPlaces>();
        try {
            JSONObject jobject = new JSONObject(jsondata);
            JSONArray jmain = jobject.optJSONArray("results");
            token  = jobject.optString("next_page_token");

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
/*
    public Location getlocation(String jsondata) {
     //   List<Address> allplaces = new LinkedList<Address>();
        Location loc= null;
        try {
            JSONObject jobject = new JSONObject(jsondata);
            JSONArray jmain = jobject.optJSONArray("results");
            Double lng,lat;

            for (int i = 0; i < jmain.length(); i++)
            {
                JSONObject jchild = jmain.getJSONObject(i);

             //   Address address = new Address(Locale.getDefault());

                Log.v("location",jchild.toString());

                lat = jchild.getJSONObject("geometry").getJSONObject("location").getDouble("lat");
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
  */
}
