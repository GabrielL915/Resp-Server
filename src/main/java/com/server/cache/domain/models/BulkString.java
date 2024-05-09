package com.server.cache.domain.models;

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
}
