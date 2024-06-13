package com.example.registrationapp;

import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
    public static Connection connectDB(){
        Connection conn = null;
        String url = "jdbc:mysql://localhost:3306/Registration";
        String user = "root";
        String pass = "Seb#@Evayo1";
        try{
            conn = DriverManager.getConnection(url, user, pass);
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            //header
//            alert.setHeaderText(null);
//            alert.setTitle("Connection Done");
//            //content inside the alert
//            alert.setContentText("Connection was successful");
//            alert.showAndWait();
        }
        catch (SQLException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            //header
            alert.setHeaderText(null);
            alert.setTitle("Connection Failed");
            //content inside the alert
            alert.setContentText(ex.getMessage());
            alert.showAndWait();

        }
        return conn;
    }
}
