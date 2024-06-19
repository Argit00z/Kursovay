module com.example.kursovayapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.kursovayapp to javafx.fxml;
    exports com.example.kursovayapp;
}