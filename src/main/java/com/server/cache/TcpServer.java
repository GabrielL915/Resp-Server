package com.server.cache;

import com.server.cache.domain.service.CommandService;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

@Component
public class TcpServer {
    private final int port;
    private final CommandService commandService;
    private boolean running = false;

    public TcpServer(int port, CommandService commandService) {
        this.port = port;
        this.commandService = commandService;
    }

    public void startServer() {
        running = true;
        try (ServerSocket serverSocket = new ServerSocket(port, 50, InetAddress.getByName("0.0.0.0"))) {
            System.out.println("TCP Server started on port " + port);

            while (running) {
                Socket clientSocket = serverSocket.accept();
                new Thread(new ClientHandler(clientSocket, commandService)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stopServer() {
        running = false;
    }

    private static class ClientHandler implements Runnable {
        private Socket clientSocket;
        private CommandService commandService;

        public ClientHandler(Socket socket, CommandService commandService) {
            this.clientSocket = socket;
            this.commandService = commandService;
        }

        @Override
        public void run() {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                 PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    System.out.println("Received: " + inputLine);
                    String response = commandService.processCommand(inputLine);
                    out.println(response);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}