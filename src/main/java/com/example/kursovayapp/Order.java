package com.example.kursovayapp;

public class Order {
    private String order_id;
    private String client_id;
    private String package_id;
    private String courier_id;
    private String center_name;

    public Order(String order_id, String client_id, String package_id, String courier_id, String center_name) {
        this.order_id = order_id;
        this.client_id = client_id;
        this.package_id = package_id;
        this.courier_id = courier_id;
        this.center_name = center_name;
    }

    public Order(){

    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getPackage_id() {
        return package_id;
    }

    public void setPackage_id(String package_id) {
        this.package_id = package_id;
    }

    public String getCourier_id() {
        return courier_id;
    }

    public void setCourier_id(String courier_id) {
        this.courier_id = courier_id;
    }

    public String getCenter_name() {
        return center_name;
    }

    public void setCenter_name(String center_name) {
        this.center_name = center_name;
    }
}
