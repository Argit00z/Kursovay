package com.example.kursovayapp;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class UserController {

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
    void initialize() {
        
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


    }





    public void openNewScene(String window) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource(window));
        rootpane.getChildren().setAll(pane);
    }


}
