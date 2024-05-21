package com.server.cache.shared.utils.factory;

import com.server.cache.domain.models.respPattern.RespType;

public interface RespTypeFactory {
    RespType createRespType(String data);
}
