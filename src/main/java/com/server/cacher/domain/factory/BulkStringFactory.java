package com.server.cacher.domain.factory;

import com.server.cacher.domain.models.respPattern.BulkString;
import com.server.cacher.domain.models.respPattern.RespType;

public class BulkStringFactory implements RespTypeFactory {
    @Override
    public RespType createRespType(String data) {
        BulkString bulkString = new BulkString();
        String result = bulkString.getContentFromBulkStringRespPattern(data);
        bulkString.setMessage(result);
        return bulkString;
    }
}
