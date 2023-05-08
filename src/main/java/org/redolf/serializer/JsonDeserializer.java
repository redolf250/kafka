package org.redolf.serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

public class JsonDeserializer<T> implements Deserializer<T> {

    public static final String VALUE_CLASS_NAME_CONFIG = "value.class.name";
    public static final String KEY_CLASS_NAME_CONFIG = "key.class.name";

    ObjectMapper objectMapper = new ObjectMapper();

    private    Class<T> type;

    public JsonDeserializer() {}

    @Override
    public void configure(Map<String, ?> props, boolean isKey) {
      if (isKey)
          type=(Class<T>) props.get(KEY_CLASS_NAME_CONFIG);
      else
          type=(Class<T>) props.get(VALUE_CLASS_NAME_CONFIG);

    }

    @Override
    public T deserialize(String topic, byte[] bytes) {
        return deserialize(bytes);
    }

    @SneakyThrows
    private T deserialize(byte[] data){
        return objectMapper.readValue(data, type);
    }

    @Override
    public void close() {
        Deserializer.super.close();
    }
}
