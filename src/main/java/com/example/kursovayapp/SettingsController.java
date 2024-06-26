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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SettingsController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button backButton;

    @FXML
    private Button quitButton;

    @FXML
    private AnchorPane rootpane;

    @FXML
    private PasswordField newPassword;

    @FXML
    private PasswordField oldPassword;

    @FXML
    private Button confirmButton;

    @FXML
    void initialize() {

        backButton.setOnAction(event -> {
            try {
                openNewScene("User-view.fxml");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        quitButton.setOnAction(event -> {
            try {
                openNewScene("Authorization-view.fxml");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        confirmButton.setOnAction(event -> {
            String oldPass = oldPassword.getText().trim();
            String newPass = newPassword.getText().trim();


        });


    }
    public void openNewScene(String window) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource(window));
        rootpane.getChildren().setAll(pane);
    }

}
