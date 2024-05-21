package com.server.cache.domain.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
}

