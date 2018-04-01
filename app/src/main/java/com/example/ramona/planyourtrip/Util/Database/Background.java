package com.example.ramona.planyourtrip.Util.Database;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Ramona on 4/1/2018.
 */

public class Background {

        Connection connect;
        String ConnectionResult = "";
        Boolean isSuccess = false;

        public List<Map<String, String>> doInBackground() {

            List<Map<String, String>> data = null;
            data = new ArrayList<Map<String, String>>();
            try {
                ConnectionHelper conStr = new ConnectionHelper();
                connect = conStr.connectionclasss();        // Connect to database
                if (connect == null) {
                    ConnectionResult = "Check Your Internet Access!";
                } else {
                    // Change below query according to your own database.
                    String query = "select * from categorii";
                    Statement stmt = connect.createStatement();
                    ResultSet rs = stmt.executeQuery(query);
                    while (rs.next()) {
                        Map<String, String> datanum = new HashMap<String, String>();
                        datanum.put("ID", rs.getString("ID"));
                        datanum.put("nume_categorie", rs.getString("nume_categorie"));
                        data.add(datanum);
                    }


                    ConnectionResult = " successful";
                    isSuccess = true;
                    connect.close();
                }
            } catch (Exception ex) {
                isSuccess = false;
                ConnectionResult = ex.getMessage();
            }

            return data;
        }


}