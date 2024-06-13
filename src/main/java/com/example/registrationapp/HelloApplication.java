package com.example.registrationapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage)  {
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
//        Scene scene = null;
//        try {
//            scene = new Scene(fxmlLoader.load(), 320, 240);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Scene scene = new Scene(root);
        stage.setTitle("Login Page");
        stage.setScene(scene);
        stage.setResizable(true);
        stage.show();
    }
    // set classpath=%classpath%;C:\Users\Sebbie\Downloads\Netbeans and JDK8\mysql-connector-java-5.1.26.jar;
    //

    public static void main(String[] args) {
        launch();
    }
}