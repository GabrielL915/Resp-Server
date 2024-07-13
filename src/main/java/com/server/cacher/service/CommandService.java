package com.server.cacher.service;

import com.server.cacher.shared.types.RespBulkString;
import com.server.cacher.shared.types.RespData;
import com.server.cacher.shared.types.RespInteger;
import com.server.cacher.shared.types.RespSimpleString;
import org.springframework.stereotype.Service;

@Service
public class CommandService {

    public String processCommand(String command) {
        try {
            Object result = null;
            RespData respData = parseCommand(command);

            switch (respData.getType()) {
                case SIMPLE_STRING -> result = ((RespSimpleString) respData).getRawValue();
                case INTEGERS -> result = ((RespInteger) respData).getRawValue();
                case BULK_STRING -> result = ((RespBulkString) respData).getRawValue();
            }
            assert result != null;
            return result.toString();
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