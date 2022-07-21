package dev.kirin.toy.lottoweb.core.scrap.definition.handler;

import dev.kirin.toy.lottoweb.core.scrap.definition.DataDefinition;
import dev.kirin.toy.lottoweb.core.scrap.definition.code.DataType;
import dev.kirin.toy.lottoweb.util.StringUtils;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;
import org.springframework.util.NumberUtils;

@Component
public class NumberValueHandler implements ValueHandler {
    @Override
    public DataType getType() {
        return DataType.NUMBER;
    }

    @Override
    public <T> T getValue(Element element, DataDefinition definition, Object[] pathArgs) {
        String numberOnly = StringUtils.numberOnly(element.select(getPath(definition, pathArgs)).text());
        return (T) NumberUtils.parseNumber(numberOnly, Integer.class);
    }
}
