package com.server.cache.domain.factory;

import com.server.cache.domain.models.respPattern.Arrays;
import com.server.cache.domain.models.respPattern.RespType;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ArrayFactory implements RespTypeFactory {
    @Override
    public RespType createRespType(String data) {
        Arrays respArray = new Arrays();
        List<RespType> result = respArray.getContentsFromRespArrayPattern(data);
        respArray.setMessage(result);
        return respArray;
    }
}
