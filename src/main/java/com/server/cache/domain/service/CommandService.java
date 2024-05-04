package com.server.cache.domain.service;

import com.server.cache.domain.models.Resp;
import com.server.cache.domain.models.SimpleString;
import org.springframework.stereotype.Service;

@Service
public class CommandService {

    public Resp processCommand(String command) {
        return Resp.deserialize(command);
    }
}
