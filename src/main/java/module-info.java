module com.example.fuelcard {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    opens com.example.fuelcard.controllers to javafx.fxml;
    exports com.example.fuelcard.controllers;
    exports com.example.fuelcard;
    opens com.example.fuelcard to javafx.fxml;
}