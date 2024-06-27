package com.example.kursovayapp;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Order {
    private StringProperty order_id;
    private StringProperty client_id;
    private StringProperty package_id;
    private StringProperty courier_id;
    private StringProperty center_name;


    public Order(){
        order_id = new SimpleStringProperty(this, "order_id");
        courier_id = new SimpleStringProperty(this, "courier_id");
        client_id = new SimpleStringProperty(this, "client_id");
        package_id = new SimpleStringProperty(this, "package_id");
        center_name = new SimpleStringProperty(this, "center_name");
    }

    public String getOrder_id() {
        return order_id.get();
    }

    public StringProperty order_idProperty() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id.set(order_id);
    }

    public String getClient_id() {
        return client_id.get();
    }

    public StringProperty client_idProperty() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id.set(client_id);
    }

    public String getPackage_id() {
        return package_id.get();
    }

    public StringProperty package_idProperty() {
        return package_id;
    }

    public void setPackage_id(String package_id) {
        this.package_id.set(package_id);
    }

    public String getCourier_id() {
        return courier_id.get();
    }

    public StringProperty courier_idProperty() {
        return courier_id;
    }

    public void setCourier_id(String courier_id) {
        this.courier_id.set(courier_id);
    }

    public String getCenter_name() {
        return center_name.get();
    }

    public StringProperty center_nameProperty() {
        return center_name;
    }

    public void setCenter_name(String center_name) {
        this.center_name.set(center_name);
    }
}
