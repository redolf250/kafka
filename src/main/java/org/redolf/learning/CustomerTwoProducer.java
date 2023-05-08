package org.redolf.learning;

import lombok.SneakyThrows;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.redolf.config.Configuration;
import org.redolf.model.CustomerOne;
import org.redolf.model.CustomerTwo;
import org.redolf.serializer.CustomSerializer;

import java.io.*;
import java.util.Arrays;
import java.util.Objects;
import java.util.Properties;

public class CustomerTwoProducer {
    @SneakyThrows
    public static void main(String[] args) throws IOException, InterruptedException {
//        Properties properties = new Properties();
//        properties.put(ProducerConfig.CLIENT_ID_CONFIG, Configuration.APPLICATION_ID);
//        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:29092");
//        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
//        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, CustomSerializer.class);
//
//        KafkaProducer<String, CustomerTwo> kafkaProducer = new KafkaProducer<>(properties);

        File file = new File("./src/main/resources/customers2.csv");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        
        
        
        String line;
        while(((line=reader.readLine())!= null)){
            String [] details = line.split(",");
            CustomerTwo customerTwo = CustomerTwo.builder()
                    .id(details[0])
                    .gender(details[1])
                    .country(details[3])
                    .phone(details[2])
                    .dob(details[4])
                    .build();
            System.out.println("customerTwo = " + customerTwo);
            Thread.sleep(1000);
//            kafkaProducer.send(new ProducerRecord<>("CustomerTwo",details[1],customerTwo));
        }
    }
}
