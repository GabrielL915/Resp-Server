package com.server.cache.domain.service;

import com.server.cache.domain.models.respPattern.RespType;
import org.springframework.stereotype.Service;

@Service
public class CommandService {

    public String processCommand(String command) {
        try {
            if(command.equalsIgnoreCase("$4")) {
                return "PONG\\r\\n";
            }
            RespType deserializeCommand = RespType.deserialize(command);
            return deserializeCommand.serialize();
        } catch (IllegalArgumentException e) {
            return "-Error " + e.getMessage() + "  comando: " + command + "\\r\\n";
        }
    }
}
