package com.server.cache.service;

import com.server.cache.domain.models.RespType;
import com.server.cache.domain.service.CommandService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CommandServiceTest {

    @InjectMocks
    private CommandService commandService;

    @Test
    void testCommandServiceReturnCorrectBulkString() {
        String input = "$11\\r\\nHello world\\r\\n";
        String result = commandService.processCommand(input);

        assertEquals(input, result);
    }
}
