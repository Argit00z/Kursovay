package com.example.kursovayapp;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import animations.Shake;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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
    private TextField phoneField;

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
            String phone = phoneField.getText().trim();

            DatabaseHandler dbHandler = new DatabaseHandler();
            Client client = new Client();
            client.setClient_phone(phone);
            client.setPassword(oldPass);
            ResultSet result = dbHandler.getClient(client);
            int counter = 0;
            while (true) {
                try {
                    if (!result.next())
                        break;
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                counter++;
            }

            if (counter >= 1) {
                try
                {
                    PreparedStatement prst;
                    DatabaseHandler db = new DatabaseHandler();
                    prst = db.getDbConnection().prepareStatement("UPDATE " + Const.CLIENT_TABLE + " SET " +
                            Const.CLIENT_PASSWORD + " = ? WHERE " + Const.CLIENT_PHONE + " = ?");

                    prst.setString(1, newPass);
                    prst.setString(2, phone);
                    prst.executeUpdate();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Смена пароля");

                    alert.setHeaderText("Смена пароля");
                    alert.setContentText("Успешно!");
                    alert.showAndWait();

                    phoneField.setText("");
                    oldPassword.setText("");
                    newPassword.setText("");

                }
                catch (SQLException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
            else {
                Shake clientloginAnim = new Shake(phoneField);
                Shake clientpasswordAnim = new Shake(oldPassword);
                clientloginAnim.playAnim();
                clientpasswordAnim.playAnim();

            }



        });


    }
    public void openNewScene(String window) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource(window));
        rootpane.getChildren().setAll(pane);
    }

}
