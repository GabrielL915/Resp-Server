package com.server.cacher.shared.types;

import com.server.cacher.shared.enuns.RespDataType;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RespSimpleString extends RespData {

    private final String value;

    @Override
    public RespDataType getType() {
        return RespDataType.SIMPLE_STRING;
    }

    @Override
    public String getFormattedValue() {
        return "+" + value + "\r\n";
    }

    @Override
    public String getRawValue() {
        return value;
    }
}
