package com.server.cacher.shared.types;

import com.server.cacher.shared.enums.RespDataEnum;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RespArray extends RespData {

    private final RespData[] values;

    @Override
    public RespDataEnum getType() {
        return RespDataEnum.ARRAYS;
    }

    @Override
    public String getFormattedValue() {
        StringBuilder sb = new StringBuilder();
        sb.append("*").append(values.length).append("\r\n");

        for (RespData value : values) {
            sb.append(value.getFormattedValue());
        }

        return sb.toString();
    }

    @Override
    public RespData[] getRawValue() {
        return values;
    }
}
