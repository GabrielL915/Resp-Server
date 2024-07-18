package com.server.cacher.service;

import com.server.cacher.shared.types.RespBulkString;
import com.server.cacher.shared.types.RespData;
import com.server.cacher.shared.types.RespInteger;
import com.server.cacher.shared.types.RespSimpleString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CommandService {

    private static final Logger logger = LoggerFactory.getLogger(CommandService.class);

    public String processCommand(String command) {
        try {
            RespData respData = parseCommand(command);

            String result = respData.getFormattedValue();
            logger.info("Processed command: " + command + ", Result: " + result);
            return result;
        } catch (IllegalArgumentException e) {
            return "-Error " + e.getMessage() + " command: " + command;
        }
    }

    private RespData parseCommand(String command) {
        char prefix = command.charAt(0);

        return switch (prefix) {
            case '+' -> new RespSimpleString(command.substring(1).trim());
            case ':' -> new RespInteger(Long.parseLong(command.substring(1).trim()));
            case '$' -> {
                String[] parts = command.split("\r\n");
                if (parts.length < 3) {
                    throw new IllegalArgumentException("Invalid bulk string format");
                }
                int length = Integer.parseInt(parts[0].substring(1).trim());
                if (length != parts[1].length()) {
                    throw new IllegalArgumentException("Bulk string length mismatch");
                }
                yield new RespBulkString(parts[1]);
            }
            default -> throw new IllegalArgumentException("Unknown command prefix" + prefix);
        };
    }
}