package com.example.kursovayapp;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Objects;
import java.util.Random;
import java.util.ResourceBundle;

import animations.Shake;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;


public class UserController {

    private long packageNew_id;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField recipientPhoneNumber;

    @FXML
    private AnchorPane rootpane;

    @FXML
    private Button settingsButton;

    @FXML
    private ChoiceBox<String> urgencyPackage;

    @FXML
    private TextField weightPackage;

    @FXML
    private ChoiceBox<String> centerName;

    @FXML
    private Button sendPackageButton;

    @FXML
    void initialize() throws IOException {
        
        DatabaseHandler db = new DatabaseHandler();
        String select = "SELECT * FROM " + Const.CENTER_TABLE;

        try {
            PreparedStatement prSt = db.getDbConnection().prepareStatement(select);
            ResultSet restSet = prSt.executeQuery(select);

            while (restSet.next()){

                centerName.getItems().add(restSet.getString(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        urgencyPackage.getItems().addAll("Обычная", "Срочная");

        settingsButton.setOnAction(event -> {
            try {
                openNewScene("Settings-view.fxml");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        sendPackageButton.setOnAction(event -> {
            String recipientPhoneNumberText = recipientPhoneNumber.getText().trim();
            DatabaseHandler dbHandler = new DatabaseHandler();
            Client client = new Client();
            String query = "SELECT * FROM clients";
            client.setClient_phone(recipientPhoneNumberText);
            Text textTrue = new Text(195, 150, "Заявка успешно отправлена!");
            Text textFalse = new Text(195, 150, "Пользователь не найден");
            boolean flag = false;
            try {
                PreparedStatement prSt = db.getDbConnection().prepareStatement(query);
                ResultSet restSet = prSt.executeQuery(query);
                while (restSet.next()){
                    if(recipientPhoneNumberText.equals(restSet.getString(3))){
                        flag = true;
                        break;
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            if (flag){
                addNewPackage();
                addNewOrder();
                rootpane.getChildren().remove(textTrue);

                textTrue.setFill(Color.GREEN); // Цвет текста
                textTrue.setFont(Font.font("Arial", FontWeight.BOLD, 24));
                rootpane.getChildren().add(textTrue);


            }else {
                Shake phoneAnim = new Shake(recipientPhoneNumber);

                phoneAnim.playAnim();
                rootpane.getChildren().remove(textTrue);

            }
        });


    }


    private void addNewPackage() {
        DatabaseHandler dbHandler = new DatabaseHandler();
        String phone = recipientPhoneNumber.getText();
        String weight_package = weightPackage.getText();
        String urgency_package = urgencyPackage.getValue();
        String center_name = centerName.getValue();
        String address_client = null;
        String courier_id = null;
        String name_center = null;
        String query1 = "SELECT * FROM " + Const.CLIENT_TABLE + " WHERE " + Const.CLIENT_PHONE + " = " + "'" + phone + "'";

        try {
            PreparedStatement prSt = dbHandler.getDbConnection().prepareStatement(query1);
            ResultSet restSet = prSt.executeQuery(query1);
            while (restSet.next()){
                address_client = restSet.getString(4);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        String query2 = "SELECT * FROM " + Const.CENTER_TABLE + " WHERE " + Const.CENTER_ADDRESS + " = " + "'" + address_client + "'";
        try {
            PreparedStatement prSt = dbHandler.getDbConnection().prepareStatement(query2);
            ResultSet restSet = prSt.executeQuery(query2);
            while (restSet.next()){
                name_center = restSet.getString(1);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        int counter = 0;
        Random r = new Random();
        String query3 = "SELECT * FROM " + Const.COURIER_TABLE;
        String query4 = "SELECT * FROM " + Const.COURIER_TABLE + " WHERE " + Const.GET_CENTER_NAME + " = " + "'" + name_center + "'";
        try {
            PreparedStatement prSt = dbHandler.getDbConnection().prepareStatement(query3);
            ResultSet restSet = prSt.executeQuery(query3);
            while (restSet.next()){
                if(name_center.equals(restSet.getString(4))){
                    counter++;
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            PreparedStatement prSt = dbHandler.getDbConnection().prepareStatement(query4);
            ResultSet restSet = prSt.executeQuery(query4);
            int rand = r.nextInt(counter - 1) + 1;
            System.out.println(rand);
            while (rand > 0){
                restSet.next();
                rand--;
                courier_id = restSet.getString(1);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


        Package packag = new Package(weight_package, urgency_package, center_name, courier_id);
        packageNew_id = dbHandler.addPackage(packag); // Возвращает идентификатор новой записи

        // Проверяем, был ли успешно добавлен пакет
        if (packageNew_id > 0) {
            System.out.println("Пакет успешно добавлен. Идентификатор: " + packageNew_id);
        } else {
            System.out.println("Ошибка при добавлении пакета.");
        }
    }

    private void addNewOrder() {
        Random r = new Random();
        DatabaseHandler dbHandler = new DatabaseHandler();
        String recipientPhoneNumberText = recipientPhoneNumber.getText().trim();
        Client client = new Client();
        String query = "SELECT * FROM clients";
        String id_cl = null;
        client.setClient_phone(recipientPhoneNumberText);
        boolean flag = false;
        try {
            PreparedStatement prSt = dbHandler.getDbConnection().prepareStatement(query);
            ResultSet restSet = prSt.executeQuery(query);
            while (restSet.next()){
                if(recipientPhoneNumberText.equals(restSet.getString(3))){
                    id_cl = restSet.getString(1);
                    break;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        String client_id = id_cl;
        String package_id = String.valueOf(packageNew_id);


        dbHandler.addOrder(client_id, package_id);
    }

    public void openNewScene(String window) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource(window));
        rootpane.getChildren().setAll(pane);


    }


}
