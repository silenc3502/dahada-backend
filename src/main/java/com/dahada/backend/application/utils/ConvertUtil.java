package com.dahada.backend.application.utils;

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
}
