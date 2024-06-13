module com.example.registrationapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.registrationapp to javafx.fxml;
    exports com.example.registrationapp;
}