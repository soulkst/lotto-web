package dev.kirin.toy.lottoweb.core.scrap.definition.handler;

import dev.kirin.toy.lottoweb.core.scrap.definition.DataDefinition;
import dev.kirin.toy.lottoweb.core.scrap.definition.code.DataType;
import dev.kirin.toy.lottoweb.util.StringUtils;
import org.jsoup.nodes.Element;

public interface ValueHandler {
    DataType getType();
    <T> T getValue(Element element, DataDefinition definition, Object[] pathArgs);

    default String getPath(DataDefinition definition, Object[] pathArgs) {
        if(pathArgs != null && pathArgs.length > 0) {
            return StringUtils.format(definition.getPath(), pathArgs);
        }
        return definition.getPath();
    }
}
