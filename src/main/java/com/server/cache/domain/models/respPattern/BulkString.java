package com.server.cache.domain.models.respPattern;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BulkString extends RespType {

    private String message;

    @Override
    public String serialize() {
        return "$" + message.length() + "\\r\\n" + message + "\\r\\n";
    }


     public String setBulkStringContent(String data) {
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
