package com.server.cache.domain.models;

import java.util.ArrayList;
import java.util.List;

public abstract class RespType {

    public abstract String serialize();

    public static RespType deserialize(String data) {
        String substring = data.substring(1, data.length() - 4);

        if (data.startsWith("+")) {
            return new SimpleString(substring);

        } else if (data.startsWith("-")) {
            return new Error(substring);

        } else if (data.startsWith(":")) {
            return new Integers(substring);

        } else if (data.startsWith("$")) {
            String content = getBulkStringContent(data);
            return new BulkString(content);


        } else if (data.startsWith("*")) {
            return new Arrays(getRespArraysContents(data));

        } else {
            throw new IllegalArgumentException("Unsupported RESP type");
        }
    }

    private static List<RespType> getRespArraysContents(String data) {
        int startIndex = 1;
        int endIndex = data.indexOf("\\r\\n");

        if (endIndex == -1) {
            throw new IllegalArgumentException("Invalid RESP format, missing \\r\\n");
        }

        String lengthStr = data.substring(startIndex, endIndex);
        int elementCount = Integer.parseInt(lengthStr);

        List<RespType> elements = new ArrayList<>();
        int currentStart = endIndex + 4;

        while (elementCount-- > 0) {
            endIndex = data.indexOf("\\r\\n", currentStart);
            if (endIndex == -1) {
                throw new IllegalArgumentException("Invalid RESP format, missing \\r\\n after element count");
            }

            int bulkLength = Integer.parseInt(data.substring(currentStart + 1, endIndex));
            currentStart = endIndex + 4;
            String bulkContent = data.substring(currentStart, currentStart + bulkLength);

            elements.add(new BulkString(bulkContent));
            currentStart += bulkLength + 4;
        }
        return elements;
    }

    private static String getBulkStringContent(String data) {
        int startIndex = 1;
        int endIndex = data.indexOf("\\r\\n");

        if (endIndex == -1) {
            throw new IllegalArgumentException("Invalid RESP format, missing '\\r\\n'");
        }
        String lengthStr = data.substring(startIndex, endIndex);

        int startOfContent = endIndex + 4;
        int endOfContent = startOfContent + Integer.parseInt(lengthStr);

        return data.substring(startOfContent, endOfContent);
    }
}
