package org.sehkah.ddonbruteforcergui.model.serialization;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

public class SerializerImpl implements Serializer {
    private static final JsonMapper jsonMapper;
    private static final YAMLMapper yamlMapper;

    static {
        JsonMapper.Builder jsonBuilder = JsonMapper.builder();
        jsonBuilder.findAndAddModules();
        jsonBuilder.enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES);
        jsonBuilder.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        jsonBuilder.serializationInclusion(JsonInclude.Include.NON_NULL);
        jsonMapper = jsonBuilder.build();

        YAMLMapper.Builder yamlBuilder = YAMLMapper.builder();
        yamlBuilder.findAndAddModules();
        yamlBuilder.enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES);
        yamlBuilder.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        yamlBuilder.serializationInclusion(JsonInclude.Include.NON_NULL);
        yamlMapper = yamlBuilder.build();
    }

    public SerializerImpl() {

    }

    public <T> T deserializeJson(String serialized, Class<T> cls) throws JsonProcessingException {
        return jsonMapper.readValue(serialized, cls);
    }

    public <T> String serializeJson(T deserialized) throws JsonProcessingException {
        return jsonMapper.writeValueAsString(deserialized);
    }

    public <T> T deserializeYaml(String serialized, Class<T> cls) throws JsonProcessingException {
        return yamlMapper.readValue(serialized, cls);
    }

    public <T> String serializeYaml(T deserialized) throws JsonProcessingException {
        return yamlMapper.writeValueAsString(deserialized);
    }
}
