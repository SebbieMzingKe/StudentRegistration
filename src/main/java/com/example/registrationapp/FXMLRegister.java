package com.example.registrationapp;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class FXMLRegister {
    @FXML TextField username;

    @FXML PasswordField password;
    @FXML PasswordField confirm;

    @FXML Button cancel;

    @FXML Button register;
    @FXML Hyperlink back;

    @FXML
    void handle_back(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        Stage stage = (Stage)((Hyperlink)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Login Page");
        stage.show();
    }

    @FXML
    void handle_register(ActionEvent event) throws IOException {
        String usern = username.getText();
        String passn = password.getText();
        String passconf = confirm.getText();

        Connection con = ConnectDB.connectDB();



        if(passn.equals(passconf)){
            String sqluser = "SELECT dbuser from users WHERE dbuser = ? ";
            try {
                PreparedStatement stateuser = con.prepareStatement(sqluser);
                stateuser.setString(1, usern);
                ResultSet userset = stateuser.executeQuery();
                if(userset.next()){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setTitle("Password Message");
                    //content inside the alert
                    alert.setContentText("Username already taken. Choose another.");
                    alert.showAndWait();
                }
                else{
                    String sql_update =  "INSERT INTO users VALUES(?, ?)";
                    PreparedStatement state_update = con.prepareStatement(sql_update);
                    state_update.setString(1, usern);
                    state_update.setString(2, password.getText());
                    int x = state_update.executeUpdate();
                    if(x == 1){
                        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("hello-view.fxml")));
                        Stage stage = (Stage)((Button)event.getSource()).getScene().getWindow();
                        stage.setTitle("Login Page");
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();
                    }
                }
            } catch (SQLException ex) {
            }
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Password Message");
            //content inside the alert
            alert.setContentText("Password Confirmed does not match");
            alert.showAndWait();

        }


    }
}
