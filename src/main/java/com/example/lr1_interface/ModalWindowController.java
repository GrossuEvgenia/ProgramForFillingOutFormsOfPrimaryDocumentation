package com.example.lr1_interface;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.apache.commons.io.input.TeeInputStream;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.util.Beta;
import javafx.event.ActionEvent;

import java.io.*;

public class ModalWindowController {
    @FXML
    ComboBox postModalWindow1;
    @FXML
    ComboBox postModalWindow2;
    @FXML
    ComboBox postModalWindow3;

    @FXML
    TextField fullName1;
    @FXML
    TextField fullName2;
    @FXML
    TextField fullName3;

    @FXML
    Button saveButton;
    @FXML
    Button cancelButton;



    private HelloController helloController;

    private ObservableList<String> getData(String fileName) throws IOException {
        File file = new File(fileName);
        ObservableList<String> listDish = FXCollections.observableArrayList();

        FileReader reader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(reader);

        String line = bufferedReader.readLine();
        while (line != null) {
            String[] words = line.split(";");
            listDish.add(words[0]);
            line = bufferedReader.readLine();
        }
        bufferedReader.close();
        reader.close();


        return listDish;
    }

    public void setHelloController(HelloController helloController) {
        this.helloController = helloController;
    }

    @FXML
    public void initialize() throws IOException {

        postModalWindow1.setEditable(true);
        postModalWindow1.setItems(getData("C:\\Users\\User\\Desktop\\8 semester THE END\\useability interfeyse\\lr1_interface\\src\\main\\resources\\files\\post.txt"));
        new AutoCompleteComboBoxListener<>(postModalWindow1);
        postModalWindow2.setEditable(true);
        postModalWindow2.setItems(getData("C:\\Users\\User\\Desktop\\8 semester THE END\\useability interfeyse\\lr1_interface\\src\\main\\resources\\files\\post.txt"));
        new AutoCompleteComboBoxListener<>(postModalWindow2);
        postModalWindow3.setEditable(true);
        postModalWindow3.setItems(getData("C:\\Users\\User\\Desktop\\8 semester THE END\\useability interfeyse\\lr1_interface\\src\\main\\resources\\files\\post.txt"));
        new AutoCompleteComboBoxListener<>(postModalWindow3);
    }


    public ComboBox getPostModalWindow1() {
        return postModalWindow1;
    }

    public ComboBox getPostModalWindow2() {
        return postModalWindow2;
    }

    public ComboBox getPostModalWindow3() {
        return postModalWindow3;
    }

    public TextField getFullName1() {
        return fullName1;
    }

    public TextField getFullName2() {
        return fullName2;
    }

    public TextField getFullName3() {
        return fullName3;
    }

    public HelloController getHelloController() {
        return helloController;
    }

    @FXML
    public void saveButton() {
        //FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("main_window.fxml"));
        //helloController =fxmlLoader.getController();

        if(postModalWindow1.getValue()!=null)
            helloController.setPost1(postModalWindow1.getValue().toString());
        helloController.setFio1(fullName1.getText());
        if(postModalWindow2.getValue()!=null)
            helloController.setPost2(postModalWindow2.getValue().toString());
        helloController.setFio2(fullName2.getText());
        if(postModalWindow3.getValue()!=null)
            helloController.setPost3(postModalWindow3.getValue().toString());
        helloController.setFio3(fullName3.getText());
        Stage stage = (Stage) saveButton.getScene().getWindow();
        stage.close();

    }
    @FXML
    public void cancel(){
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}