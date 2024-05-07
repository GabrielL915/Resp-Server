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
            int endOfLengthIndex = data.indexOf('\r');
            int count = Integer.parseInt(data.substring(1, endOfLengthIndex));

            int startPos = endOfLengthIndex + 2;

            List<RespType> elements = new ArrayList<>();

            for (int i = 0; i < count; i++) {
                int endOfLine = data.indexOf('\r', startPos);

                String elementData = data.substring(startPos, endOfLine);

                elements.add(deserialize(elementData));

                startPos = endOfLine + 2;

            }
            return new Arrays(elements.toString());

        } else {
            throw new IllegalArgumentException("Unsupported RESP type");
        }
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
