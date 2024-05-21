package com.server.cache.shared.utils.factory;

import com.server.cache.domain.models.respPattern.BulkString;
import com.server.cache.domain.models.respPattern.RespType;

public class BulkStringFactory implements RespTypeFactory {
    @Override
    public RespType createRespType(String data) {
        BulkString bulkString = new BulkString();
        String result = bulkString.setBulkStringContent(data);
        bulkString.setMessage(result);
        return bulkString;
    }
}
