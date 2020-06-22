package vitaliy.telizhenko.parsers;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LimitParserTest {

    @Test
    public void testLimit(){

        LimitParser parser = new LimitParser();

        Integer actual = parser.parseLimit("8 ");

        assertEquals(8, actual);
    }

    @Test
    public void testLimitWrongNumber(){

        LimitParser parser = new LimitParser();

        assertThrows(NumberFormatException.class,() -> parser.parseLimit("f"));
    }
}