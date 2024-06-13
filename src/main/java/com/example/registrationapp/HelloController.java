package com.example.registrationapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class HelloController {
    @FXML
    TextField username;
    @FXML
    PasswordField password;
    @FXML
    Button cancel;

    @FXML
    public void handle_login_button(ActionEvent event) throws IOException {
        Connection conn = ConnectDB.connectDB();
        String user = username.getText();
        String pass = password.getText();
        String sql = "SELECT dbpass from users WHERE dbuser = ? ";
        try {
            PreparedStatement state = conn.prepareStatement(sql);
            state.setString(1, user);
            ResultSet set = state.executeQuery();
            if (set.next()) {
                String passfromdb = set.getString(1);
                //System.out.println("Password from db " + passfromdb);

                if (passfromdb.equals(pass)) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
//                    alert.setWidth(50);
//                    alert.setHeight(20);
                    alert.setHeaderText(null);
                    alert.setTitle("Login");
                    alert.setContentText("Welcome back " + user);
                    alert.showAndWait();
//                    Parent root = FXMLLoader.load(getClass().getResource("FXMLStudent.fxml"));
                    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("FXMLStudent.fxml"));
                    Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                    Scene scene = new Scene(fxmlLoader.load(), 320, 240);
                    stage.setScene(scene);
                    stage.setTitle("Login Student Details");
                    stage.show();
                }
                else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
//                    alert.setWidth(50);
//                    alert.setHeight(20);
                    alert.setHeaderText(null);
                    alert.setTitle("Login");
                    alert.setContentText("Ooops! Wrong username or password! " + user + " does not exist");
                    alert.showAndWait();
                }


            }
        } catch (SQLException ex) {

        }
    }

    @FXML
    public void handle_cancel_button() {
        username.setText(null);
        password.setText(null);
    }

    @FXML
    void handle_register_button(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("FXMLRegister.fxml"));
        Stage stage = (Stage) ((Hyperlink) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setScene(scene);
        stage.setTitle("Register User");
        stage.show();
    }
}


