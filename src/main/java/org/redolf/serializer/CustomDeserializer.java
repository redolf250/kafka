package org.redolf.serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.IOException;
import java.util.Map;

public class CustomDeserializer<T> implements Deserializer<T> {
    ObjectMapper objectMapper = new ObjectMapper();
    public Class <T> type;

    public CustomDeserializer() {
    }

    public static final String VALUE_CLASS_NAME_CONFIG = "value.class.name";
    public static final String KEY_CLASS_NAME_CONFIG = "key.class.name";

    @Override
    public void configure(Map<String, ?> props, boolean isKey) {
        if (isKey)
            type=(Class<T>) props.get(KEY_CLASS_NAME_CONFIG);
        else
            type=(Class<T>) props.get(VALUE_CLASS_NAME_CONFIG);
    }

    @Override
    public T deserialize(String topic, byte[] bytes) {
        try {
            return objectMapper.readValue(bytes, type);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
