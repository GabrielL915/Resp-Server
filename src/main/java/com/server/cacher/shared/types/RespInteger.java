package com.server.cacher.shared.types;

import com.server.cacher.shared.enums.RespDataEnum;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RespInteger extends RespData {

    private final Long value;

    @Override
    public RespDataEnum getType() {
        return RespDataEnum.INTEGERS;
    }

    @Override
    public String getFormattedValue() {
        return ":" + value + "\r\n";
    }

    @Override
    public Long getRawValue() {
        return value;
    }
}
