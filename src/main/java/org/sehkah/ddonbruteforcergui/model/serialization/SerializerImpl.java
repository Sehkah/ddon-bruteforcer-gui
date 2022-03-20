package org.sehkah.ddonbruteforcergui.model.serialization;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;

public class SerializerImpl implements Serializer {
    private final JsonMapper mapper;

    public SerializerImpl() {
        JsonMapper.Builder builder = JsonMapper.builder();
        builder.findAndAddModules();
        builder.enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES);
        builder.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        mapper = builder.build();
    }

    public <T> T deserialize(String serialized, Class<T> cls) throws JsonProcessingException {
        return mapper.readValue(serialized, cls);
    }

    public <T> String serialize(T deserialized) throws JsonProcessingException {
        return mapper.writeValueAsString(deserialized);
    }
}
