package com.server.cache.domain.service;

import com.server.cache.domain.models.RespType;
import org.springframework.stereotype.Service;

@Service
public class CommandService {

    public String processCommand(String command) {
       RespType deserializeCommand = RespType.deserialize(command);
       return deserializeCommand.serialize();
    }
}
