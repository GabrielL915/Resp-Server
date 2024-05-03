package com.server.cache.domain.service;

import com.server.cache.domain.models.SimpleString;
import com.server.cache.shared.types.RespType;
import org.springframework.stereotype.Service;

@Service
public class CommandService {

    public RespType processCommand(String command) {
        return new SimpleString("OK");
    }
}
