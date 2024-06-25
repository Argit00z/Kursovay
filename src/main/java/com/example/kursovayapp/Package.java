package com.example.kursovayapp;

public class Package {
    private String package_id;
    private String weight_package;
    private String urgency_package;

    public Package(String package_id, String weight_package, String urgency_package) {
        this.package_id = package_id;
        this.weight_package = weight_package;
        this.urgency_package = urgency_package;
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
