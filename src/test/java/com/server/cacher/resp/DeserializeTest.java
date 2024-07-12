//package com.server.cacher.resp;
//
//import com.server.cacher.domain.models.respPattern.Arrays;
//import com.server.cacher.domain.models.respPattern.BulkString;
//import com.server.cacher.domain.models.respPattern.Error;
//import com.server.cacher.domain.models.respPattern.Integers;
//import com.server.cacher.domain.models.respPattern.RespType;
//import com.server.cacher.domain.models.respPattern.SimpleString;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//public class DeserializeTest {
//
//    @Test
//    void testDeserializeBulkString() {
//        String input = "$11\\r\\nHello world\\r\\n";
//        RespType result = RespType.deserialize(input);
//        assertInstanceOf(BulkString.class, result);
//        assertEquals("Hello world", ((BulkString) result).getMessage());
//    }
//
//    @Test
//    void testDeserializeSimpleString() {
//        String input = "+OK\\r\\n";
//        RespType result = RespType.deserialize(input);
//        assertInstanceOf(SimpleString.class, result);
//        assertEquals("OK", ((SimpleString) result).getMessage());
//    }
//
//    @Test
//    void testDeserializeError() {
//        String input = "-Error message\\r\\n";
//        RespType result = RespType.deserialize(input);
//        assertInstanceOf(Error.class, result);
//        assertEquals("Error message", ((Error) result).getErrorMessage());
//    }
//
//    @Test
//    void testDeserializeInteger() {
//        String input = ":1\\r\\n";
//        RespType result = RespType.deserialize(input);
//        assertInstanceOf(Integers.class, result);
//        assertEquals("1", ((Integers) result).getMessage());
//    }
//
//    @Test
//    void testDeserializeArray() {
//        String input = "*2\\r\\n$4\\r\\necho\\r\\n$11\\r\\nhello world\\r\\n";
//        RespType result =  RespType.deserialize(input);
//        assertInstanceOf(Arrays.class, result);
//        assertEquals("echo hello world", ((Arrays) result).getConcatenatedMessages());
//    }
//
//
//}
