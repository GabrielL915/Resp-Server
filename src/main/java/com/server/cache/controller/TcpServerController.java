package com.server.cache.controller;

import com.server.cache.domain.service.CommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

@MessageEndpoint
public class TcpServerController {

    private final CommandService commandService;

    @Autowired
    public TcpServerController(CommandService commandService) {
        this.commandService = commandService;
    }

    @ServiceActivator(inputChannel = "inBoundChannel")
    public byte[] process(byte[] command) {
        String commandStr = new String(command, StandardCharsets.UTF_8);
        String error = "-Erro: " + commandStr;
        if (commandStr.equals("ping")) {
            String responseContent = "Hello world";

            String response = "$" + responseContent.length() + "\r\n" + responseContent;
            return response.getBytes(StandardCharsets.UTF_8);
        } else {
            return error.getBytes(StandardCharsets.UTF_8);
        }

    }
}
