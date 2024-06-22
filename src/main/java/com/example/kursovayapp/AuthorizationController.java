package com.example.kursovayapp;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AuthorizationController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button authSiginButton;

    @FXML
    private Button loginSiginUpButton;

    @FXML
    private TextField login_field;

    @FXML
    private PasswordField password_field;

    @FXML
    void initialize() {

        authSiginButton.setOnAction(event -> {
            String loginText = login_field.getText().trim();
            String loginPassword = password_field.getText().trim();

            if (!loginText.equals("") && !loginPassword.equals("")){
                loginUser(loginText, loginPassword);
            }else {
                System.out.println("Login and password is empty");
            }
        });

        loginSiginUpButton.setOnAction(event -> {
            loginSiginUpButton.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader(AuthorizationApplication.class.getResource("SignUp-view.fxml"));


            try {
                loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();
        });

    }

    private void loginUser(String loginText, String loginPassword) {

    }

}
