package com.example.kursovayapp;

import javafx.scene.control.Alert;

import java.sql.*;

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
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Регистрация");

            alert.setHeaderText("Регистрация");
            alert.setContentText("Успешно!");
            alert.showAndWait();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void addOrder(String client_id, String package_id){
        String insert = "INSERT INTO " + Const.ORDER_TABLE + "(" + Const.CLIENT_ID + "," +
                Const.PACKAGE_ID + ")" +
                "VALUES(?,?)";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setString(1, client_id);
            prSt.setString(2, package_id);
            prSt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public long addPackage(Package packag){
        String insert = "INSERT INTO " + Const.PACKAGE_TABLE + "(" + Const.WEIGHT_PACKAGE + "," +
                Const.URGENCY_PACKAGE + "," + Const.SEND_CENTER_NAME + "," + Const.COURIER_ID + ")" + " VALUES(?,?,?,?)";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            prSt.setString(1, packag.getWeight_package());
            prSt.setString(2, packag.getUrgency_package());
            prSt.setString(3, packag.getSend_center_name());
            prSt.setString(4, packag.getCourier_id());
            prSt.executeUpdate();
            ResultSet generatedKeys = prSt.getGeneratedKeys();
            if (generatedKeys.next()) {
                // Возвращаем идентификатор новой записи
                return generatedKeys.getLong(1);
            } else {
                // Если генерация ключа не удалась, возвращаем -1 или бросаем исключение
                throw new SQLException("Не удалось получить идентификатор новой записи.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public ResultSet getClient(Client client){
        ResultSet restSet = null;

        String select = "SELECT * FROM " + Const.CLIENT_TABLE + " WHERE " +
                Const.CLIENT_PHONE + "=? AND " + Const.CLIENT_PASSWORD + "=?";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, client.getClient_phone());
            prSt.setString(2, client.getPassword());
            restSet = prSt.executeQuery();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return restSet;
    }


}
