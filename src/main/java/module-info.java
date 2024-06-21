module com.example.kursovayapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.kursovayapp to javafx.fxml;
    exports com.example.kursovayapp;
}