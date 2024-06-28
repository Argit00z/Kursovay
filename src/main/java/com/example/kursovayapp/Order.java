package com.example.kursovayapp;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Order {
    private final StringProperty order_id;
    private final StringProperty client_id;
    private final StringProperty package_id;


    public Order(){
        order_id = new SimpleStringProperty(this, "order_id");
        client_id = new SimpleStringProperty(this, "client_id");
        package_id = new SimpleStringProperty(this, "package_id");
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

}
