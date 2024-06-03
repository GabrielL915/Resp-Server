package com.server.cache.controller;

import com.server.cache.domain.service.CommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;

@MessageEndpoint
public class TcpServerController {

    private final CommandService commandService;

    @Autowired
    public TcpServerController(CommandService commandService) {
        this.commandService = commandService;
    }

    @ServiceActivator(inputChannel = "inBoundChannel")
    public String process(String command) {
        return commandService.processCommand(command);
    }
}
