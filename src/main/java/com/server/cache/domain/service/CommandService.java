package com.server.cache.domain.service;

import com.server.cache.domain.models.respPattern.RespType;
import org.springframework.stereotype.Service;

@Service
public class CommandService {

    public String processCommand(String command) {
        try {
            if(command.equalsIgnoreCase("ping")) {
                return "$4\r\nPONG";
            }
            RespType deserializeCommand = RespType.deserialize(command);
            return deserializeCommand.serialize();
        } catch (IllegalArgumentException e) {
            return "-Error1 " + e.getMessage() + "  comando: " + command;
        }
    }
}
