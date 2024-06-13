package com.example.registrationapp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.EventObject;
import java.util.function.UnaryOperator;


public class FXMLStudent implements initialize{
    @FXML
    TextField reg_no;
    @FXML
    TextField first_name;
    @FXML
    TextField last_name;
    @FXML
    TextField middle_name;
    @FXML
    RadioButton female;
    @FXML
    RadioButton male;
    @FXML
    DatePicker dob;
    @FXML
    TextField phone_no;
    @FXML
    PasswordField password;
    @FXML
    ComboBox p_code;

    private void clear(){
        reg_no.setText(null);
        first_name.setText(null);
        last_name.setText(null);
        middle_name.setText(null);
        dob.setValue(null);
        phone_no.setText(null);
        password.setText(null);
        p_code.setValue(null);
    }
    @FXML
    public void handle_save(ActionEvent event){
        Connection conn = ConnectDB.connectDB();
        String sql = "INSERT INTO students VALUES(?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement stud_state = conn.prepareStatement(sql);
            stud_state.setString(1, reg_no.getText());
            stud_state.setString(2, first_name.getText());
            stud_state.setString(3, middle_name.getText());
            stud_state.setString(4, last_name.getText());
            String gender = "Female";
            ToggleGroup tog_sex = new ToggleGroup();
            female.setToggleGroup(tog_sex);
            male.setToggleGroup(tog_sex);
            if(male.isSelected()){
                gender = "Male";

            }
            if(female.isSelected()) {
                gender = "Female";
            }
            stud_state.setString(5, gender);
            stud_state.setString(6, dob.getValue().toString());
            stud_state.setString(7, phone_no.getText());
            stud_state.setString(8, p_code.getValue().toString());
            stud_state.setString(9, password.getText());

            int x = stud_state.executeUpdate();
            if(x == 1){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setTitle("Register Information");
                alert.setContentText("Student saved successfully");
                alert.show();
                clear();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    @FXML
    public void handle_logout(ActionEvent event) throws IOException {
        Logout out = new Logout();
        out.logout(event);
    }
    public void initialize(){

        UnaryOperator<TextFormatter.Change> name_filter = new UnaryOperator<TextFormatter.Change>() {
            @Override
            public TextFormatter.Change apply(TextFormatter.Change change) {
                String new_text = change.getControlNewText();
//                if (new_phone.matches("^0[17]([0-9]{0,8})$")){
                if (new_text.matches("^([A-Z]{0,50}[a-z]{0,50}$)")){
                    return change;

                }
                return null;
            }

        };
        first_name.setTextFormatter(new TextFormatter<>(name_filter));
        middle_name.setTextFormatter(new TextFormatter<>(name_filter));
        last_name.setTextFormatter(new TextFormatter<>(name_filter));

        UnaryOperator<TextFormatter.Change> phone_filter = new UnaryOperator<TextFormatter.Change>() {
            @Override
            public TextFormatter.Change apply(TextFormatter.Change change) {
                String new_phone = change.getControlNewText();
                if (new_phone.matches("^(0[17][0-9]{8})$")){
                    return change;

                }
                return null;
            }
        };

//        phone_no.setTextFormatter(new TextFormatter<>(phone_filter));
//        String [] progs = {
//                "SIT", "COM", "DIT", "SIK", "ETS"
//        };
//        ObservableList list = FXCollections.observableArrayList(progs);
//        p_code.setItems(list);
        try {
            Connection conn = ConnectDB.connectDB();
            String p_code_sql = "SELECT pcode from programs";
            PreparedStatement state_p_code = conn.prepareStatement(p_code_sql);
            ResultSet p_code_set = state_p_code.executeQuery();
            ObservableList p_code_list = FXCollections.observableArrayList();

            while (p_code_set.next()){
                p_code_list.add(p_code_set.getString(1));

            }
            p_code.setItems(p_code_list);
        }
        catch(SQLException ex){

        }
    }


}
