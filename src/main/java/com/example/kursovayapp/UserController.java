package com.example.kursovayapp;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
        String weight_package = weightPackage.getText();
        String urgency_package = urgencyPackage.getValue();
        Package packag = new Package(weight_package, urgency_package);
        packageNew_id = dbHandler.addPackage(packag); // Возвращает идентификатор новой записи

        // Проверяем, был ли успешно добавлен пакет
        if (packageNew_id > 0) {
            System.out.println("Пакет успешно добавлен. Идентификатор: " + packageNew_id);
        } else {
            System.out.println("Ошибка при добавлении пакета.");
        }
    }

    private void addNewOrder() {
        int counter = 0;
        Random r = new Random();
        DatabaseHandler dbHandler = new DatabaseHandler();
        String select = "SELECT * FROM " + Const.COURIER_TABLE;
        try {
            PreparedStatement prSt = dbHandler.getDbConnection().prepareStatement(select);
            ResultSet restSet = prSt.executeQuery(select);

            while (restSet.next()) {
                counter++;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
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
        System.out.println(String.valueOf((r.nextInt(counter - 1) + 1)));
        String courier_id = String.valueOf((r.nextInt(counter - 1) + 1));
        String center_name = centerName.getValue();
        Order order = new Order(client_id, package_id, courier_id, center_name);
        dbHandler.addOrder(order);
    }

    public void openNewScene(String window) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource(window));
        rootpane.getChildren().setAll(pane);


    }


}
