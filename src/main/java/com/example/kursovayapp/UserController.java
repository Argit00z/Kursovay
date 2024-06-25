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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class UserController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button settingsButton;

    @FXML
    private AnchorPane rootpane;

    @FXML
    void initialize() {
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
