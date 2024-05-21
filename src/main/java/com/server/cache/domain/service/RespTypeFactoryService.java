package com.server.cache.domain.service;

import com.server.cache.domain.factory.ArrayFactory;
import com.server.cache.domain.factory.BulkStringFactory;
import com.server.cache.domain.factory.ErrorFactory;
import com.server.cache.domain.factory.IntegersFactory;
import com.server.cache.domain.factory.RespTypeFactory;
import com.server.cache.domain.factory.SimpleStringFactory;

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
