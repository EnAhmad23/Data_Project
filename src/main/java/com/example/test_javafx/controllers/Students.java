package com.example.test_javafx.controllers;

import com.example.test_javafx.Navigation;
import com.example.test_javafx.models.DBModel;
import com.example.test_javafx.models.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.TextFields;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class Students implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private TextField t_id;
//    @FXML
//    private TextField s_name;
    @FXML
    private TableColumn<Student, String> id;
    @FXML
    private TableColumn<Student, String> name;
    @FXML
    private TableColumn<Student, String> gender;
    @FXML
    private TableColumn<Student, String> place;
    @FXML
    private TableColumn<Student, String> major;
    @FXML
    private TableColumn<Student, String> phone_num;
//    @FXML
//    private TextField department;
    @FXML
    private Button add;
    @FXML
    private Button update;
    @FXML
    private Button delete;
    @FXML
    private Button back;
    @FXML
    private TableView<Student> table;
    @FXML
    private Button id_close_students;

    DBModel dm = DBModel.getModel();
    Navigation nav = new Navigation();


    public void addStudent() {
        nav.navigateTo(root, nav.Add_STUDENT_FXML);
    }

    public void UpdateStudent() throws IOException {
        Parent rootP = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("views/updateStudent.fxml")));

//            rootPane.getScene().setRoot(root);
        Stage stage = new Stage();
        Scene scene = new Scene(rootP);
        stage.setScene(scene);
        stage.show();
//        nav.navigateTo(root, nav.UPDATE_STUDENT);

    }

    public void deleteStudent() {
        Stage stage = new Stage();
        VBox root = new VBox();
        root.setSpacing(20);
        root.setAlignment(Pos.BASELINE_CENTER);
        root.setStyle("-fx-padding: 20px; -fx-background-color:   #DEE4E7");
        Label label =new Label("STUDENT DELETED !");
        if ( dm.delete_Student(t_id.getText())!=0) {
            view();
            label.setTextFill(Color.color(0,0,0));
        }else {
            label.setTextFill(Color.color(1, 0, 0));
            label.setText("STUDENT DIDN'T DELETE !");
        }
        root.getChildren().add(label);
        stage.setScene(new Scene(root, 300, 100));
        stage.show();
//
//        stage.setScene(new Scene());

    }

    public void enterStudentID() {

    }

    public void enterStudentName() {

    }

    public void enterStudentDepartment() {

    }

    public void Update_back() {
//        if (nav.getCurrentPath().equals(nav.MAIN_FXML))
//            nav.navigateTo(root, nav.MAIN_FXML);
//        else
//            nav.navigateTo(root, nav.TEACHING_FXML);
        nav.navigateTo(root, nav.MAIN_FXML);
    }
    public void view (){
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("Name"));
        gender.setCellValueFactory(new PropertyValueFactory<>("Gender"));
        major.setCellValueFactory(new PropertyValueFactory<>("Majer"));
        place.setCellValueFactory(new PropertyValueFactory<>("Place"));
        phone_num.setCellValueFactory(new PropertyValueFactory<>("phone_num"));


        ObservableList<Student> ids = FXCollections.observableArrayList(dm.getStd());
        table.setItems(ids);
    }

//    public void searchStudent() {
//        if(t_id.getText().isEmpty()){
//            view();
//        }else{
//
//            viewSearch();
////            }
////            for(int i =0 ;i < dm.getStudentIds() ;i++ ){
////
////            }
//        }
//    }
    public void viewSearch(){
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("Name"));
        gender.setCellValueFactory(new PropertyValueFactory<>("Gender"));
        place.setCellValueFactory(new PropertyValueFactory<>("Place"));
        major.setCellValueFactory(new PropertyValueFactory<>("Majer"));

        ObservableList<Student> ids = FXCollections.observableArrayList(dm.searchStudent(t_id.getText()));
        table.setItems(ids);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        start(new Stage() );
        view();
    }

//    public void searchStudent(ActionEvent actionEvent) {
//    }

    public void searchStudent() {
        ArrayList<String> list = new ArrayList<>();
        for (Student s : dm.getStd()) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(s.getId());
            stringBuilder.append(",");
            stringBuilder.append(s.getName());
            stringBuilder.append(",");
            stringBuilder.append(s.getGender());
            stringBuilder.append(",");
            stringBuilder.append(s.getMajer());
            stringBuilder.append(",");
            stringBuilder.append(s.getPlace());
            stringBuilder.append(",");
            stringBuilder.append(s.getPhone_num());
            list.add(stringBuilder.toString());
            System.out.println(stringBuilder);
        }
        TextFields.bindAutoCompletion(t_id, list.toArray());

    }
    private static final String[] DATA = {
            "Apple",
            "Banana",
            "Cherry",
            "Grape",
            "Lemon",
            "Orange",
            "Peach",
            "Strawberry"
    };


    public void start(Stage primaryStage) {
//        TextField t_id = new TextField();
        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.setVisible(false);

        t_id.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.DOWN) {
                comboBox.requestFocus();
                comboBox.getSelectionModel().selectFirst();
            }
        });

        t_id.textProperty().addListener((observable, oldValue, newValue) -> {
            String input = t_id.getText().toLowerCase();

            if (input.isEmpty()) {
                comboBox.setVisible(false);
            } else {
                comboBox.setItems(FXCollections.observableArrayList(getMatchingItems(input)));
                comboBox.setVisible(true);
            }
        });

        comboBox.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                t_id.setText(comboBox.getSelectionModel().getSelectedItem());
                comboBox.setVisible(false);
            } else if (event.getCode() == KeyCode.ESCAPE) {
                comboBox.setVisible(false);
            }
        });

        VBox root = new VBox(t_id, comboBox);
        Scene scene = new Scene(root, 200, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private String[] getMatchingItems(String input) {
        return FXCollections.observableArrayList(DATA).filtered(item -> item.toLowerCase().startsWith(input)).toArray(new String[0]);
    }


}
