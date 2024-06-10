package com.server.cacher.domain.models.respPattern;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Error extends RespType {

    private String errorMessage;

    @Override
    public String serialize() {
        return "-" + errorMessage + "\\r\\n";
    }
}
