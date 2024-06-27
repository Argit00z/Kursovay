package com.example.kursovayapp;

public class Client {
    private String client_name;
    private String client_phone;
    private String client_address;
    private String password;
    private String client_id;


    public Client(String client_name, String client_phone, String client_address, String password) {
        this.client_name = client_name;
        this.client_phone = client_phone;
        this.client_address = client_address;
        this.password = password;
    }

    public Client() {

    }

    public String getClient_name() {
        return client_name;
    }

    public void setClient_name(String client_name) {
        this.client_name = client_name;
    }

    public String getClient_phone() {
        return client_phone;
    }

    public void setClient_phone(String client_phone) {
        this.client_phone = client_phone;
    }

    public String getClient_address() {
        return client_address;
    }

    public void setClient_address(String client_address) {
        this.client_address = client_address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
