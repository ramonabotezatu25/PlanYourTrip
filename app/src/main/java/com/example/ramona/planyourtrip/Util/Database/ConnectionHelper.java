package com.example.ramona.planyourtrip.Util.Database;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Ramona on 4/1/2018.
 */

public class ConnectionHelper {

    String ip,db,DBUserNameStr,DBPasswordStr;
    static Connection con;

    @SuppressLint("NewApi")
    public Connection connectionclasss()
    {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://server.yourz.ro:3306/worldtips","worldtips","1xw50MhJLGDi47L4");


        }
        catch (SQLException se)
        {
            Log.e("error here 1 : ", se.getMessage());
        }
        catch (ClassNotFoundException e)
        {
            Log.e("error here 2 : ", e.getMessage());
        }
        catch (Exception e)
        {
            Log.e("error here 3 : ", e.getMessage());
        }
        return con;
    }
}