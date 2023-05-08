package org.redolf.javafx;

import javafx.concurrent.Task;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.redolf.config.Configuration;
import org.redolf.model.Customer;
import org.redolf.serializer.CustomDeserializer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class GenderTask extends Task<Integer> {
    @Override
    protected Integer call() throws Exception {
//        Properties properties = new Properties();
//        properties.put(ConsumerConfig.CLIENT_ID_CONFIG, Configuration.APPLICATION_ID);
//        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:29092");
//        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
//        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, CustomDeserializer.class);
//        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "consumer-myapp");
//        properties.put(CustomDeserializer.VALUE_CLASS_NAME_CONFIG, Customer.class);
//
//        KafkaConsumer<String, Customer> kafkaConsumer = new KafkaConsumer<>(properties);
//        kafkaConsumer.subscribe(Collections.singleton("learning"));
        int sum=0;
//        while(true) {
//            ConsumerRecords<String, Customer> consumerRecords = kafkaConsumer.poll(Duration.ofMillis(100));
//            for (ConsumerRecord<String, Customer> record : consumerRecords) {
//
//                Customer customer = Customer.builder()
//                        .firstName(record.value().getFirstName())
//                        .lastName(record.value().getLastName())
//                        .email(record.value().getEmail())
//                        .phone(record.value().getPhone())
//                        .gender(record.value().getGender())
//                        .country(record.value().getCountry())
//                        .dob(record.value().getDob())
//                        .build();

                    sum = sum + 1;

                return sum;
//            }
//
//        }

    }
}
