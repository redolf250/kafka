package org.redolf.runner;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.kstream.Produced;

import java.util.Arrays;
import java.util.Properties;

public class Driver {
    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put(StreamsConfig.APPLICATION_ID_CONFIG,"hello");
        properties.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:29092");
        properties.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.Integer().getClass());
        properties.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        properties.put(StreamsConfig.CACHE_MAX_BYTES_BUFFERING_CONFIG,"0");
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"earliest");

        StreamsBuilder builder = new StreamsBuilder();
        builder.<Integer,String>stream("inventory")
                .flatMapValues((K, V)-> Arrays.asList(V.toLowerCase().split(" ")))
                .filter((integer, s) -> s.contains("r"))
                .groupBy((K,V) -> V)
                .count(Materialized.with(Serdes.String(), Serdes.Long()))
                .toStream()
                .to("seller", Produced.with(Serdes.String(), Serdes.Long()));

        Topology topology = builder.build();
        KafkaStreams kafkaStreams = new KafkaStreams(topology,properties);
        kafkaStreams.start();
        Runtime.getRuntime().addShutdownHook(new Thread(kafkaStreams::close));
    }
}
