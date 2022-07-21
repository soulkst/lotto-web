package dev.kirin.toy.lottoweb.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static String toJson(Object value) {
        try {
            return OBJECT_MAPPER.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            throw new JsonException(e);
        }
    }

    public static <T> T toObject(String json, Class<T> targetClass) {
        try {
            return OBJECT_MAPPER.readValue(json, targetClass);
        } catch (JsonProcessingException e) {
            throw new JsonException(e);
        }
    }

    public static <T> T toObject(String json, TypeReference<T> type) {
        try {
            return OBJECT_MAPPER.readValue(json, type);
        } catch (JsonProcessingException e) {
            throw new JsonException(e);
        }
    }

    public static <T> T convert(Object src, Class<T> targetClass) {
        return toObject(toJson(src), targetClass);
    }

    public static <T> T convert(Object src, TypeReference<T> type) {
        return toObject(toJson(src), type);
    }

    public static class JsonException extends RuntimeException {
        public JsonException(Throwable cause) {
            super(cause);
        }
    }
}
