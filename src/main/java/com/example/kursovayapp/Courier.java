package com.example.kursovayapp;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Courier {

    private final StringProperty courier_id;
    private final StringProperty courier_name;
    private final StringProperty courier_phone;
    private final StringProperty center_name;



    public Courier(){
        courier_id = new SimpleStringProperty(this, "courier_id");
        courier_name = new SimpleStringProperty(this, "courier_name");
        courier_phone = new SimpleStringProperty(this, "courier_phone");
        center_name = new SimpleStringProperty(this, "center_name");
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

    public String getCourier_name() {
        return courier_name.get();
    }

    public StringProperty courier_nameProperty() {
        return courier_name;
    }

    public void setCourier_name(String courier_name) {
        this.courier_name.set(courier_name);
    }

    public String getCourier_phone() {
        return courier_phone.get();
    }

    public StringProperty courier_phoneProperty() {
        return courier_phone;
    }

    public void setCourier_phone(String courier_phone) {
        this.courier_phone.set(courier_phone);
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
