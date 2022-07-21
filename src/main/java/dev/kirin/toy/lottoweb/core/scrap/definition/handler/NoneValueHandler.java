package dev.kirin.toy.lottoweb.core.scrap.definition.handler;

import dev.kirin.toy.lottoweb.core.scrap.definition.DataDefinition;
import dev.kirin.toy.lottoweb.core.scrap.definition.code.DataType;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

@Component
public class NoneValueHandler implements ValueHandler {
    @Override
    public DataType getType() {
        return DataType.NONE;
    }

    @Override
    public <T> T getValue(Element element, DataDefinition definition, Object[] pathArgs) {
        return (T) element.select(getPath(definition, pathArgs));
    }
}
