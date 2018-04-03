package com.example.ramona.planyourtrip.Util.Database;

import android.content.ContentValues;

import com.example.ramona.planyourtrip.Util.Locatii;
import com.example.ramona.planyourtrip.Util.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.ramona.planyourtrip.Util.Database.DataBaseContants.TABLE_NAME_UTILIZATORI;
import static com.example.ramona.planyourtrip.Util.Database.DataBaseContants.USER_COD_CONFIRMARE;
import static com.example.ramona.planyourtrip.Util.Database.DataBaseContants.USER_EMAIL;
import static com.example.ramona.planyourtrip.Util.Database.DataBaseContants.USER_ID;
import static com.example.ramona.planyourtrip.Util.Database.DataBaseContants.USER_NAME;
import static com.example.ramona.planyourtrip.Util.Database.DataBaseContants.USER_PASS;

/**
 * Created by Ramona on 4/3/2018.
 */

public class DatabaseOperation {

    Connection connect;
    String ConnectionResult = "";
    Boolean isSuccess = false;



    //getAllLocation from DB
    public List<Locatii> getLocation() {

        List<Locatii> data = null;
        data = new ArrayList<Locatii>();
        try {
            ConnectionHelper conStr = new ConnectionHelper();
            connect = conStr.connectionclasss();        // Connect to database
            if (connect == null) {
                ConnectionResult = "Check Your Internet Access!";
            } else {
                // Change below query according to your own database.
                String query = "select * from locatii order by nume_oras";
                Statement stmt = connect.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Locatii locatie = new Locatii();
                    locatie.setId(rs.getInt("ID"));
                    locatie.setNume(rs.getString("nume_oras"));
                    locatie.setCategorie(rs.getInt("id_categorie_1"));
                    locatie.setCategorie2(rs.getInt("id_categorie_2"));
                    locatie.setLat(rs.getString("lat"));
                    locatie.setLon(rs.getString("lon"));
                    data.add(locatie);
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


    //insert USER in database

    public int insertUtilizator(User user) {

        ConnectionHelper conStr = new ConnectionHelper();
        connect = conStr.connectionclasss();        // Connect to database
        if (connect == null)
            ConnectionResult = "Check Your Internet Access!";
        int res = -1;
        if (user == null) {
            return res;
        }
        try {
            String query = "INSERT INTO user(nume,email,parola,cod_confirmare) VALUES(?,?,?,?)";
            PreparedStatement preparedStatement = null;
            preparedStatement = connect.prepareStatement(query);
            preparedStatement.setString(1, user.getNume());
            preparedStatement.setString(2,user.getEmail());
            preparedStatement.setString(3,user.getParola());
            preparedStatement.setString(4,user.getCodConfirmare());
            res = preparedStatement.executeUpdate();
            connect.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return res;
    }

    public String checkConfirmationCode(String email){
        String codConfirmare="";

        ConnectionHelper conStr = new ConnectionHelper();
        connect = conStr.connectionclasss();        // Connect to database

        if (connect == null) {
            ConnectionResult = "Check Your Internet Access!";
            return codConfirmare;
        } else {
            // Change below query according to your own database.
            String query = "select cod_confirmare from user where email = '"+email +"'";
            try {
                Statement stmt  = connect.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    codConfirmare = rs.getString(1);
                }
                connect.close();
                return codConfirmare;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return codConfirmare;
    }


    public void activateUser (String email) {

        ConnectionHelper conStr = new ConnectionHelper();
        connect = conStr.connectionclasss();        // Connect to database
        if (connect == null)
            ConnectionResult = "Check Your Internet Access!";
        if (!email.equals("")) {
            try {
                String query = "UPDATE user SET activ = 1 where email = '" + email + "'";
                PreparedStatement preparedStatement = null;
                preparedStatement = connect.prepareStatement(query);
                preparedStatement.executeUpdate();
                connect.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void updateConfirmationCode (String email,String codConfirmare) {

        ConnectionHelper conStr = new ConnectionHelper();
        connect = conStr.connectionclasss();        // Connect to database
        if (connect == null)
            ConnectionResult = "Check Your Internet Access!";
        if (!email.equals("")) {
            try {
                String query = "UPDATE user SET cod_confirmare ='"+codConfirmare+"' where email = '" + email + "'";
                PreparedStatement preparedStatement = null;
                preparedStatement = connect.prepareStatement(query);
                preparedStatement.executeUpdate();
                connect.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Integer checkUniqueEmail (String email){
        Integer uniqueEmail=1;

        ConnectionHelper conStr = new ConnectionHelper();
        connect = conStr.connectionclasss();        // Connect to database

        if (connect == null) {
            ConnectionResult = "Check Your Internet Access!";
            return uniqueEmail;
        } else {
            // Change below query according to your own database.
            String query = "select count(*) from user where email = '"+email +"'";
            try {
                Statement stmt  = connect.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    uniqueEmail = rs.getInt(1);
                }
                connect.close();
                return uniqueEmail;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return uniqueEmail;
    }

    public Integer logInUtilizator(String email,String parola){
        Integer utilizatorActiv=0;

        ConnectionHelper conStr = new ConnectionHelper();
        connect = conStr.connectionclasss();        // Connect to database

        if (connect == null) {
            ConnectionResult = "Check Your Internet Access!";
            return utilizatorActiv;
        } else {
            // Change below query according to your own database.
            String query = "select count(*) from user where email = '"+email +"' and parola='"+parola+"'";
            try {
                Statement stmt  = connect.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    utilizatorActiv = rs.getInt(1);
                }
                connect.close();
                return utilizatorActiv;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return utilizatorActiv;
    }
}
