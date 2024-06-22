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
        authSiginButton.setOnAction(event -> {

            singUpNewClient();

        });

    }

    private void singUpNewClient() {
        DatabaseHandler dbHandler = new DatabaseHandler();
        String client_name = singUpName.getText();
        String client_phone = singUpPhoneNumber.getText();
        String client_address = singUpAddress.getText();
        String password = singUpPassword.getText();

        Client client = new Client(client_name, client_phone, client_address, password);

        dbHandler.signUpUser(client);
    }

}

