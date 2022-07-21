package dev.kirin.toy.lottoweb.core.scrap.definition.handler;

import dev.kirin.toy.lottoweb.core.scrap.definition.DataDefinition;
import dev.kirin.toy.lottoweb.core.scrap.definition.DateDefinition;
import dev.kirin.toy.lottoweb.core.scrap.definition.code.DataType;
import dev.kirin.toy.lottoweb.util.StringUtils;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class DateValueHandler implements ValueHandler {
    @Override
    public DataType getType() {
        return DataType.DATE;
    }

    @Override
    public <T> T getValue(Element element, DataDefinition definition, Object[] pathArgs) {
        DateDefinition dateDefinition = (DateDefinition) definition;
        String dateString = StringUtils.numberOnly(element.select(getPath(definition, pathArgs)).text());
        return (T) LocalDate.parse(dateString, DateTimeFormatter.ofPattern(dateDefinition.getFormat()));
    }
}
