package com.server.cache.domain.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Error extends Resp {

    private String errorMessage;

    @Override
    public String serialize() {
        return "-" + errorMessage + "\\r\\n";
    }
}
