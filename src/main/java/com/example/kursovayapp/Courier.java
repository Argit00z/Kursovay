package com.example.kursovayapp;

public class Courier {

    private String courier_id;
    private String courier_name;
    private String courier_phone;

    public Courier(String courier_id, String courier_name, String courier_phone) {
        this.courier_id = courier_id;
        this.courier_name = courier_name;
        this.courier_phone = courier_phone;
    }

    public Courier(){

    }

    public String getCourier_id() {
        return courier_id;
    }

    public void setCourier_id(String courier_id) {
        this.courier_id = courier_id;
    }

    public String getCourier_name() {
        return courier_name;
    }

    public void setCourier_name(String courier_name) {
        this.courier_name = courier_name;
    }

    public String getCourier_phone() {
        return courier_phone;
    }

    public void setCourier_phone(String courier_phone) {
        this.courier_phone = courier_phone;
    }
}
