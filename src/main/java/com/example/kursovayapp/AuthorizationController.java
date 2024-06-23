package com.example.kursovayapp;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import animations.Shake;
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
            openNewScene("SignUp-view.fxml");
        });

    }

    private void loginUser(String loginText, String loginPassword) {
        DatabaseHandler dbHandler = new DatabaseHandler();
        Client client = new Client();
        client.setClient_phone(loginText);
        client.setPassword(loginPassword);
        ResultSet result = dbHandler.getClient(client);
        
        int counter = 0;

        while (true){
            try {
                if (!result.next()) break;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            counter++;
        }

        if (counter >= 1){
            openNewScene("User-view.fxml");
        }else {
            Shake clientloginAnim = new Shake(login_field);
            Shake clientpasswordAnim = new Shake(password_field);
            clientloginAnim.playAnim();
            clientpasswordAnim.playAnim();

        }
    }

    public void openNewScene(String window){
        loginSiginUpButton.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader(AuthorizationApplication.class.getResource(window));


        try {
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }

}
