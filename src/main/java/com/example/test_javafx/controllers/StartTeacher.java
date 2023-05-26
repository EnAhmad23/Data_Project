package com.example.test_javafx.controllers;

import com.example.test_javafx.Navigation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class StartTeacher implements Initializable {
    @FXML
    AnchorPane root;
    Navigation nav = new Navigation();
    public void back(ActionEvent actionEvent) {
        nav.navigateTo(root,nav.LOGIN);
    }

    public void students(ActionEvent actionEvent) {
        Navigation.owner =(nav.TEACHING_FXML);
        nav.navigateTo(root,nav.STUDENTS_FXML);
    }

    public void lectures(ActionEvent actionEvent) {
        Navigation.owner =(nav.TEACHING_FXML);
        nav.navigateTo(root,nav.LECTURES_TIMES_FXML);
    }

    public void attendance_page(ActionEvent actionEvent) {
        nav.navigateTo(root,nav.ATTENDENCE_FXML);
    }

    public void report_statement_page(ActionEvent actionEvent) {
        nav.navigateTo(root,nav.REPORT_STUDENT);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
