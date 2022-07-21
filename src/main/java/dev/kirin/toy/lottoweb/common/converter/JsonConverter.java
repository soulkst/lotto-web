package dev.kirin.toy.lottoweb.common.converter;

import com.fasterxml.jackson.core.type.TypeReference;
import dev.kirin.toy.lottoweb.util.JsonUtils;

import javax.persistence.AttributeConverter;

public abstract class JsonConverter<T> implements AttributeConverter<T, String> {
    @Override
    public final String convertToDatabaseColumn(T attribute) {
        return JsonUtils.toJson(attribute);
    }

    @Override
    public T convertToEntityAttribute(String dbData) {
        return JsonUtils.toObject(dbData, new TypeReference<T>() {});
    }
}
