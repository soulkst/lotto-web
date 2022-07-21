package dev.kirin.toy.lottoweb.core.scrap.definition.code;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum DataType {
    NUMBER("number"), DATE("date"), NUMBER_LIST("number-list")
    , NONE("none")
    ;

    private final String typeName;
}
