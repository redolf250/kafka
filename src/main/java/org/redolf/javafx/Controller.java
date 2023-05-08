package org.redolf.javafx;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.redolf.config.Configuration;
import org.redolf.model.Customer;
import org.redolf.serializer.CustomDeserializer;

import java.net.URL;
import java.time.Duration;
import java.util.Collections;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.Thread.sleep;

public class Controller implements Initializable {

    @FXML
    private Label bigender;

    @FXML
    private Label females;

    @FXML
    private Label males;

    @FXML
    private Label males1;

    @FXML
    private Label males11;

    @FXML
    private Label males111;

    @FXML
    private Label males1111;

    @FXML
    private Label males11111;

    @FXML
    private Label males12;

    @FXML
    private Label non_binary;

    @FXML
    private Label other;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void count(ActionEvent event) {
        invokeGenderCounter();

    }

    @FXML
    private void stop(ActionEvent event) {
        System.out.println("Stopped streaming");
    }

    public void invokeGenderCounter(){
        GenderTask counterTask = new GenderTask();
        counterTask.valueProperty().addListener((observable, oldValue, newValue) ->
                males.setText(String.valueOf(newValue)));
        Thread thread = new Thread(counterTask);
        thread.setDaemon(true);
        thread.start();
        System.out.println("Hello world!");
    }

   }
