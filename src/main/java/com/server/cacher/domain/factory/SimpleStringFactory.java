package com.server.cacher.domain.factory;

import com.server.cacher.domain.models.respPattern.RespType;
import com.server.cacher.domain.models.respPattern.SimpleString;

public class SimpleStringFactory implements RespTypeFactory{
    @Override
    public RespType createRespType(String data) {
        String substring = data.substring(1, data.length() - 4);
        return new SimpleString(substring);
    }
}
