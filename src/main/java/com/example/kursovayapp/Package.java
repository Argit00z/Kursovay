package com.example.kursovayapp;

public class Package {
    private String package_id;
    private String weight_package;
    private String urgency_package;
    private String send_center_name;
    private String courier_id;

    public Package(String package_id, String weight_package, String urgency_package, String send_center_name, String courier_id) {
        this.package_id = package_id;
        this.weight_package = weight_package;
        this.urgency_package = urgency_package;
        this.send_center_name = send_center_name;
        this.courier_id = courier_id;
    }

    public Package(String weight_package, String urgency_package, String send_center_name, String courier_id) {
        this.weight_package = weight_package;
        this.urgency_package = urgency_package;
        this.send_center_name = send_center_name;
        this.courier_id = courier_id;
    }

    public String getSend_center_name() {
        return send_center_name;
    }

    public void setSend_center_name(String send_center_name) {
        this.send_center_name = send_center_name;
    }

    public String getCourier_id() {
        return courier_id;
    }

    public void setCourier_id(String courier_id) {
        this.courier_id = courier_id;
    }

    public Package(){

    }

    public String getPackage_id() {
        return package_id;
    }

    public void setPackage_id(String package_id) {
        this.package_id = package_id;
    }

    public String getWeight_package() {
        return weight_package;
    }

    public void setWeight_package(String weight_package) {
        this.weight_package = weight_package;
    }

    public String getUrgency_package() {
        return urgency_package;
    }

    public void setUrgency_package(String urgency_package) {
        this.urgency_package = urgency_package;
    }
}
