package com.server.cacher.domain.models.respPattern;

import com.server.cacher.domain.service.RespTypeFactoryService;
import com.server.cacher.domain.factory.RespTypeFactory;

public abstract class RespType {

    public abstract String serialize();

    public static RespType deserialize(String data) {
        RespTypeFactory factory = new RespTypeFactoryService().getFactory(data.charAt(0));
        return factory.createRespType(data);
    }

}
