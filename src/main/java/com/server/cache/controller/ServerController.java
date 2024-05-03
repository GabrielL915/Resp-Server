package com.server.cache.controller;

import com.server.cache.domain.service.CommandService;
import com.server.cache.shared.types.RespType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/redis")
public class ServerController {

    @Autowired
    private CommandService commandService;

    @PostMapping()
    public ResponseEntity<String> handlerCommand(@RequestBody String command) {
        RespType response = commandService.processCommand(command);
        return ResponseEntity.ok(response.serialize());
    }
}
