package com.server.cache.domain.models;

public abstract class Resp {

    public abstract String serialize();

    public static Resp deserialize(String data) {

        String result = data.substring(1, data.length() - 2);

        if (data.startsWith("+")) {
            return new SimpleString(result);

        } else if (data.startsWith("-")) {
            return new Error(result);

        }
        return null;
    }
}
