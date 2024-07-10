package com.server.cacher.service;

import com.server.cacher.shared.enums.RespDataEnum;
import com.server.cacher.shared.types.RespData;
import com.server.cacher.shared.types.RespSimpleString;
import org.springframework.stereotype.Service;

@Service
public class CommandService {

    public String processCommand(String command) {
        try {
            RespData respData = parseCommand(command);

            if (respData.getType() == RespDataEnum.SIMPLE_STRING) {
                return ((RespSimpleString) respData).getRawValue();
            }

            return command;
        } catch (IllegalArgumentException e) {
            return "-Error " + e.getMessage() + " command: " + command;
        }
    }

    private RespData parseCommand(String command) {
        if (command.startsWith("+")) {
            return new RespSimpleString(command.substring(1).trim());
        }
        return new RespSimpleString(command);
    }
}