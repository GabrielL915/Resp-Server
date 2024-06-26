package com.server.cacher.domain.models.respPattern;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Integers extends RespType {

    private String message;

    @Override
    public String serialize() {
        return ":" + message + "\\r\\n";
    }
}
