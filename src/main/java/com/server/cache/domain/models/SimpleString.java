package com.server.cache.domain.models;

import com.server.cache.shared.types.RespType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SimpleString extends RespType {

    private String message;

    @Override
    public String serialize() {
        return "+" + message + "\r\n";
    }
}
