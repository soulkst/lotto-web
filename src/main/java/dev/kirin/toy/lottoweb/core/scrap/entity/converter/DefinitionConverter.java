package dev.kirin.toy.lottoweb.core.scrap.entity.converter;


import dev.kirin.toy.lottoweb.common.converter.JsonConverter;
import dev.kirin.toy.lottoweb.core.scrap.entity.type.ScrapDefinition;
import dev.kirin.toy.lottoweb.util.JsonUtils;

public class DefinitionConverter extends JsonConverter<ScrapDefinition> {
    @Override
    public ScrapDefinition convertToEntityAttribute(String dbData) {
        return JsonUtils.toObject(dbData, ScrapDefinition.class);
    }
}
