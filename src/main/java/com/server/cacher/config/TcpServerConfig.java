package com.server.cacher.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.ip.tcp.TcpInboundGateway;
import org.springframework.integration.ip.tcp.connection.AbstractServerConnectionFactory;
import org.springframework.integration.ip.tcp.connection.TcpNioServerConnectionFactory;
import org.springframework.messaging.MessageChannel;

@Configuration
public class TcpServerConfig {

    private static final Logger logger = LoggerFactory.getLogger(TcpServerConfig.class);

    @Value("${tcp.server.port}")
    private int port;

    @Bean
    public AbstractServerConnectionFactory serverConnectionFactory() {
        TcpNioServerConnectionFactory serverConnectionFactory = new TcpNioServerConnectionFactory(port);
        serverConnectionFactory.setUsingDirectBuffers(true);
        serverConnectionFactory.setBacklog(100);
        serverConnectionFactory.setSoTimeout(60000);
        return serverConnectionFactory;
    }

    @Bean
    public MessageChannel inBoundChannel() {
        return new DirectChannel();
    }

    @Bean
    public TcpInboundGateway inboundGateway(AbstractServerConnectionFactory serverConnectionFactory,
                                            MessageChannel inBoundChannel) {
        TcpInboundGateway tcpInboundGateway = new TcpInboundGateway();
        tcpInboundGateway.setConnectionFactory(serverConnectionFactory);
        tcpInboundGateway.setRequestChannel(inBoundChannel);
        logger.info("TcpInboundGateway configured with channel: " + inBoundChannel);
        return tcpInboundGateway;
    }

    @Bean
    public ApplicationListener<ApplicationEvent> applicationEventListener() {
        return event -> {
            if (event instanceof ContextRefreshedEvent) {
                logger.info("Context Refreshed: Server is starting");
            } else if (event instanceof ContextClosedEvent) {
                logger.info("Context Closed: Server is shutting down");
            }
        };
    }
}
