package org.sehkah.ddonbruteforcergui.model.serialization;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface Serializer {
    <T> T deserialize(String serialized, Class<T> cls) throws JsonProcessingException;

    <T> String serialize(T deserialized) throws JsonProcessingException;
}
