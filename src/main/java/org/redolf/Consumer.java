package org.redolf;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.redolf.model.Student;
import org.redolf.serializer.JsonDeserializer;

import javax.swing.*;
import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.stream.Stream;

import static org.redolf.config.Configuration.APPLICATION_ID;
import static org.redolf.config.Configuration.SERVER;

public class Consumer {

    public static void main(String[] args) {

        Properties props = new Properties();
        props.put(ConsumerConfig.CLIENT_ID_CONFIG,APPLICATION_ID);
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,SERVER);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "console-consumer-myapp");
//        props.put(JsonDeserializer.VALUE_CLASS_NAME_CONFIG, Student.class);

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Collections.singleton("customer_topic"));

        while(true){
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
            for (ConsumerRecord<String, String> record: records) {
                String j = Arrays.asList(record).toString().trim().toUpperCase();
                Stream.of(j)
                                .filter(value -> value.length() >= 5)
                                        .filter(value -> value.contains("r"))
                                                .peek(s -> System.out.println(s));

            }
        }
    }
}
