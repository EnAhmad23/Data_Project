package com.example.test_javafx;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;
import java.util.Objects;

public class Navigation {
    public final String MAIN_FXML = "views/start.fxml";
    public final String ADD_SECTION_FXML = "views/addNewSection.fxml";
    public final String LECTURES_TIMES_FXML = "views/lecturestimes.fxml";
    public final String ENROLL_STUDENT_FXML = "views/enrollstudent.fxml";
    public final String Add_STUDENT_FXML = "views/addStudent.fxml";
    public final String Admin_FXML = "views/start.fxml";
    public final String Users_FXML = "views/users.fxml";
    public final String ADD_USER_FXML = "views/users.fxml";


    public void navigateTo(Parent rootPane, String path) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(path)));
            rootPane.getScene().setRoot(root);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
