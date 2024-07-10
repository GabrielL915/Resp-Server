package com.server.cacher.endpoint;

import com.server.cacher.service.service.CommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;

import java.nio.charset.StandardCharsets;

@MessageEndpoint
public class TcpServerEndpoint {

    private final CommandService commandService;

    @Autowired
    public TcpServerEndpoint(CommandService commandService) {
        this.commandService = commandService;
    }

    @ServiceActivator(inputChannel = "inBoundChannel")
    public byte[] process(byte[] command) {
        String commandStr = new String(command, StandardCharsets.UTF_8);
        String response = commandService.processCommand(commandStr);
        return response.getBytes(StandardCharsets.UTF_8);

    }
}
