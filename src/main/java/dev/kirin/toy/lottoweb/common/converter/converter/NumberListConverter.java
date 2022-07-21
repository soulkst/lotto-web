package dev.kirin.toy.lottoweb.common.converter.converter;

import com.fasterxml.jackson.core.type.TypeReference;
import dev.kirin.toy.lottoweb.common.converter.JsonConverter;
import dev.kirin.toy.lottoweb.util.JsonUtils;

import javax.persistence.Converter;
import java.util.List;

@Converter(autoApply = true)
public class NumberListConverter extends JsonConverter<List<Integer>> {
    @Override
    public List<Integer> convertToEntityAttribute(String dbData) {
        return JsonUtils.toObject(dbData, new TypeReference<List<Integer>>() {});
    }
}
