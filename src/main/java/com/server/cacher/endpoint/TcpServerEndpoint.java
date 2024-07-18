package com.server.cacher.endpoint;

import com.server.cacher.service.CommandService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;

import java.nio.charset.StandardCharsets;

@MessageEndpoint
public class TcpServerEndpoint {

    private final CommandService commandService;
    private static final Logger logger = LoggerFactory.getLogger(TcpServerEndpoint.class);


    @Autowired
    public TcpServerEndpoint(CommandService commandService) {
        this.commandService = commandService;
    }

    @ServiceActivator(inputChannel = "inBoundChannel")
    public String process(Message<byte[]> message) {
        String command = new String(message.getPayload(), StandardCharsets.UTF_8).trim();
        logger.info("Received command: " + command);
        String response = commandService.processCommand(command);
        logger.info("Sending response: " + response);
        return response + "\r\n";
    }
}
