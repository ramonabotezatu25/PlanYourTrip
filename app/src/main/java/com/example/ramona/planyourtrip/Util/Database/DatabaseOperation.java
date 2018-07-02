package com.example.ramona.planyourtrip.Util.Database;

import android.content.Context;
import android.content.res.Resources;

import com.example.ramona.planyourtrip.Util.Categorii;
import com.example.ramona.planyourtrip.Util.Email;
import com.example.ramona.planyourtrip.Util.Locatii;
import com.example.ramona.planyourtrip.Util.User;
import com.example.ramona.planyourtrip.Util.UserPreferences;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Ramona on 4/3/2018.
 */

public class DatabaseOperation {

    Connection connect;
    String ConnectionResult = "";
    Boolean isSuccess = false;
    //setari limba
    Context context;
    Resources resources;


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

    public int insertUserPref(UserPreferences userPreferences,Integer idUtilizator) {

        ConnectionHelper conStr = new ConnectionHelper();
        connect = conStr.connectionclasss();        // Connect to database
        if (connect == null)
            ConnectionResult = "Check Your Internet Access!";
        int res = -1;
            if (userPreferences == null) {
                return res;
            }
            try {
                String query2 = "INSERT INTO user_preferences(id_user,status_relatie,copii,calatorii,id_categorie_1,id_categorie_2,buget,id_locatii) VALUES(?,?,?,?,?,?,?,?)";
                PreparedStatement preparedStatement = null;
                preparedStatement = connect.prepareStatement(query2);
                preparedStatement.setInt(1, idUtilizator);
                preparedStatement.setString(2, userPreferences.getStatusRelatie());
                preparedStatement.setString(3, userPreferences.getAreCopii());
                preparedStatement.setString(4, userPreferences.getCatDeDesPleci());
                preparedStatement.setString(5, userPreferences.getCategoria1());
                preparedStatement.setString(6, userPreferences.getCategoria2());
                preparedStatement.setString(7, userPreferences.getBuget());
                preparedStatement.setString(8, userPreferences.getOraseVizitate());
                res = preparedStatement.executeUpdate();
                connect.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }


        return res;
    }
    public int updateUserPref(UserPreferences userPreferences) {

        ConnectionHelper conStr = new ConnectionHelper();
        connect = conStr.connectionclasss();        // Connect to database
        if (connect == null)
            ConnectionResult = "Check Your Internet Access!";
        int res = -1;
        if (userPreferences == null) {
            return res;
        }
        try {
            String query2 = "update user_preferences set status_relatie="+userPreferences.getStatusRelatie()+
                    ", copii ="+userPreferences.getAreCopii()+
                    ",calatorii="+userPreferences.getCatDeDesPleci()+
                    ",id_categorie_1="+userPreferences.getCategoria1()+
                    ",id_categorie_2="+userPreferences.getCategoria2()+
                    ",buget = '"+userPreferences.getBuget()+
                    "',id_locatii='"+userPreferences.getOraseVizitate()+
                    "' where id = "+userPreferences.getId();
            PreparedStatement preparedStatement = null;
            preparedStatement = connect.prepareStatement(query2);
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

    public User logInUtilizator(String email,String parola){
        User utilizator = new User();
        ConnectionHelper conStr = new ConnectionHelper();
        connect = conStr.connectionclasss();        // Connect to database

        if (connect == null) {
            ConnectionResult = "Check Your Internet Access!";
            return utilizator;
        } else {
            // Change below query according to your own database.
            String query = "select count(*) activ,id from user where email = '"+email +"' and parola='"+parola+"' and activ = 1";
            try {
                Statement stmt  = connect.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    utilizator.setActiv(rs.getInt("activ"));
                    utilizator.setId(rs.getInt("id"));
                }
                connect.close();
                return utilizator;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return utilizator;
    }


    //insert messages in database

    public int insertMessages(Email message) {

        ConnectionHelper conStr = new ConnectionHelper();
        connect = conStr.connectionclasss();        // Connect to database
        if (connect == null)
            ConnectionResult = "Check Your Internet Access!";
        int res = -1;
        if (message == null) {
            return res;
        }
        try {
            String query = "INSERT INTO email(nume_utilizator,email,email_to,email_subject,email_body) VALUES(?,?,?,?,?)";
            PreparedStatement preparedStatement = null;
            preparedStatement = connect.prepareStatement(query);
            preparedStatement.setString(1,message.getNume_utilizator());
            preparedStatement.setString(2,message.getEmailFrom());
            preparedStatement.setString(3,message.getEmailTo());
            preparedStatement.setString(4,message.getEmailSubject());
            preparedStatement.setString(5,message.getEmailBody());
            res = preparedStatement.executeUpdate();
            connect.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return res;
    }


    //getAllLocation from DB
    public String getLinkLocatie(String numeLocatie) {
        String data="";
        try {
            ConnectionHelper conStr = new ConnectionHelper();
            connect = conStr.connectionclasss();        // Connect to database
            if (connect == null) {
                ConnectionResult = "Check Your Internet Access!";
            } else {
                // Change below query according to your own database.
                String query = "SELECT link_locatie \n" +
                        "FROM descriere_orase \n" +
                        "WHERE id_locatie=(select id from locatii where upper(nume_oras)='"+numeLocatie.toUpperCase()+"');";
                Statement stmt = connect.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    data=rs.getString(1);
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

    //getALLLocation from db BY ID
    public List<Locatii> getLinksiNumeLocatii() {
        List<Locatii> data=new ArrayList<>();
            try {
            ConnectionHelper conStr = new ConnectionHelper();
            connect = conStr.connectionclasss();        // Connect to database
            if (connect == null) {
                ConnectionResult = "Check Your Internet Access!";
            } else {
                // Change below query according to your own database.
                String query = "select l.nume_oras as nume, d.link_locatie as link, l.lat, l.lon from locatii as l ,descriere_orase as d \n" +
                        "where l.id=d.id_locatie limit 35";
                Statement stmt = connect.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Locatii locatie= new Locatii();
                    locatie.setNume(rs.getString("nume"));
                    locatie.setLink(rs.getString("link"));
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




    //getAllLocation from DB
    public UserPreferences  getUserPref(Integer idUtilizator) {
        UserPreferences data=new UserPreferences();
        try {
            ConnectionHelper conStr = new ConnectionHelper();
            connect = conStr.connectionclasss();        // Connect to database
            if (connect == null) {
                ConnectionResult = "Check Your Internet Access!";
            } else {
                // Change below query according to your own database.
                String query = "select * from user_preferences where id_user = "+idUtilizator;
                Statement stmt = connect.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                     data.setId(rs.getInt("id"));
                     data.setCatDeDesPleci(rs.getString("calatorii"));
                     data.setStatusRelatie(rs.getString("status_relatie"));
                     data.setAreCopii(rs.getString("copii"));
                     data.setCategoria1(rs.getString("id_categorie_1"));
                     data.setCategoria2(rs.getString("id_categorie_2"));
                     data.setBuget(rs.getString("buget"));
                     data.setOraseVizitate(rs.getString("id_locatii"));
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


    public List<Categorii> selectCategorii(){
        List<Categorii> data  = new ArrayList<>();

        ConnectionHelper conStr = new ConnectionHelper();
        connect = conStr.connectionclasss();        // Connect to database

        if (connect == null) {
            ConnectionResult = "Check Your Internet Access!";
            return null;
        } else {
            // Change below query according to your own database.
            String query = "select * from categorii";
            try {
                Statement stmt  = connect.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Categorii categorie = new Categorii();
                    categorie.setId(Integer.parseInt(rs.getString(1)));
                    categorie.setNumeCategorie(rs.getString(2));
                    data.add(categorie);
                }
                connect.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return data;
    }


    //getLocationByCateg from DB
    public List<Locatii>  getLocationByCateg(UserPreferences userPreferences) {
        List<Locatii> data=new ArrayList<>();
        try {
            ConnectionHelper conStr = new ConnectionHelper();
            connect = conStr.connectionclasss();        // Connect to database
            if (connect == null) {
                ConnectionResult = "Check Your Internet Access!";
            } else {
                // Change below query according to your own database.
                String query = "SELECT l.*, d.link_locatie FROM locatii as l, descriere_orase as d " +
                        " WHERE id_categorie_1 = '" +userPreferences.getCategoria1()+
                        "' and l.id=d.id_locatie ORDER BY RAND() " +
                        " LIMIT 2";
                Statement stmt = connect.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    Locatii locatie = new Locatii();
                    locatie.setId(rs.getInt(1));
                    locatie.setNume(rs.getString(2));
                    locatie.setCategorie(rs.getInt(3));
                    locatie.setCategorie2(rs.getInt(4));
                    locatie.setLon(rs.getString(5));
                    locatie.setLon(rs.getString(6));
                    locatie.setLink(rs.getString("link_locatie"));
                     data.add(locatie);
                }


                String query2 = "SELECT l.*, d.link_locatie FROM locatii as l, descriere_orase as d " +
                        " WHERE id_categorie_1 = '" +userPreferences.getCategoria2()+
                        "' and l.id=d.id_locatie ORDER BY RAND() " +
                        " LIMIT 2";
                Statement stmt2 = connect.createStatement();
                ResultSet rs2 = stmt2.executeQuery(query2);
                while (rs2.next()) {
                    Locatii locatie = new Locatii();
                    locatie.setId(rs2.getInt(1));
                    locatie.setNume(rs2.getString(2));
                    locatie.setCategorie(rs2.getInt(3));
                    locatie.setCategorie2(rs2.getInt(4));
                    locatie.setLon(rs2.getString(5));
                    locatie.setLon(rs2.getString(6));
                    locatie.setLink(rs2.getString("link_locatie"));
                    data.add(locatie);
                }

                String query3 = "SELECT l.*, d.link_locatie FROM locatii as l, descriere_orase as d \n" +
                        " WHERE id_categorie_2 in('"+userPreferences.getCategoria1()+"','"+userPreferences.getCategoria2()+"') and\n" +
                        " id_categorie_1 != '"+userPreferences.getCategoria1()+ "' and id_categorie_1 !='"+userPreferences.getCategoria2() +
                        "' and l.id=d.id_locatie ORDER BY RAND() " +
                        " LIMIT 2 ";
                Statement stmt3 = connect.createStatement();
                ResultSet rs3 = stmt3.executeQuery(query3);
                while (rs3.next()) {
                    Locatii locatie = new Locatii();
                    locatie.setId(rs3.getInt(1));
                    locatie.setNume(rs3.getString(2));
                    locatie.setCategorie(rs3.getInt(3));
                    locatie.setCategorie2(rs3.getInt(4));
                    locatie.setLon(rs3.getString(5));
                    locatie.setLon(rs3.getString(6));
                    locatie.setLink(rs3.getString("link_locatie"));
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


    //getAllLocation from DB
    public List<Locatii> getUserLocation(Integer idUer) {
        List<Locatii> locatiiList = new ArrayList<>();
        String locationIds="";
        try {
            ConnectionHelper conStr = new ConnectionHelper();
            connect = conStr.connectionclasss();        // Connect to database
            if (connect == null) {
                ConnectionResult = "Check Your Internet Access!";
            } else {
                // Change below query according to your own database.
                String query = "select id_locatii from user_preferences where id_user ="+idUer;
                Statement stmt = connect.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    locationIds=rs.getString("id_locatii");
                }
                String query2 = "select * from locatii where id in("+locationIds+")";
                Statement stmt2 = connect.createStatement();
                ResultSet rs2 = stmt2.executeQuery(query2);
                while (rs2.next()) {
                    Locatii locatie = new Locatii();
                    locatie.setId(rs2.getInt("id"));
                    locatie.setNume(rs2.getString("nume_oras"));
                    locatie.setCategorie(rs2.getInt("id_categorie_1"));
                    locatie.setCategorie2(rs2.getInt("id_categorie_2"));
                    locatie.setLat(rs2.getString("lat"));
                    locatie.setLon(rs2.getString("lon"));
                    locatiiList.add(locatie);
                }
                ConnectionResult = " successful";
                isSuccess = true;
                connect.close();
            }
        } catch (Exception ex) {
            isSuccess = false;
            ConnectionResult = ex.getMessage();
        }
        return locatiiList;
    }
}
