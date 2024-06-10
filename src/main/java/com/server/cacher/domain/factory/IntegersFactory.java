package com.server.cacher.domain.factory;

import com.server.cacher.domain.models.respPattern.Integers;
import com.server.cacher.domain.models.respPattern.RespType;

public class IntegersFactory implements RespTypeFactory{
    @Override
    public RespType createRespType(String data) {
        String substring = data.substring(1, data.length() - 4);
        return new Integers(substring);
    }
}
