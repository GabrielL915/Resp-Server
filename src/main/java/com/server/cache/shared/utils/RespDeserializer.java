package com.server.cache.shared.utils;

import com.server.cache.domain.models.Error;
import com.server.cache.domain.models.SimpleString;
import com.server.cache.shared.types.RespType;

public class RespDeserializer {
    public static RespType deserialize(String data) {

        String result = data.substring(1, data.length() - 2);

        if (data.startsWith("+")) {
            return new SimpleString(result);

        } else if (data.startsWith("-")) {
           return new Error(result);

        }
        return null;
    }
}
