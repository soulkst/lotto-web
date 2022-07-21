package dev.kirin.toy.lottoweb.core.scrap.definition;

import dev.kirin.toy.lottoweb.core.scrap.definition.code.DataType;
import dev.kirin.toy.lottoweb.core.scrap.definition.handler.ValueHandler;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@Slf4j
public class DataValueHandler {
    private Map<DataType, ValueHandler> handlerMap;

    public DataValueHandler(List<ValueHandler> handlers) {
        handlerMap = handlers.stream()
                .collect(Collectors.toMap(ValueHandler::getType, handler -> handler));
    }

    public <T> T getValue(Element element, DataDefinition definition, Object... pathArgs) {
        return handlerMap.get(definition.getType()).getValue(element, definition, pathArgs);
    }
}
