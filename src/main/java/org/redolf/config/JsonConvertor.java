package org.redolf.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;

public class JsonConvertor<T> implements Serde<T> {

    ObjectMapper objectMapper = new ObjectMapper();

    public   Class<T> type;

    public JsonConvertor(Class<T> type) {
        this.type=type;
    }

    @Override
    public Serializer<T> serializer() {
        return (topic, data) -> serialize(data);
    }
    @SneakyThrows
    private byte[] serialize(T data) {
        return objectMapper.writeValueAsBytes(data);
    }

    @Override
    public Deserializer<T> deserializer() {
        return (topic, data) -> deserialize(data);
    }

    @SneakyThrows
    private T deserialize(byte[] data){
        return objectMapper.readValue(data, type);
    }
}
