package com.server.cacher.domain.factory;

import com.server.cacher.domain.models.respPattern.Error;
import com.server.cacher.domain.models.respPattern.RespType;

public class ErrorFactory implements RespTypeFactory{
    @Override
    public RespType createRespType(String data) {
        String substring = data.substring(1, data.length() - 4);
        return new Error(substring);
    }
}
