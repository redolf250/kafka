package org.redolf.learning;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.redolf.config.Configuration;
import org.redolf.model.Customer;
import org.redolf.serializer.CustomSerializer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class MyProducer {

    public static void main(String[] args) throws InterruptedException, IOException {
        Properties properties = new Properties();
        properties.put(ProducerConfig.CLIENT_ID_CONFIG, Configuration.APPLICATION_ID);
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:29092");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, CustomSerializer.class);

        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<>(properties);

            File file = new File("./src/main/resources/customers.csv");

            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while((line=reader.readLine()) != null){
                String [] details = line.split(",");
                Customer customer = Customer.builder()
                        .id(details[0])
                        .firstName(details[1])
                        .lastName(details[2])
                        .email(details[3])
                        .gender(details[4])
                        .phone(details[5])
                        .country(details[6])
                        .dob(details[7])
                        .build();
                System.out.println("Customer details: "+customer.getFirstName());
                Thread.sleep(1000);
                kafkaProducer.send(new ProducerRecord<>("customer_topic",details[0],details[1]));
            }
        }
    }

