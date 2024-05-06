package com.server.cache.domain.models;

public abstract class RespType {

    public abstract String serialize();

    public static RespType deserialize(String data) {

        String result = data.substring(1, data.length() - 4);

        if (data.startsWith("+")) {
            return new SimpleString(result);

        } else if (data.startsWith("-")) {
            return new Error(result);

        } else if (data.startsWith("$")) {
            return new BulkString(result);

        } else if (data.startsWith("*")) {
            return new Arrays(result);
        }

        throw new IllegalArgumentException("Unsupported RESP type");
    }
}
