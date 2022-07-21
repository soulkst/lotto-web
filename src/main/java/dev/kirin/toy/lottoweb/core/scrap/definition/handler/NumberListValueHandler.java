package dev.kirin.toy.lottoweb.core.scrap.definition.handler;

import dev.kirin.toy.lottoweb.core.scrap.definition.DataDefinition;
import dev.kirin.toy.lottoweb.core.scrap.definition.code.DataType;
import dev.kirin.toy.lottoweb.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;
import org.springframework.util.NumberUtils;

import java.util.stream.Collectors;

@Component
@Slf4j
public class NumberListValueHandler implements ValueHandler {
    @Override
    public DataType getType() {
        return DataType.NUMBER_LIST;
    }

    @Override
    public <T> T getValue(Element element, DataDefinition definition, Object[] pathArgs) {
        return (T) element.select(getPath(definition, pathArgs)).stream()
                .map(value -> NumberUtils.parseNumber(StringUtils.numberOnly(value.text()), Integer.class))
                .collect(Collectors.toList());
    }
}
