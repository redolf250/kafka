package org.redolf;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.*;
import org.redolf.config.Configuration;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

public class StreamJoiner {
    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put(StreamsConfig.APPLICATION_ID_CONFIG, "customer");
        properties.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:29092");
        properties.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        properties.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"earliest");
        properties.put(StreamsConfig.CACHE_MAX_BYTES_BUFFERING_CONFIG,"0");


        StreamsBuilder builder = new StreamsBuilder();
        KStream<String, String> customerOne = builder.stream("CustomerOne", Consumed.with(Serdes.String(),Serdes.String()))
                .peek((s, s2) -> System.out.println(s2));

        KStream<String, String> customerTwo = builder.stream("CustomerTwo", Consumed.with(Serdes.String(),Serdes.String()))
                .peek((s, s2) -> System.out.println(s2));


        KStream<String, String> streamJoin = customerOne.
                join(customerTwo,
                        (value1, value2) -> value1 + value2,
                        JoinWindows.of(Duration.ofMillis(1000)),
                        StreamJoined.with(Serdes.String(),Serdes.String(),Serdes.String()));
        streamJoin.peek((key, value) -> System.out.println(value));


//        builder.<String,String>stream("customersOne")
//                .flatMapValues((readOnlyKey, value)-> Arrays.asList(value.toLowerCase().split(" ")))
//                .groupBy((key, value) -> value)
//                .count(Materialized.with(Serdes.String(), Serdes.Long()))
//                .toStream()
//                .to("word-count", Produced.with(Serdes.String(), Serdes.Long()));

        KafkaStreams kafkaStreams = new KafkaStreams(builder.build(),properties);
        kafkaStreams.start();
        Runtime.getRuntime().addShutdownHook(new Thread(kafkaStreams::close));
    }
}
