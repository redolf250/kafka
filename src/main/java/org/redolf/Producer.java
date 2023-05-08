package org.redolf;

import lombok.var;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.redolf.config.JsonConvertor;
import org.redolf.model.Student;
import org.redolf.serializer.JsonSerializer;

import java.util.Properties;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

import static org.redolf.config.Configuration.*;

public class Producer {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        var studentSerde = new JsonConvertor<>(Student.class);
        Properties props = new Properties();
        props.put(ProducerConfig.CLIENT_ID_CONFIG,APPLICATION_ID);
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,SERVER);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        Student student=new Student(UUID.randomUUID().toString(),"Asamaning","Redolf_");
        KafkaProducer<Integer, Student> producer = new KafkaProducer<>(props);
        System.out.println("Starting sending messages.......");
        for (int i = 0; i < EVENT; i++) {
            producer.send(new ProducerRecord<>(TOPIC,i,student));
        }
        System.out.println("Finished sending messages.......");
        producer.close();

//        StreamsBuilder builder = new StreamsBuilder();
//        KStream<Integer,String> stream = builder.stream(TOPIC);
//
//        Topology topology = builder.build();
//        KafkaStreams kafkaStreams = new KafkaStreams(topology,props);
//        kafkaStreams.start();
//        Runtime.getRuntime().addShutdownHook(new Thread(kafkaStreams::close));
    }
}
