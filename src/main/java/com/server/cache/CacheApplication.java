package com.server.cache;

import com.server.cache.domain.service.CommandService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CacheApplication {

	private final CommandService commandService;

    public CacheApplication(CommandService commandService) {
        this.commandService = commandService;
    }

    public static void main(String[] args) {
		SpringApplication.run(CacheApplication.class, args);
	}

	@Bean
	public TcpServer tcpServer() {
		int tcpPort = 8082;
		return new TcpServer(tcpPort, commandService);
	}

	@Bean
	CommandLineRunner startServer(TcpServer tcpServer) {
		return args -> tcpServer.startServer();
	}
}
