package com.server.cacher.service;

import com.server.cacher.shared.types.RespArray;
import com.server.cacher.shared.types.RespBulkString;
import com.server.cacher.shared.types.RespData;
import com.server.cacher.shared.types.RespInteger;
import com.server.cacher.shared.types.RespSimpleString;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;

@Service
@RequiredArgsConstructor
public class CommandService {

    private static final char r = '\r';
    private static final char n = '\n';

    public String processCommand(String command, InputStream inputStream) {
        try {
            Object result = null;
            RespData respData = parseCommand(command, inputStream);
            switch (respData.getType()) {
                case SIMPLE_STRING -> {
                    result = ((RespSimpleString) respData).getRawValue();
                }
                case INTEGERS -> {
                    result = ((RespInteger) respData).getRawValue();
                }
                case BULK_STRING -> {
                    result = ((RespBulkString) respData).getRawValue();
                }
                case ARRAYS -> {
                    RespData[] array = ((RespArray) respData).getRawValue();
                    result = getArraObject(array);
                }
            }
            assert result != null;
            return result.toString();
        } catch (IllegalArgumentException e) {
            return "-Error " + e.getMessage() + " command: " + command;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static Object getArraObject(RespData[] array) {
        Object result;
        StringBuilder sb = new StringBuilder();
        for (RespData data : array) {
            sb.append(data.getFormattedValue()).append("\r\n");
        }
        result = sb.toString();
        return result;
    }

    private RespData parseCommand(String command, InputStream inputStream) throws IOException {
        if (command.isEmpty()) {
            throw new IllegalArgumentException("Command cannot be empty");
        }
        String prefix = command.substring(0, 1);

        switch (prefix) {
            case "+":
                return new RespSimpleString(getString(inputStream));
            case ":":
                try {
                    long value = Integer.parseInt(command.substring(1).trim());
                    return new RespInteger(value);
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Invalid integer format", e);
                }
            case "$":
                return new RespBulkString(command.substring(1).trim());
            case "*":
                String[] parts = command.substring(1).trim().split("\\s+");
                RespData[] array = new RespData[parts.length];
                for (int i = 0; i < parts.length; i++) {
                    array[i] = new RespSimpleString(parts[i]);
                }
                return new RespArray(array);
            default:
                throw new IllegalArgumentException("Unknown command prefix: " + prefix);
        }
    }

    private String getString(InputStream inputStream) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        char charToRead = getChar(inputStream);

        while (charToRead != r) {
            stringBuilder.append(charToRead);
            charToRead = getChar(inputStream);
        }
        skipBytes(inputStream, 1);

        return stringBuilder.toString();
    }

    private char getChar(InputStream inputStream) throws IOException {
        int c = -1;
        while (c == -1) {
            c = inputStream.read();
        }
        return (char) c;
    }

    private void skipBytes(InputStream inputStream, int number) throws IOException {
        while (number != 0) {
            number -= (int) inputStream.skip(number);
        }
    }
}