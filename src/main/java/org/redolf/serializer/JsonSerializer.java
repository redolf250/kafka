package org.redolf.serializer;

import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

public class JsonSerializer <T> implements Serializer<T> {
    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        Serializer.super.configure(configs, isKey);
    }

    @Override
    public byte[] serialize(String s, T t) {
        return new byte[0];
    }
}
