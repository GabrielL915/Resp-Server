package com.server.cacher.shared.types;

import com.server.cacher.shared.enums.RespDataEnum;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RespBulkString extends RespData {

    private final String value;

    @Override
    public RespDataEnum getType() {
        return RespDataEnum.BULK_STRING;
    }

    @Override
    public String getFormattedValue() {
        if (value == null) return "$-1\r\n";
        return "$" + value.length() + "\r\n" + value + "\r\n";    }

    @Override
    public String getRawValue() {
        return value;
    }
}
