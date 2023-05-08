package org.redolf.learning;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import okhttp3.*;
import okio.BufferedSink;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.redolf.config.Configuration;
import org.redolf.javafx.Controller;
import org.redolf.model.Customer;
import org.redolf.model.Product;
import org.redolf.serializer.CustomDeserializer;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
import java.util.Properties;

@SuppressWarnings("ALL")
public class MyConsumer {

    public int show(){
       return 0;
    }

    public static void main(String[] args) throws IOException {
        Properties properties = new Properties();
        properties.put(ConsumerConfig.CLIENT_ID_CONFIG, Configuration.APPLICATION_ID);
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:29092");
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, CustomDeserializer.class);
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "consumer-myapp");
        properties.put(CustomDeserializer.VALUE_CLASS_NAME_CONFIG, Customer.class);

        KafkaConsumer<String, Customer> kafkaConsumer = new KafkaConsumer<>(properties);
        kafkaConsumer.subscribe(Collections.singleton("learning"));
        int sum=0;
        while(true){
            ConsumerRecords<String, Customer> consumerRecords = kafkaConsumer.poll(Duration.ofMillis(100));
            for (ConsumerRecord<String,Customer> record : consumerRecords) {

                Customer customer = Customer.builder()
                        .firstName(record.value().getFirstName())
                        .lastName(record.value().getLastName())
                        .email(record.value().getEmail())
                        .phone(record.value().getPhone())
                        .gender(record.value().getGender())
                        .country(record.value().getCountry())
                        .dob(record.value().getDob())
                        .build();
                if (customer.getGender().equals("Male")){
                    sum = sum+1;
                }
                System.out.println("Total = " + sum);
//                ObjectMapper objectMapper = new ObjectMapper();
//
//                String b = objectMapper.writeValueAsString(customer);
//                RequestBody body = RequestBody.create(MediaType.parse("application/json"),b);
//                OkHttpClient httpClient = new OkHttpClient();
//
//                Request request = new  Request.Builder()
//                        .url("http://localhost:8081/api/save")
//                        .post(body)
//                        .build();
//
//                try {
//                    Response response =httpClient.newCall(request).execute();
//                    System.out.println(request.body());
//                    response.close();
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
            }
        }

    }
}
