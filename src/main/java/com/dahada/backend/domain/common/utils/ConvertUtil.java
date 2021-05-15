package com.dahada.backend.domain.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public final class ConvertUtil {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    @SuppressWarnings("unchecked")
    public static Map<String, Object> toMap(String json) {
        try {
            return MAPPER.readValue(json, Map.class);
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    public static <T> T toObject(Map<String, Object> map, Class<T> type) {
        return MAPPER.convertValue(map, type);
    }

    public static <T> T toObject(String json, Class<T> type) {
        try {
            final Object object = MAPPER.readValue(json, type.getClass());
            return type.cast(object);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("Unable to convert to object: ");
        }
    }

    public static String stringify(Object data) {
        try {
            return MAPPER.writeValueAsString(data);
        } catch (JsonProcessingException e) {
            return null;
        }
    }
}
