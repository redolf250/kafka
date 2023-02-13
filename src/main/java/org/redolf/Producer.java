package org.redolf;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.KStream;

import java.util.Properties;

import static org.redolf.config.Configuration.*;

public class KafkaProducer {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put(ProducerConfig.CLIENT_ID_CONFIG,APPLICATION_ID);
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,SERVER);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        StreamsBuilder builder = new StreamsBuilder();
        KStream<Integer,String> stream = builder.stream(TOPIC);
        
        Topology topology = builder.build();
        KafkaStreams kafkaStreams = new KafkaStreams(topology,props);
        kafkaStreams.start();
        Runtime.getRuntime().addShutdownHook(new Thread(kafkaStreams::close));
    }
}
