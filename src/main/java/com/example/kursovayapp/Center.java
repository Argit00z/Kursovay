package com.example.kursovayapp;

public class Center {
    private String center_name;
    private String center_address;

    public Center(String center_name, String center_address){
        this.center_name = center_name;
        this.center_address = center_address;
    }

    public Center(){

    }

    public String getCenter_name() {
        return center_name;
    }

    public void setCenter_name(String center_name) {
        this.center_name = center_name;
    }

    public String getCenter_address() {
        return center_address;
    }

    public void setCenter_address(String center_address) {
        this.center_address = center_address;
    }
}
