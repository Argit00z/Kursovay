package com.example.kursovayapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class DatabaseHandler extends Configs {
    Connection dbConnection;

    public Connection getDbConnection()
            throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;

        Class.forName("com.mysql.cj.jdbc.Driver");

        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);

        return dbConnection;
    }
    public  void signUpUser(Client client){
        String insert = "INSERT INTO " + Const.CLIENT_TABLE + "(" + Const.CLIENT_ADDRESS + "," +
                Const.CLIENT_NAME + "," + Const.CLIENT_PASSWORD + "," + Const.CLIENT_PHONE + ")" +
                "VALUES(?,?,?,?)";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setString(1, client.getClient_address());
            prSt.setString(2, client.getClient_name());
            prSt.setString(3, client.getPassword());
            prSt.setString(4, client.getClient_phone());
            prSt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
