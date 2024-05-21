package com.server.cache.domain.service;

import com.server.cache.shared.utils.factory.ArrayFactory;
import com.server.cache.shared.utils.factory.BulkStringFactory;
import com.server.cache.shared.utils.factory.ErrorFactory;
import com.server.cache.shared.utils.factory.IntegersFactory;
import com.server.cache.shared.utils.factory.RespTypeFactory;
import com.server.cache.shared.utils.factory.SimpleStringFactory;

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
