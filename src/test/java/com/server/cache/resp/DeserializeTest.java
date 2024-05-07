package com.server.cache.resp;

import com.server.cache.domain.models.BulkString;
import com.server.cache.domain.models.RespType;
import com.server.cache.domain.models.SimpleString;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class DeserializeTest {

    @Test
    void testDeserializeBulkString() {
        String input = "$11\\r\\nHello world\\r\\n";
        RespType result = RespType.deserialize(input);
        assertInstanceOf(BulkString.class, result);
        assertEquals("Hello world", ((BulkString) result).getMessage());
    }

    @Test
    void testDeserializeSimpleString() {
        String input = "+OK\\r\\n";
        RespType result = RespType.deserialize(input);
        assertInstanceOf(SimpleString.class, result);
        assertEquals("OK", ((SimpleString) result).getMessage());
    }
}