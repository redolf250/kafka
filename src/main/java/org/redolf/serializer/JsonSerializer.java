package org.redolf.serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

public class JsonSerializer <T> implements Serializer<T> {
    ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        Serializer.super.configure(configs, isKey);
    }

    @Override
    public byte[] serialize(String topic, T data) {
        return serialize(data);
    }

    @SneakyThrows
    private byte[] serialize(T data) {
        return objectMapper.writeValueAsBytes(data);
    }

    @Override
    public void close() {
        Serializer.super.close();
    }
}
