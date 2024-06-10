package com.server.cacher.domain.service;

import com.server.cacher.domain.factory.ArrayFactory;
import com.server.cacher.domain.factory.BulkStringFactory;
import com.server.cacher.domain.factory.ErrorFactory;
import com.server.cacher.domain.factory.IntegersFactory;
import com.server.cacher.domain.factory.RespTypeFactory;
import com.server.cacher.domain.factory.SimpleStringFactory;

public class RespTypeFactoryService {

    public RespTypeFactory getFactory(char type) {
        return switch (type) {
            case '+' -> new SimpleStringFactory();
            case '-' -> new ErrorFactory();
            case ':' -> new IntegersFactory();
            case '$' -> new BulkStringFactory();
            case '*' -> new ArrayFactory();
            default -> throw new IllegalArgumentException("Unsupported RESP type");
        };
    }
}
