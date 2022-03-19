package org.sehkah.ddonbruteforcergui.model.serialization;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class SerializerImpl implements Serializer {
    private final ObjectMapper mapper;

    public SerializerImpl() {
        mapper = new ObjectMapper(new JsonFactory());
        mapper.findAndRegisterModules();
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    }

    public <T> T deserialize(String serialized, Class<T> cls) throws JsonProcessingException {
        return mapper.readValue(serialized, cls);
    }

    public <T> String serialize(T deserialized) throws JsonProcessingException {
        return mapper.writeValueAsString(deserialized);
    }
}
