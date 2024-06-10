package com.server.cacher.domain.factory;

import com.server.cacher.domain.models.respPattern.RespType;

public interface RespTypeFactory {
    RespType createRespType(String data);
}
