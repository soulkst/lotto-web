package dev.kirin.toy.lottoweb.core.prediction.entity.converter;

import com.fasterxml.jackson.core.type.TypeReference;
import dev.kirin.toy.lottoweb.common.converter.JsonConverter;
import dev.kirin.toy.lottoweb.core.prediction.entity.PredictionItem;
import dev.kirin.toy.lottoweb.util.JsonUtils;

import java.util.List;

public class PredictionItemConverter extends JsonConverter<List<PredictionItem>> {
    @Override
    public List<PredictionItem> convertToEntityAttribute(String dbData) {
        return JsonUtils.toObject(dbData, new TypeReference<List<PredictionItem>>() {});
    }
}
