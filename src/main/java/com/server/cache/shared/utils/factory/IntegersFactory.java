package com.server.cache.shared.utils.factory;

import com.server.cache.domain.models.respPattern.Integers;
import com.server.cache.domain.models.respPattern.RespType;

public class IntegersFactory implements RespTypeFactory{
    @Override
    public RespType createRespType(String data) {
        String substring = data.substring(1, data.length() - 4);
        return new Integers(substring);
    }
}
