package com.example.kursovayapp;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

public class OrderTableController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button quitButton;

    @FXML
    private AnchorPane rootpane;

    @FXML
    private ChoiceBox<String> tableCheckBox;

    @FXML
    private TableColumn<Order, String> center_nameCol;

    @FXML
    private TableColumn<Order, String> client_idCol;

    @FXML
    private TableColumn<Order, String> courier_idCol;

    @FXML
    private TableColumn<Order, String> order_idCol;

    @FXML
    private TableView<Order> orders;

    @FXML
    private TableColumn<Order, String> package_idCol;

    public void orders() throws SQLException, ClassNotFoundException {
        Connect();
        ObservableList<Order> order = FXCollections.observableArrayList();
        DatabaseHandler db = new DatabaseHandler();
        try {
            pst = db.getDbConnection().prepareStatement("SELECT * FROM " + Const.ORDER_TABLE);
            ResultSet rs = pst.executeQuery();
            {
                while (rs.next())
                {
                    Order ord = new Order();
                    ord.setOrder_id(rs.getString(1));
                    ord.setClient_id(rs.getString(2));
                    ord.setPackage_id(rs.getString(3));
                    ord.setCourier_id(rs.getString(4));
                    ord.setCenter_name(rs.getString(5));

                    order.add(ord);
                }
            }
            orders.setItems(order);
            order_idCol.setCellValueFactory(f -> f.getValue().order_idProperty());
            client_idCol.setCellValueFactory(f -> f.getValue().client_idProperty());
            package_idCol.setCellValueFactory(f -> f.getValue().package_idProperty());
            courier_idCol.setCellValueFactory(f -> f.getValue().courier_idProperty());
            center_nameCol.setCellValueFactory(f -> f.getValue().center_nameProperty());

        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
//        orders.setRowFactory( tv -> {
//            TableRow<Courier> myRow = new TableRow<>();
//            myRow.setOnMouseClicked (event ->
//            {
//                if (event.getClickCount() == 1 && (!myRow.isEmpty()))
//                {
//                    myIndex =  orders.getSelectionModel().getSelectedIndex();
//
//                    id = Integer.parseInt(String.valueOf(orders.getItems().get(myIndex).getOrder_id()));
//                    courierNameField.setText(couriers.getItems().get(myIndex).getCourier_name());
//                    courierPhoneField.setText(couriers.getItems().get(myIndex).getCourier_phone());
//                    centerName.setValue(couriers.getItems().get(myIndex).getCenter_name());
//
//                }
//            });
//            return myRow;
//        });


    }

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {

        orders();

        quitButton.setOnAction(event -> {
            try {
                openNewScene("Authorization-view.fxml");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        tableCheckBox.setValue("Заказы");
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

    }
    private PreparedStatement pst;
    private int myIndex;
    private int id;

    public void Connect() throws SQLException, ClassNotFoundException {
        DatabaseHandler db = new DatabaseHandler();
        db.getDbConnection();
    }

    public void openNewScene(String window) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource(window));
        rootpane.getChildren().setAll(pane);
    }

}
