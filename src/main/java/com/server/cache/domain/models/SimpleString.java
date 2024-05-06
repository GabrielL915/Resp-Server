package com.server.cache.domain.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SimpleString extends Resp {

    private String message;

    @Override
    public String serialize() {
        return "+" + message + "\\r\\n";
    }

}
