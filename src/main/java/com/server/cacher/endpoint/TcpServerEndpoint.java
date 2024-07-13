package com.server.cacher.endpoint;

import com.server.cacher.service.CommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;

import java.nio.charset.StandardCharsets;

@MessageEndpoint
public class TcpServerEndpoint {

    private final CommandService commandService;
    private final StringBuilder commandBuffer = new StringBuilder();

    @Autowired
    public TcpServerEndpoint(CommandService commandService) {
        this.commandService = commandService;
    }

    @ServiceActivator(inputChannel = "inBoundChannel")
    public byte[] process(byte[] command) {
        String commandStr = new String(command, StandardCharsets.UTF_8);
        commandBuffer.append(commandStr);
        if (commandBuffer.toString().endsWith("\r\n")) {
            String fullCommand = commandBuffer.toString();
            commandBuffer.setLength(0);
            String response = commandService.processCommand(commandStr);
            return response.getBytes(StandardCharsets.UTF_8);
        }
        return new byte[0];
    }
}
