package com.server.cache.shared.enuns;

import lombok.ToString;

@ToString
public enum RespEnum {
    SIMPLE_STRING("+"),
    BULK_STRING("$"),
    INTEGERS(":"),
    ERROR("-"),
    ARRAYS("*");

    private final String value;

    RespEnum(String value) {
        this.value = value;
    }

}
