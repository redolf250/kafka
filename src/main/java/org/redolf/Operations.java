package org.redolf;

import lombok.val;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.kstream.Produced;
import org.apache.kafka.streams.kstream.Reducer;

import java.util.Arrays;
import java.util.Properties;

public class Operations {
    public static void main(String[] args) {
        Properties properties = new Properties();
        StreamsBuilder builder = new StreamsBuilder();
        KStream<Integer,Long> stream = builder.stream("A");

        Reducer<Long> reducer = Long::sum;
                stream.groupByKey()
                .reduce(reducer, Materialized.with(Serdes.Integer(),Serdes.Long()))
                .toStream()
                .to("B", Produced.with(Serdes.Integer(), Serdes.Long()));

                stream.mapValues(Object::toString);

        KStream<Integer, String>  stream1= builder.stream("C");
                stream1.mapValues(s -> s.toLowerCase());
    }
}
