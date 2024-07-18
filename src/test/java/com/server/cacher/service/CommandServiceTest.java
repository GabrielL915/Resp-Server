package com.server.cacher.service;

import com.server.cacher.service.CommandService;
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

    @Test
    void testCommandServiceReturnCorrectSimpleString() {
        String input = "+OK\\r\\n";
        String result = commandService.processCommand(input);

        assertEquals(input, result);
    }

    @Test
    void testCommandServiceReturnCorrectError() {
        String input = "-Error message\\r\\n";
        String result = commandService.processCommand(input);

        assertEquals(input, result);
    }

    @Test
    void testCommandServiceReturnCorrectIntegers() {
        String input = ":1\\r\\n";
        String result = commandService.processCommand(input);

        assertEquals(input, result);
    }

    @Test
    void testCommandServiceReturnCorrectArray() {
        String input = "*2\\r\\n$4\\r\\necho\\r\\n$11\\r\\nhello world\\r\\n";
        String result = commandService.processCommand(input);
        assertEquals(input, result);
    }

}
