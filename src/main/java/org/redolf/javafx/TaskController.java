package org.redolf.javafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class TaskController implements Initializable {
    @FXML
    private Label count_label;

    @FXML
    private Label count_label1;

    @FXML
    private TextField number;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    void count(ActionEvent event) {
        invokeCounter();
    }

    public void invokeGenderCounter(){

    }
    public  void invokeCounter(){
        String value = number.getText();
        value = value.replaceAll(",","");
        long input = Long.parseLong(value);
        CounterTask counterTask = new CounterTask(input);
        counterTask.valueProperty().addListener((observable, oldValue, newValue) ->
                count_label.setText(String.valueOf(newValue)));
        Thread thread = new Thread(counterTask);
        thread.setDaemon(true);
        thread.start();
    }
}
