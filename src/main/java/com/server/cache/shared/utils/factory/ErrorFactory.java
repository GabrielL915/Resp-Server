package com.server.cache.shared.utils.factory;

import com.server.cache.domain.models.respPattern.Error;
import com.server.cache.domain.models.respPattern.RespType;

public class ErrorFactory implements RespTypeFactory{
    @Override
    public RespType createRespType(String data) {
        String substring = data.substring(1, data.length() - 4);
        return new Error(substring);
    }
}
