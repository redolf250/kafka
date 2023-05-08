package org.redolf.extractor;

import lombok.var;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.streams.processor.TimestampExtractor;
import org.redolf.model.Student;

public class CustomExtractor implements TimestampExtractor {

    @Override
    public long extract(ConsumerRecord<Object, Object> consumerRecord, long l) {
        var student  = (Student) consumerRecord.value();
        return 0;//Optional.ofNullable();
    }
}
