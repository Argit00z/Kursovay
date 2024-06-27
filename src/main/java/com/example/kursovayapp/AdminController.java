package com.example.kursovayapp;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;

import java.sql.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.AnchorPane;

public class AdminController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button addButton;

    @FXML
    private ChoiceBox<String> centerName;

    @FXML
    private TableColumn<Courier, String> center_nameCol;

    @FXML
    private TextField courierNameField;

    @FXML
    private TextField courierPhoneField;

    @FXML
    private TableColumn<Courier, String> courier_idCol;

    @FXML
    private TableColumn<Courier, String> courier_nameCol;

    @FXML
    private TableColumn<Courier, String> courier_phoneCol;

    @FXML
    private Button deleteButton;

    @FXML
    private Button quitButton;

    @FXML
    private AnchorPane rootpane;

    @FXML
    private TableView<Courier> couriers;
    @FXML
    private Button updateButton;
    @FXML
    private ChoiceBox<String> tableCheckBox;

    @FXML
    void Add(ActionEvent event) {
        String courier_name = courierNameField.getText();
        String courier_phone = courierPhoneField.getText();
        String center_name = centerName.getValue();
        DatabaseHandler db = new DatabaseHandler();
        try
        {

            pst = db.getDbConnection().prepareStatement("INSERT INTO " + Const.COURIER_TABLE +"(" + Const.COURIER_NAME + ", "
                    + Const.COURIER_PHONE + ", " + Const.CENTER_NAME + ")" + "VALUES(?,?,?)");
            pst.setString(1, courier_name);
            pst.setString(2, courier_phone);
            pst.setString(3, center_name);
            pst.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Регистрация курьера");

            alert.setHeaderText("Регистрация");
            alert.setContentText("Курьер добавлен!");
            alert.showAndWait();
            couriers();

            courierNameField.setText("");
            courierPhoneField.setText("");
            centerName.setValue("");
            courierNameField.requestFocus();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void couriers() throws SQLException, ClassNotFoundException {
        Connect();
        ObservableList<Courier> courier = FXCollections.observableArrayList();
        DatabaseHandler db = new DatabaseHandler();
        try {
            pst = db.getDbConnection().prepareStatement("SELECT * FROM " + Const.COURIER_TABLE);
            ResultSet rs = pst.executeQuery();
            {
                while (rs.next())
                {
                    Courier c = new Courier();
                    c.setCourier_id(rs.getString(1));
                    c.setCourier_name(rs.getString(2));
                    c.setCourier_phone(rs.getString(3));
                    c.setCenter_name(rs.getString(4));
                    courier.add(c);
                }
            }
            couriers.setItems(courier);
            courier_idCol.setCellValueFactory(f -> f.getValue().courier_idProperty());
            courier_nameCol.setCellValueFactory(f -> f.getValue().courier_nameProperty());
            courier_phoneCol.setCellValueFactory(f -> f.getValue().courier_phoneProperty());
            center_nameCol.setCellValueFactory(f -> f.getValue().center_nameProperty());
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        couriers.setRowFactory( tv -> {
            TableRow<Courier> myRow = new TableRow<>();
            myRow.setOnMouseClicked (event ->
            {
                if (event.getClickCount() == 1 && (!myRow.isEmpty()))
                {
                    myIndex =  couriers.getSelectionModel().getSelectedIndex();

                    id = Integer.parseInt(String.valueOf(couriers.getItems().get(myIndex).getCourier_id()));
                    courierNameField.setText(couriers.getItems().get(myIndex).getCourier_name());
                    courierPhoneField.setText(couriers.getItems().get(myIndex).getCourier_phone());
                    centerName.setValue(couriers.getItems().get(myIndex).getCenter_name());

                }
            });
            return myRow;
        });


    }
    @FXML
    void Delete(ActionEvent event) {
        myIndex = couriers.getSelectionModel().getSelectedIndex();

        id = Integer.parseInt(String.valueOf(couriers.getItems().get(myIndex).getCourier_id()));
        DatabaseHandler db = new DatabaseHandler();

        try
        {
            pst = db.getDbConnection().prepareStatement("DELETE FROM " + Const.COURIER_TABLE + " WHERE " + Const.COURIER_ID + " = ?");
            pst.setInt(1, id);
            pst.executeUpdate();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Регистрация курьера");

            alert.setHeaderText("Регистрация");
            alert.setContentText("Удалено!");
            alert.showAndWait();
            couriers();
        }
        catch (SQLException | ClassNotFoundException ex)
        {;}
    }
    @FXML
    void Update(ActionEvent event) {

        myIndex = couriers.getSelectionModel().getSelectedIndex();

        id = Integer.parseInt(String.valueOf(couriers.getItems().get(myIndex).getCourier_id()));

        String courier_name = courierNameField.getText();
        String courier_phone = courierPhoneField.getText();
        String center_name = centerName.getValue();
        DatabaseHandler db = new DatabaseHandler();
        try
        {
            pst = db.getDbConnection().prepareStatement("UPDATE " + Const.COURIER_TABLE + " SET " +
                    Const.COURIER_NAME + " = ?, " + Const.COURIER_PHONE + " = ?, " +
                    Const.CENTER_NAME + " = ? WHERE " + Const.COURIER_ID + " = ?");

            pst.setString(1, courier_name);
            pst.setString(2, courier_phone);
            pst.setString(3, center_name);
            pst.setInt(4, id);
            pst.executeUpdate();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Регистрация курьера");

            alert.setHeaderText("Регистрация курьера");
            alert.setContentText("Обновлено!");
            alert.showAndWait();
            couriers();
        }
        catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private PreparedStatement pst;
    private int myIndex;
    private int id;

    public void Connect() throws SQLException, ClassNotFoundException {
        DatabaseHandler db = new DatabaseHandler();
        db.getDbConnection();
    }

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        tableCheckBox.setValue("Курьеры");
        tableCheckBox.getItems().addAll("Заказы", "Курьеры");
        tableCheckBox.setOnAction(event -> {
            if (tableCheckBox.getValue().equals("Заказы")){
                try {
                    openNewScene("OrdersTable-view.fxml");

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else if (tableCheckBox.getValue().equals("Курьеры")){
                try {
                    openNewScene("Admin-view.fxml");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        Connect();
        couriers();


        DatabaseHandler db = new DatabaseHandler();
        String select = "SELECT * FROM " + Const.CENTER_TABLE;

        try {
            pst = db.getDbConnection().prepareStatement(select);
            ResultSet restSet = pst.executeQuery(select);

            while (restSet.next()){

                centerName.getItems().add(restSet.getString(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        quitButton.setOnAction(event -> {
            try {
                openNewScene("Authorization-view.fxml");
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
