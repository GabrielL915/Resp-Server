package com.server.cache.domain.models.respPattern;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Arrays extends RespType {

    private List<RespType> message;

    @Override
    public String serialize() {
        StringBuilder sb = new StringBuilder("*");
        for (RespType element : message) {
            sb.append(element.serialize());
        }
        return sb.toString();
    }

    public String getConcatenatedMessages() {
        StringBuilder sb = new StringBuilder();
        for (RespType element : message) {
            if (element instanceof BulkString) {
                sb.append(((BulkString) element).getMessage()).append(" ");
            }
        }
        return sb.toString().trim();
    }

    public List<RespType> getContentsFromRespArrayPattern(String data) {
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
}

