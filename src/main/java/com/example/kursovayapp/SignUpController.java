package com.example.kursovayapp;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class SignUpController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button authSiginButton;

    @FXML
    private TextField singUpAddress;

    @FXML
    private TextField singUpName;

    @FXML
    private PasswordField singUpPassword;

    @FXML
    private TextField singUpPhoneNumber;

    @FXML
    void initialize() {
        DatabaseHandler dbHandler = new DatabaseHandler();
        authSiginButton.setOnAction(event -> {
            dbHandler.signUpUser(singUpName.getText(),  singUpAddress.getText(),
                    singUpPassword.getText(), singUpPhoneNumber.getText());
        });

    }

}

