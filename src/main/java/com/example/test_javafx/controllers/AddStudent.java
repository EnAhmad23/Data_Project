package com.example.test_javafx.controllers;

import com.example.test_javafx.Navigation;
import com.example.test_javafx.models.DBModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

import java.net.URL;
import java.sql.Array;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddStudent implements Initializable {
    @FXML
    private Label label = new Label();
    @FXML
    private AnchorPane root;
    @FXML
    private TextField id;
    @FXML
    private TextField name;
    @FXML
    private TextField major;
    @FXML
    private TextField place;

    @FXML
    private ComboBox gender;

    @FXML
    private TextField phone_number;
    @FXML
    private Button btn_add;

    @FXML
    private Button id_back_addStu;

    private String genders[] = {"male", "female"};
    Navigation nav = new Navigation();
    DBModel dm = DBModel.getModel();

    public void addStudntBotton()  {
        String stu_id = id.getText();
        String stu_name = name.getText();
        String stu_major = major.getText();
        String stu_place =  place.getText();
        String stu_gender = (String) gender.getValue();
        String stu_phone = phone_number.getText();

        if (dm.addStudent(stu_id, stu_name, stu_gender,  stu_major,stu_place,stu_phone) != 0) {
            label.setTextFill(Color.color(0, 1, 0));
            label.setText("Student added successfully");

        } else {
            label.setTextFill(Color.color(1, 0, 0));
            label.setText("Student did'nt add");
        }
        id.clear();
        name.clear();
        major.clear();
        place.clear();
        phone_number.clear();
    }


    public void back_addStu() {
        nav.navigateTo(root, nav.STUDENTS_FXML);
    }
    public void esc(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ESCAPE) {
            back_addStu();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gender.getItems().addAll(genders);
    }
}
