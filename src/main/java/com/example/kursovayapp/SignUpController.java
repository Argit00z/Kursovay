package com.example.kursovayapp;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SignUpController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button authSiginButton;

    @FXML
    private Button backButton;

    @FXML
    private TextField singUpAddress;

    @FXML
    private TextField singUpName;

    @FXML
    private PasswordField singUpPassword;

    @FXML
    private TextField singUpPhoneNumber;

    @FXML
    private AnchorPane rootpane;

    @FXML
    void initialize() {
        authSiginButton.setOnAction(event -> {

            singUpNewClient();

        });
        backButton.setOnAction(event -> {
            try {
                openNewScene("Authorization-view.fxml");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }


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
    public void openNewScene(String window) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource(window));
        rootpane.getChildren().setAll(pane);
    }

}

